package com.github.itdachen.framework.weixin.properties;

/**
 * Description: 微信支付平台配置信息获取接口 IWeChatPayProperties
 * Created by 剑鸣秋朔 on 2023/03/18 23:12
 * Created with IntelliJ IDEA.
 */
public interface IWeChatPayProperties {

    /**
     * 公众号/小程序 appId
     */
    String getAppId();

    /**
     * 商户号
     */
    String getMchId();

    /**
     * 商户API证书序列号
     */
    String getMchSerialNo();

    /**
     * 商户私钥文件地址
     */
    String getPrivateKeyPath();

    /**
     * APIv3密钥
     */
    String getSecretKey();

    /**
     * 接收结果通知地址, 配置域名, 例如: https://api.pay.itdachen.github.com
     */
    String getNotifyDomain();

}
