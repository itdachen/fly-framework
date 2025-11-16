package com.github.itdachen.framework.weixin.wxpay.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description:
 * Created by 剑鸣秋朔 on 2023/04/11 21:52
 * Created with IntelliJ IDEA.
 */
public class WeChatPayOrderInfo implements Serializable {
    private static final long serialVersionUID = 4545920043925236270L;

    /**
     * 商户号
     */
    private String mchid;

    /**
     * appId
     */
    private String appId;

    /**
     * 第三方用户唯一标识
     */
    private String openId;

    /**
     * 商户系统内部订单号, 这里跟主键一致
     */
    private String outTradeNo;

    /**
     * 系统购买时的订单ID
     */
    private String orderId;

    /**
     * 支付金额
     */
    private BigDecimal totalAmount;

    /**
     * 实际支付金额
     */
    private BigDecimal realAmount;

    /**
     * 商品描述
     */
    private String title;

    /**
     * 附加数据
     */
    private String attach;

    /**
     * 回调地址
     */
    private String notifyUrl;

    public void setMchid(String mchid) {
        this.mchid = mchid;
    }

    public String getMchid() {
        return mchid;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppId() {
        return appId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public String getAttach() {
        return attach;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("mchid", getMchid())
                .append("appId", getAppId())
                .append("openId", getOpenId())
                .append("outTradeNo", getOutTradeNo())
                .append("orderId", getOrderId())
                .append("totalAmount", getTotalAmount())
                .append("realAmount", getRealAmount())
                .append("title", getTitle())
                .append("attach", getAttach())
                .append("notifyUrl", getNotifyUrl())
                .toString();
    }

}
