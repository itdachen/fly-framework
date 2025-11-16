package com.github.itdachen.framework.weixin.common.mp;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Description:
 * Created by 剑鸣秋朔 on 2023/05/08 9:35
 * Created with IntelliJ IDEA.
 */
public class EncryptUtils {
    private static Logger log = LoggerFactory.getLogger(EncryptUtils.class);

    public static String encrypt(String keyStr, String datastr) {
        String s = null;
        try {
            //加密
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] decrypt = cipher.doFinal(datastr.getBytes());
            byte[] decrypt1 = Base64.encodeBase64(decrypt);
            s = new String(decrypt1);
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("加密失败" + e.getMessage());
        }
        return s;
    }

    /**
     * @Description: 解密
     * @author: zhenghao
     * @date: 2019/7/30 15:13
     */
    public static String Decrypt(String keyStr, String datastr) {
        String s = null;
        try {
            Key key = generateKey(keyStr);
            Cipher cipher1 = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher1.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypt2 = cipher1.doFinal(Base64.decodeBase64(datastr));
            s = new String(decrypt2);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("解密失败" + e.getMessage());
        }
        return s;
    }

    private static Key generateKey(String key) throws Exception {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), "AES");
            return keySpec;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String keyStr = "D3BF52E111B8F129";
        String datastr = "q8DtrPAfnJ/awzRnW44ui4YSQ/VIsm/5YdOn9lWFi8f1OTGH+3UNGnZRlRIvUXhTgJu8Y8F9yFIPLftxhgjvYz3cS9qrpo08/8gRT4xtXwFuWse+mc5J9Gt5Qf1U/aDCerV744NB1uzfOaG1umIGemR7vwyNCY88xGw3w0+iIpBNBg+bvbJ5j8GZv+owIkJf+mcUUqVFNXeaO+yFGmHHfg17iIdrSTQRYBAAO9Dx2+/tuvbep8QiXXrl2yMZfrByXooxIGxI1DOxwZl0qskuE/QWb5ePYTdkcdETQz/F1xx/1c9eEN1BGtP4YrVg0EhZx3ZqTlz3fFC4ns/r+8MutnyF2QzVhZLsI1ec1Qd0CbA59A4ivEHpxhmxk61yyhZ7IyxC7UZfS5O8vRVVB7deC33VTyxvCfq/wKnGZnCK2xYH6VVKwvl8GifDVneU57zf0Bi8wlSQ7VR11LwFuom00ICP/aDzKtfxSZtPsELaXkY=";
        try {
            //加密
            Key key = generateKey(keyStr);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] decrypt = cipher.doFinal(datastr.getBytes());
            byte[] decrypt1 = Base64.encodeBase64(decrypt);
            System.out.println("decrypt1: " + new String(decrypt1));

            //解密
            Cipher cipher1 = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher1.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypt2 = cipher1.doFinal(Base64.decodeBase64(datastr));
            System.out.println("decrypt2: " + new String(decrypt2));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
