package com.github.itdachen.framework.weixin.properties.autoconfigure.pay.decrypt;

import com.github.itdachen.framework.weixin.properties.IWeChatPayProperties;
import com.github.itdachen.framework.weixin.properties.autoconfigure.pay.WeChatPayConfigurationProperties;

/**
 * Description: 不加密
 * Created by 剑鸣秋朔 on 2023/04/11 22:59
 * Created with IntelliJ IDEA.
 */
public class NoneWeChatPayDecrypt implements IWeChatPayProperties {

    private final WeChatPayConfigurationProperties properties;

    public NoneWeChatPayDecrypt(WeChatPayConfigurationProperties properties) {
        this.properties = properties;
    }

    @Override
    public String getAppId() {
        return properties.getAppId();
    }

    @Override
    public String getMchId() {
        return properties.getMchId();
    }

    @Override
    public String getMchSerialNo() {
        return properties.getMchSerialNo();
    }

    @Override
    public String getPrivateKeyPath() {
        return properties.getPrivateKeyPath();
    }

    @Override
    public String getSecretKey() {
        return properties.getSecretKey();
    }

    @Override
    public String getNotifyDomain() {
        return properties.getNotifyDomain();
    }
}
