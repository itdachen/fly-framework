package com.github.itdachen.framework.weixin.wxpay.callback;

/**
 * Description: 退款业务处理接口
 * * * 退款处理接口，为了防止项目开发人员，不手动判断退款失败的情况
 * * * 退款失败：退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台-交易中心，手动处理此笔退款
 * Created by 王大宸 on 2023/04/11 22:04
 * Created with IntelliJ IDEA.
 */
public interface WeChatRefundCallback {

    /**
     * 退款成功处理情况
     */
    void success(WeChatCallbackRefund refundData) throws Exception;

    /**
     * 退款失败处理情况
     */
    void fail(WeChatCallbackRefund refundData) throws Exception;

}
