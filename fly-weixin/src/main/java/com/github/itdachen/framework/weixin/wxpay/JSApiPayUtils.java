package com.github.itdachen.framework.weixin.wxpay;

import com.github.itdachen.framework.weixin.wxpay.api.WeChatPayApi;
import com.github.itdachen.framework.weixin.wxpay.entity.WeChatPayOrderInfo;
import com.github.itdachen.framework.weixin.wxpay.utils.HttpPayUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: JSAPI 支付
 * Created by 王大宸 on 2023/04/11 21:50
 * Created with IntelliJ IDEA.
 */
public class JSApiPayUtils {
    private static final Logger logger = LoggerFactory.getLogger(JSApiPayUtils.class);

    /***
     * wxJsApiPay
     *
     * @author 王大宸
     * @date 2023/4/11 21:52
     * @param payOrderInfo payOrderInfo
     * @param wxPayClient wxPayClient
     * @return java.lang.String
     */
    public static String pay(WeChatPayOrderInfo payOrderInfo,
                             CloseableHttpClient wxPayClient) throws Exception {
        // 1.获取请求参数的Map格式
        Map<String, Object> paramsMap = bizPayParams(payOrderInfo);

        // 1.1 添加支付者信息
        Map<String, String> payer = new HashMap<>();
        payer.put("openid", payOrderInfo.getOpenId());

        paramsMap.put("payer", payer);

        // 2.获取请求对象
        HttpPost httpPost = HttpPayUtils.getHttpPost(WeChatPayApi.JS_API, paramsMap);

        // 3.完成签名并执行请求
        CloseableHttpResponse response = null;
        try {
            response = wxPayClient.execute(httpPost);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception("微信支付请求失败");
        }

        // 4.解析response对象
        HashMap<String, String> resultMap = HttpPayUtils.resolverResponse(response);
        if (resultMap != null) {
            logger.info("支付成功: " + resultMap.toString());
            // native请求返回的是二维码链接，前端将链接转换成二维码即可
            return resultMap.get("prepay_id");
        }
        return null;
    }


    /**
     * 封装基础通用请求参数
     *
     * @param payOrderInfo 微信支付请求数据
     * @return 封装后的map对象
     */
    private static Map<String, Object> bizPayParams(WeChatPayOrderInfo payOrderInfo) {
        Map<String, Object> bizPayParams = new HashMap<>();
        bizPayParams.put("appid", payOrderInfo.getAppId());
        bizPayParams.put("mchid", payOrderInfo.getMchid());

        // 如果商品名称过长则截取
        String title = payOrderInfo.getTitle().length() > 62 ? payOrderInfo.getTitle().substring(0, 62) : payOrderInfo.getTitle();
        bizPayParams.put("description", title);
        bizPayParams.put("out_trade_no", payOrderInfo.getOutTradeNo());
        bizPayParams.put("notify_url", payOrderInfo.getNotifyUrl());

        /* 支付金额 */
        Map<String, Object> amountMap = new HashMap<>();
        amountMap.put("total", payOrderInfo.getRealAmount().multiply(new BigDecimal("100")).intValue());
        amountMap.put("currency", "CNY");//货币类型

        bizPayParams.put("amount", amountMap);
        return bizPayParams;
    }

}
