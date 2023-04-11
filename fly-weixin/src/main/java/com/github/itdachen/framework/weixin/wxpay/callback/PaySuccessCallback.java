package com.github.itdachen.framework.weixin.wxpay.callback;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description: 微信支付成功回调返回的数据
 * Created by 王大宸 on 2023/04/11 22:02
 * Created with IntelliJ IDEA.
 */
public class PaySuccessCallback implements Serializable {
    private static final long serialVersionUID = 9012900318128515903L;

    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 微信支付系统生成的订单号
     */
    private String transactionId;

    /**
     * 交易状态
     * SUCCESS：支付成功
     * REFUND：转入退款
     * NOTPAY：未支付
     * CLOSED：已关闭
     * REVOKED：已撤销（付款码支付）
     * USERPAYING：用户支付中（付款码支付）
     * PAYERROR：支付失败(其他原因，如银行返回失败)
     */
    private String tradestate;

    /**
     * 支付完成时间
     */
    private DateTime successTime;

    /**
     * 交易类型
     * JSAPI：公众号支付
     * NATIVE：扫码支付
     * APP：APP支付
     * MICROPAY：付款码支付
     * MWEB：H5支付
     * FACEPAY：刷脸支付
     */
    private String tradetype;

    /**
     * 订单总金额
     */
    private BigDecimal totalMoney;


    public DateTime getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        // Hutool工具包的方法，自动识别一些常用格式的日期字符串
        this.successTime = DateUtil.parse(successTime);
        //  this.successTime = LocalDateTimeUtil.parse(successTime);
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTradestate() {
        return tradestate;
    }

    public void setTradestate(String tradestate) {
        this.tradestate = tradestate;
    }

    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }

    public BigDecimal getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        this.totalMoney = totalMoney;
    }

}
