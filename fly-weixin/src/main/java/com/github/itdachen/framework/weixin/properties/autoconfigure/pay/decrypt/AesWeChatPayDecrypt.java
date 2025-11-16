package com.github.itdachen.framework.weixin.properties.autoconfigure.pay.decrypt;

import com.github.itdachen.framework.crypto.aes.AesEncryptEncoder;
import com.github.itdachen.framework.weixin.properties.IWeChatPayProperties;
import com.github.itdachen.framework.weixin.properties.constents.CryptoKeyConstants;
import com.github.itdachen.framework.weixin.properties.autoconfigure.pay.WeChatPayConfigurationProperties;

/**
 * Description: AES 解密
 * Created by 剑鸣秋朔 on 2023/04/11 23:03
 * Created with IntelliJ IDEA.
 */
public class AesWeChatPayDecrypt implements IWeChatPayProperties {

    private final WeChatPayConfigurationProperties properties;

    public AesWeChatPayDecrypt(WeChatPayConfigurationProperties properties) {
        this.properties = properties;
    }

    @Override
    public String getAppId() {
        return AesEncryptEncoder.decryptStr(properties.getAppId(), CryptoKeyConstants.SECRET_KEY);
    }

    @Override
    public String getMchId() {
        return AesEncryptEncoder.decryptStr(properties.getMchId(), CryptoKeyConstants.SECRET_KEY);
    }

    @Override
    public String getMchSerialNo() {
        return AesEncryptEncoder.decryptStr(properties.getMchSerialNo(), CryptoKeyConstants.SECRET_KEY);
    }

    @Override
    public String getPrivateKeyPath() {
        return AesEncryptEncoder.decryptStr(properties.getPrivateKeyPath(), CryptoKeyConstants.SECRET_KEY);
    }

    @Override
    public String getSecretKey() {
        return AesEncryptEncoder.decryptStr(properties.getSecretKey(), CryptoKeyConstants.SECRET_KEY);
    }

    @Override
    public String getNotifyDomain() {
        if (properties.getNotifyDomain().startsWith("http://") || properties.getNotifyDomain().startsWith("https://")) {
            return properties.getNotifyDomain();
        }
        return AesEncryptEncoder.decryptStr(properties.getNotifyDomain(), CryptoKeyConstants.SECRET_KEY);
    }

}
