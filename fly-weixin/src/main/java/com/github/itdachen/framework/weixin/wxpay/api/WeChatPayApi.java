package com.github.itdachen.framework.weixin.wxpay.api;

/**
 * Description: 微信订单支付系列接口
 * Created by 剑鸣秋朔 on 2023/04/11 21:55
 * Created with IntelliJ IDEA.
 */
public class WeChatPayApi {

    /**
     * 微信支付主机地址
     */
    public static final String WE_CHAT_PAY_HOST = "https://api.mch.weixin.qq.com" ;

    /**
     * jsapi下单
     */
    public static final String JS_API = WE_CHAT_PAY_HOST + "/v3/pay/transactions/jsapi" ;

    /**
     * Native下单
     */
    public static final String NATIVE_ORDER = WE_CHAT_PAY_HOST + "/v3/pay/transactions/native" ;

    /**
     * Native订单状态查询, 根据商户订单号查询
     */
    public static final String NATIVE_QUERY = WE_CHAT_PAY_HOST + "/v3/pay/transactions/out-trade-no/%s?mchid=%s" ;

    /**
     * 关闭订单接口
     */
    public static final String NATIVE_CLOSE_ORDER = WE_CHAT_PAY_HOST + "/v3/pay/transactions/out-trade-no/%s/close" ;

    /**
     * 申请退款接口
     */
    public static final String NATIVE_REFUND_ORDER = WE_CHAT_PAY_HOST + "/v3/refund/domestic/refunds" ;

    /**
     * 退款状态查询接口
     */
    public static final String NATIVE_REFUND_QUERY = WE_CHAT_PAY_HOST + "/v3/refund/domestic/refunds/%s" ;

}
