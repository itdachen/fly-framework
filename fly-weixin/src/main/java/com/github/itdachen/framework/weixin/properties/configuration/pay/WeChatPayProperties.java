package com.github.itdachen.framework.weixin.properties.configuration.pay;

import java.io.Serializable;

/**
 * Description: 微信支付配置
 * Created by 王大宸 on 2023/03/19 13:26
 * Created with IntelliJ IDEA.
 */
public class WeChatPayProperties implements Serializable {
    private static final long serialVersionUID = 3413550161118989335L;


    /**
     * 小程序/公众号等 appId
     */
    private String appId;

    /**
     * 商户号
     */
    private String mchId;

    /**
     * 商户API证书序列号
     */
    private String mchSerialNo;

    /**
     * 商户私钥文件地址
     */
    private String privateKeyPath;

    /**
     * APIv3密钥
     */
    private String key;

    /**
     * 接收结果通知地址, 配置域名, 例如: https://api.pay.itdachen.com
     */
    private String notifyDomain;

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchSerialNo() {
        return mchSerialNo;
    }

    public void setMchSerialNo(String mchSerialNo) {
        this.mchSerialNo = mchSerialNo;
    }

    public String getPrivateKeyPath() {
        return privateKeyPath;
    }

    public void setPrivateKeyPath(String privateKeyPath) {
        this.privateKeyPath = privateKeyPath;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNotifyDomain() {
        return notifyDomain;
    }

    public void setNotifyDomain(String notifyDomain) {
        this.notifyDomain = notifyDomain;
    }

}
