package com.github.itdachen.framework.weixin.properties.crypto;

import com.github.itdachen.framework.crypto.aes.AesEncryptEncoder;
import com.github.itdachen.framework.weixin.properties.constents.CryptoKeyConstants;

/**
 * Description: 加密工具类
 * Created by 剑鸣秋朔 on 2023/04/11 22:59
 * Created with IntelliJ IDEA.
 */
public class WeChatAesCryptoUtils {

    public static String encrypt(String str) {
        return AesEncryptEncoder.encryptStr(str, CryptoKeyConstants.SECRET_KEY);
    }

}
