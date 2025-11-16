package com.github.itdachen.framework.weixin.wxpay.callback;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.math.BigDecimal;

/**
 * Description: 退款回调返回数据信息
 * Created by 剑鸣秋朔 on 2023/04/11 22:04
 * Created with IntelliJ IDEA.
 */
public class WeChatCallbackRefund {

    /**
     * 商户订单号
     */
    private String orderId;

    /**
     * 商户退款单号
     */
    private String outRefundNo;

    /**
     * 微信支付退款单号
     */
    private String refundId;

    /**
     * 微信支付系统生成的订单号
     */
    private String transactionId;

    /**
     * 微信支付系统生成的退款订单号
     */
    private String transactionRefundId;

    /**
     * 退款渠道
     * ORIGINAL：原路退款
     * BALANCE：退回到余额
     * OTHER_BALANCE：原账户异常退到其他余额账户
     * OTHER_BANKCARD：原银行卡异常退到其他银行卡
     */
    private String 	channel;

    /**
     * 退款成功时间
     * 当前退款成功时才有此返回值
     */
    private DateTime successTime;

    /**
     * 退款状态
     * 退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台-交易中心，手动处理此笔退款。
     * SUCCESS：退款成功
     * CLOSED：退款关闭
     * PROCESSING：退款处理中
     * ABNORMAL：退款异常
     */
    private String 	status;

    /**
     * 退款金额
     */
    private BigDecimal refundMoney;


    public DateTime getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime =   DateUtil.parse(successTime);
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOutRefundNo() {
        return outRefundNo;
    }

    public void setOutRefundNo(String outRefundNo) {
        this.outRefundNo = outRefundNo;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionRefundId() {
        return transactionRefundId;
    }

    public void setTransactionRefundId(String transactionRefundId) {
        this.transactionRefundId = transactionRefundId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(BigDecimal refundMoney) {
        this.refundMoney = refundMoney;
    }

    @Override
    public String toString() {
        return "WeChatCallbackRefund{" +
                "orderId='" + orderId + '\'' +
                ", refundId='" + refundId + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", transactionRefundId='" + transactionRefundId + '\'' +
                ", channel='" + channel + '\'' +
                ", successTime=" + successTime +
                ", status='" + status + '\'' +
                ", refundMoney=" + refundMoney +
                '}';
    }


}
