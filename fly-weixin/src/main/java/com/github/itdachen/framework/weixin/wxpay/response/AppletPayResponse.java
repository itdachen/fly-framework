package com.github.itdachen.framework.weixin.wxpay.response;

import java.io.Serializable;

/**
 * Description: 微信小程序发起支付返回给小程序的信息
 * Created by 剑鸣秋朔 on 2023/04/11 21:49
 * Created with IntelliJ IDEA.
 */
public class AppletPayResponse  implements Serializable {
    private static final long serialVersionUID = 5052896731749018615L;

    /**
     * 商户系统内部订单号
     */
    private String outTradeNo;

    /**
     * 时间戳
     */
    private String timeStamp;

    /**
     * 随机字符串
     */
    private String nonceStr;

    /**
     * 订单详情扩展字符串
     */
    private String packagePrepayId;

    /**
     * 签名方式
     */
    private String signType = "RSA";

    /**
     * 签名
     */
    private String paySign;

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackagePrepayId() {
        return packagePrepayId;
    }

    public void setPackagePrepayId(String packagePrepayId) {
        this.packagePrepayId = packagePrepayId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

}
