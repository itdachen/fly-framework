package com.github.itdachen.framework.weixin.properties.autoconfigure.pay;

/**
 * Description: 微信支付配置文件
 * Created by 王大宸 on 2023/04/11 21:29
 * Created with IntelliJ IDEA.
 */
public class WeChatPayConfigurationProperties {

    /**
     * 是否启动微信支付
     */
    private Boolean enabled = false;

    /**
     * 是否加密
     */
    private Boolean crypto = false;

    /**
     * 公众号/小程序 appId
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
    private String secretKey;

    /**
     * 接收结果通知地址, 配置域名, 例如: https://api.pay.itdachen.github.com
     */
    private String notifyDomain;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getCrypto() {
        return crypto;
    }

    public void setCrypto(Boolean crypto) {
        this.crypto = crypto;
    }

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

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
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
