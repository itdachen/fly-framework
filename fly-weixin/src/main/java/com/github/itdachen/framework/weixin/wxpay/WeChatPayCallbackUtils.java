package com.github.itdachen.framework.weixin.wxpay;

import com.alibaba.fastjson.JSONObject;
import com.github.itdachen.framework.weixin.wxpay.callback.PaySuccessCallback;
import com.github.itdachen.framework.weixin.wxpay.callback.WeChatCallbackRefund;
import com.github.itdachen.framework.weixin.wxpay.callback.WeChatRefundCallback;
import com.github.itdachen.framework.weixin.wxpay.utils.HttpPayUtils;
import com.github.itdachen.framework.weixin.wxpay.utils.WeChatSignatureVerification;
import com.google.gson.Gson;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.util.AesUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Description: 微信支付成功回调
 * Created by 王大宸 on 2023/04/11 22:12
 * Created with IntelliJ IDEA.
 */
public class WeChatPayCallbackUtils {
    private static final Logger logger = LoggerFactory.getLogger(WeChatPayCallbackUtils.class);

    /***
     * 微信支付创建订单回调方法
     *
     * @author 王大宸
     * @date 2023/2/22 23:03
     * @param request request
     * @param response response
     * @param verifier         证书
     * @param secretKey        V3 支付秘钥
     * @param businessCallback 回调方法，用于处理业务逻辑
     * @return java.lang.String
     */
    public static String wxPaySuccessCallback(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Verifier verifier,
                                              String secretKey,
                                              Consumer<PaySuccessCallback> businessCallback) throws Exception {
        Gson gson = new Gson();

        // 1.处理通知参数
        final String body = HttpPayUtils.readData(request);
        HashMap<String, Object> bodyMap = gson.fromJson(body, HashMap.class);

        // 2.签名验证
        WeChatSignatureVerification wechatForRequest = new WeChatSignatureVerification(verifier, body, (String) bodyMap.get("id"));
        try {
            if (!wechatForRequest.validate(request)) {
                // 通知验签失败
                response.setStatus(500);
                JSONObject jsonResponse = new JSONObject();
                jsonResponse.put("code", "ERROR");
                jsonResponse.put("message", "通知验签失败");
                return jsonResponse.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        // 3.获取明文数据
        String plainText = decryptFromResource(bodyMap, secretKey);
        HashMap<String, Object> plainTextMap = gson.fromJson(plainText, HashMap.class);
        logger.info("plainTextMap: {}", plainTextMap);

        // 4.封装微信返回的数据
        PaySuccessCallback callbackData = new PaySuccessCallback();
        callbackData.setSuccessTime(String.valueOf(plainTextMap.get("success_time")));
        callbackData.setOutTradeNo(String.valueOf(plainTextMap.get("out_trade_no")));
        callbackData.setTransactionId(String.valueOf(plainTextMap.get("transaction_id")));
        callbackData.setTradestate(String.valueOf(plainTextMap.get("trade_state")));
        callbackData.setTradetype(String.valueOf(plainTextMap.get("trade_type")));
        String amount = String.valueOf(plainTextMap.get("amount"));
        HashMap<String, Object> amountMap = gson.fromJson(amount, HashMap.class);
        String total = String.valueOf(amountMap.get("total"));
        callbackData.setTotalMoney(new BigDecimal(total).movePointLeft(2));


        logger.info("callbackData: {}", callbackData);

        if ("SUCCESS".equals(callbackData.getTradestate())) {
            // 执行业务逻辑
            businessCallback.accept(callbackData);
        }

        // 5.成功应答
        response.setStatus(200);
        final HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "SUCCESS");
        resultMap.put("message", "成功");
        return gson.toJson(resultMap);
    }


    /**
     * 微信支付申请退款回调方法
     *
     * @param verifier       证书
     * @param secretKey      加密 key
     * @param refundCallback 回调方法，用于处理业务逻辑，包含退款成功处理于退款失败处理
     * @return json格式的string数据，直接返回给微信
     */
    public static String wxPayRefundCallback(HttpServletRequest request,
                                             HttpServletResponse response,
                                             Verifier verifier,
                                             String secretKey,
                                             WeChatRefundCallback refundCallback) throws Exception {
        Gson gson = new Gson();

        // 1.处理通知参数
        final String body = HttpPayUtils.readData(request);
        HashMap<String, Object> bodyMap = gson.fromJson(body, HashMap.class);

        // 2.签名验证
        WeChatSignatureVerification wechatForRequest = new WeChatSignatureVerification(verifier, body, (String) bodyMap.get("id"));
        try {
            if (!wechatForRequest.validate(request)) {
                // 通知验签失败
                response.setStatus(500);
                final HashMap<String, Object> map = new HashMap<>();
                map.put("code", "ERROR");
                map.put("message", "通知验签失败");
                return gson.toJson(map);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 3.获取明文数据
        String plainText = decryptFromResource(bodyMap, secretKey);
        HashMap<String, Object> plainTextMap = gson.fromJson(plainText, HashMap.class);

        // 4.封装微信返回的数据
        WeChatCallbackRefund refundData = getRefundCallbackData(plainTextMap);

        if ("SUCCESS".equals(refundData.getStatus())) {
            // 执行业务逻辑
            refundCallback.success(refundData);
        } else {
            // 特殊情况退款失败业务处理，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台-交易中心，手动处理此笔退款
            refundCallback.fail(refundData);
        }

        // 5.成功应答
        response.setStatus(200);
        final HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("code", "SUCCESS");
        resultMap.put("message", "成功");
        return gson.toJson(resultMap);
    }

    private static WeChatCallbackRefund getRefundCallbackData(HashMap<String, Object> plainTextMap) {
        Gson gson = new Gson();
        // {transaction_id=4200001643202302264853277372,
        //  mchid=1622460525,
        //  out_trade_no=1629720856265625600,
        //  refund_status=SUCCESS,
        //  out_refund_no=1629734451724554240,
        //  success_time=2023-02-26T14:45:56+08:00,
        //  user_received_account=支付用户零钱,
        //  refund_id=50302305052023022631497157326,
        //  amount={total=11.0,
        //          refund=5.0,
        //          payer_total=11.0,
        //          payer_refund=5.0
        //      },
        //  }
        WeChatCallbackRefund refundData = new WeChatCallbackRefund();
        String successTime = String.valueOf(plainTextMap.get("success_time"));
        if (StringUtils.isNoneBlank(successTime)) {
            refundData.setSuccessTime(successTime);
        }
        refundData.setOrderId(String.valueOf(plainTextMap.get("out_trade_no")));
        refundData.setOutRefundNo(String.valueOf(plainTextMap.get("out_refund_no")));
        refundData.setTransactionId(String.valueOf(plainTextMap.get("transaction_id")));
        refundData.setTransactionRefundId(String.valueOf(plainTextMap.get("refund_id")));
        refundData.setChannel(String.valueOf(plainTextMap.get("channel")));
        final String status = String.valueOf(plainTextMap.get("refund_status"));
        refundData.setStatus(status);

        String amount = String.valueOf(plainTextMap.get("amount"));
        HashMap<String, Object> amountMap = gson.fromJson(amount, HashMap.class);
        String refundMoney = String.valueOf(amountMap.get("refund"));
        refundData.setRefundMoney(new BigDecimal(refundMoney).movePointLeft(2));

        logger.info("refundData: {}", refundData);
        return refundData;
    }

    /**
     * 对称解密
     */
    private static String decryptFromResource(HashMap<String, Object> bodyMap,
                                              String secretKey) throws Exception {
        // 通知数据
        Map<String, String> resourceMap = (Map) bodyMap.get("resource");
        // 数据密文
        String ciphertext = resourceMap.get("ciphertext");
        // 随机串
        String nonce = resourceMap.get("nonce");
        // 附加数据
        String associateData = resourceMap.get("associated_data");
        AesUtil aesUtil = new AesUtil(secretKey.getBytes(StandardCharsets.UTF_8));
        try {
            return aesUtil.decryptToString(
                    associateData.getBytes(StandardCharsets.UTF_8),
                    nonce.getBytes(StandardCharsets.UTF_8),
                    ciphertext);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new Exception("解密失败");
        }
    }



}
