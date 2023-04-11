package com.github.itdachen.framework.weixin.wxpay;

import com.github.itdachen.framework.weixin.wxpay.entity.WeChatRefundParam;
import com.github.itdachen.framework.weixin.wxpay.utils.HttpPayUtils;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description: 退款
 * Created by 王大宸 on 2023/04/11 22:30
 * Created with IntelliJ IDEA.
 */
public class WeChatPayRefundUtils {
    private static final Logger logger = LoggerFactory.getLogger(WeChatPayRefundUtils.class);
    private static final String HTTP_URL = "https://api.mch.weixin.qq.com/v3/refund/domestic/refunds";


    /**
     * 发起微信退款申请
     *
     * @param param       微信支付申请退款请求参数
     * @param wxPayClient 微信请求客户端（）
     * @return 微信支付二维码地址
     */
    public static String refundPay(WeChatRefundParam param,
                                   CloseableHttpClient wxPayClient) throws Exception {
        // 1.获取请求参数的Map格式
        Map<String, Object> paramsMap = getRefundParams(param);

        // 2.获取请求对象
        HttpPost httpPost = getHttpPost(paramsMap);

        // 3.完成签名并执行请求
        CloseableHttpResponse response = null;
        try {
            response = wxPayClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("微信支付请求失败");
        }

        // 4.解析response对象
      //  HashMap<String, String> resultMap = resolverResponse(response);
        HashMap<String, String> resultMap = HttpPayUtils.resolverResponse(response);
        logger.info("发起退款参数：{}", resultMap);
        if (resultMap != null) {
            // 返回微信支付退款单号
            return resultMap.get("refund_id");
        }
        return null;
    }

    /**
     * 解析响应数据
     *
     * @param response 发送请求成功后，返回的数据
     * @return 微信返回的参数
     */
    private static HashMap<String, String> resolverResponse(CloseableHttpResponse response) throws Exception {
        try {
            // 1.获取请求码
            int statusCode = response.getStatusLine().getStatusCode();
            // 2.获取返回值 String 格式
            final String bodyAsString = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();
            if (statusCode == 200) {
                // 3.如果请求成功则解析成Map对象返回
                return gson.fromJson(bodyAsString, HashMap.class);
            } else {
                if (StringUtils.isNoneBlank(bodyAsString)) {
                    logger.error("微信支付请求失败，提示信息:{}", bodyAsString);
                    // 4.请求码显示失败，则尝试获取提示信息
                    HashMap<String, String> resultMap = gson.fromJson(bodyAsString, HashMap.class);
                    throw new Exception(resultMap.get("message"));
                }
                logger.error("微信支付请求失败，未查询到原因，提示信息:{}", response);
                // 其他异常，微信也没有返回数据，这就需要具体排查了
                throw new IOException("request failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获取请求对象（Post请求）
     *
     * @param paramsMap 请求参数
     * @return Post请求对象
     */
    private static HttpPost getHttpPost(Map<String, Object> paramsMap) {
        // 1.设置请求地址
        HttpPost httpPost = new HttpPost(HTTP_URL);

        // 2.设置请求数据
        Gson gson = new Gson();
        String jsonParams = gson.toJson(paramsMap);

        // 3.设置请求信息
        StringEntity entity = new StringEntity(jsonParams, "utf-8");
        entity.setContentType("application/json");
        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        return httpPost;
    }

    /**
     * 封装微信支付申请退款请求参数
     *
     * @param param 微信支付申请退款请求参数
     * @return 封装后的map微信支付申请退款请求参数对象
     */
    private static Map<String, Object> getRefundParams(WeChatRefundParam param) throws Exception {
        Map<String, Object> paramsMap = new HashMap<>();
        if (StringUtils.isNoneBlank(param.getTransactionId())) {
            paramsMap.put("transaction_id", param.getTransactionId());
        } else if (StringUtils.isNoneBlank(param.getOutTradeNo())) {
            paramsMap.put("out_trade_no", param.getOutTradeNo());
        } else {
            throw new Exception("微信支付订单号和商户订单号必须填写一个");
        }
        paramsMap.put("out_refund_no", param.getOutRefundNo());
        paramsMap.put("notify_url", param.getNotifyUrl());

        if (StringUtils.isNoneBlank(param.getReason())) {
            paramsMap.put("reason", param.getReason());
        }
        if (StringUtils.isNoneBlank(param.getFundsAccount())) {
            paramsMap.put("funds_account", param.getFundsAccount());
        }

        /* 订单金额信息 */
        Map<String, Object> amountMap = new HashMap<>();
        amountMap.put("refund", param.getAmount().getRefund().multiply(new BigDecimal("100")).intValue());
        amountMap.put("total", param.getAmount().getTotal().multiply(new BigDecimal("100")).intValue());
        if (StringUtils.isNoneBlank(param.getAmount().getCurrency())) {
            amountMap.put("currency", "CNY");
        } else {
            amountMap.put("currency", param.getAmount().getCurrency());
        }

        /* 退款出资账户及金额 */
        List<WeChatRefundParam.WeChatRefundAmount.WeChatRefundAmountForm> froms = param.getAmount().getFrom();
        if (null != froms && 0 < froms.size()) {
            List<Map<String, Object>> fromList = new ArrayList<>();
            Map<String, Object> one;
            for (WeChatRefundParam.WeChatRefundAmount.WeChatRefundAmountForm from : froms) {
                one = new HashMap<>();
                one.put("account", from.getAccount());
                one.put("amount", from.getAmount());
                fromList.add(one);
            }
            amountMap.put("from", fromList);
        }
        paramsMap.put("amount", amountMap);

        List<WeChatRefundParam.WeChatRefundGoodsDetail> goodsDetails = param.getGoodsDetail();
        if (null != goodsDetails && 0 < goodsDetails.size()) {
            List<Map<String, Object>> goodsDetailList = new ArrayList<>();
            Map<String, Object> one;
            for (WeChatRefundParam.WeChatRefundGoodsDetail goodsDetail : goodsDetails) {
                one = new HashMap<>();
                one.put("merchant_goods_id", goodsDetail.getMerchantGoodsId());
                one.put("unit_price", goodsDetail.getUnitPrice().multiply(new BigDecimal("100")).intValue());
                one.put("refund_amount", goodsDetail.getRefundAmount().multiply(new BigDecimal("100")).intValue());
                one.put("refund_quantity", goodsDetail.getRefundQuantity());
                if (StringUtils.isNoneBlank(goodsDetail.getWechatpayGoodsId())) {
                    one.put("wechatpay_goods_id", goodsDetail.getWechatpayGoodsId());
                }
                if (StringUtils.isNoneBlank(goodsDetail.getGoodsName())) {
                    one.put("goods_name", goodsDetail.getGoodsName());
                }
                goodsDetailList.add(one);
            }
            paramsMap.put("goods_detail", goodsDetailList);
        }

        return paramsMap;
    }



}
