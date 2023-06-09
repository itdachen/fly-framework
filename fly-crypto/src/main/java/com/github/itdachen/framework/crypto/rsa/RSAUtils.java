package com.github.itdachen.framework.crypto.rsa;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Description:
 * Created by 王大宸 on 2023/05/08 9:50
 * Created with IntelliJ IDEA.
 */
public class RSAUtils {

    //解密
    public static String encrypt(String dataString, String publicDecodeKeyString) {
        try {
            PublicKey publicKey = RSAUtils.loadPublicKey(publicDecodeKeyString);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] bytes = dataString.getBytes("UTF-8");
            byte[] b = cipher.doFinal(bytes);
            return Base64.getEncoder().encodeToString(b);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }

    //解密
    public static String decrypt(String dataString, String privateDecodeKeyString) {
        try {
            PrivateKey privateKey = RSAUtils.loadPrivateKey(privateDecodeKeyString);
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

            byte[] bytes = Base64.getDecoder().decode(dataString);
            byte[] b = cipher.doFinal(bytes);
            return new String(b);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";

    }

    /**
     * 签名字符串
     *
     * @param text       需要签名的字符串
     * @param privateKey 私钥(BASE64编码)
     * @return 签名结果(BASE64编码)
     */
    public static String sign(String text, String privateKey) {
        try {
            PrivateKey privateK = loadPrivateKey(privateKey);
            Signature signature = Signature.getInstance("SHA256WithRSA");
            signature.initSign(privateK);
            signature.update(text.getBytes());
            byte[] result = signature.sign();
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //验签
    public static boolean verify(String textString, String signStr, String publicKeyString) {
        PublicKey key = RSAUtils.loadPublicKey(publicKeyString);
        try {
            Signature signature = Signature.getInstance("SHA256WithRSA");
            signature.initVerify(key);
            signature.update(textString.getBytes());

            boolean success = signature.verify(Base64.getDecoder().decode(signStr));
            System.out.println("验证成功？--" + (success ? "YES" : "NO"));
            return success;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //加载公钥
    public static RSAPublicKey loadPublicKey(String publicKeyString) {
        try {
            byte[] buffer = Base64.getDecoder().decode(publicKeyString);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            return (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    //加载私钥
    public static RSAPrivateKey loadPrivateKey(String priKeyString) {
        try {
            PKCS8EncodedKeySpec priPK = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(priKeyString));
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = factory.generatePrivate(priPK);

            return (RSAPrivateKey) privateKey;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
        //用于验签的公钥
        final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCGCmJL7KLKlk2sHmT5GOlIw9cFqEhwfhTaGqrqDRn1jfCRDh9ppe+mabiCvst6jH5MbCVMVGaqcLyrtzCTAWEsj7GLGvYn0TnpLcxr2w4Df//kGLHpGn8Or90fn4SK+Pku5PX94X2WtA8ospagmcz/+Y0WMoHQl5tRs26bWUXa1QIDAQAB";
        //用于验签的私钥
        final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIYKYkvsosqWTaweZPkY6UjD1wWoSHB+FNoaquoNGfWN8JEOH2ml76ZpuIK+y3qMfkxsJUxUZqpwvKu3MJMBYSyPsYsa9ifROektzGvbDgN//+QYsekafw6v3R+fhIr4+S7k9f3hfZa0DyiylqCZzP/5jRYygdCXm1GzbptZRdrVAgMBAAECgYARlts/S1Yxb3fR1ks5xOMYAVr+Cw82c9UYqdczz3RQnMeswUWt/3BrTgRAY/kfo8APF0HtukWeqByaC/f70nqFxbN4DnLiGsHQRKbFt2dPFV+333M7UIDYgb7Y5fmHmArFZ4ezY+WC24sSsu4+A836d0mfGjSIa03TUH8XI6X5IQJBAMfjcGC2l+TKQLQWODeJEAH2q8SoPsN6BzYUNlPMMJcVoyeqxzy/X/YcLqgdrZD+WrX8g6Y3+RkaRJ/CScDW+RMCQQCrqu0vkEZooNzpbX+o2NJAZL9gzgxXnDe9rH53OjYjbQdD7cacYZ1ZRxehL8/3itPIUy4tZpQbA5e5WL4bBQF3AkAyu914DqA658LIcqNOJTG07eDnBzT29HAEH9kyJ69liY5hsQzktEYs9zY4YV/+XzCy5Cad97L31hz4151UnruVAkBr0zSfh3NyDHg1dj2VBHsrTxyV5VYDQXARhuL4aGvQ3I6PsC3r07RNe0XwTGPIDD7xuK1sft3QCfWmyYK+3eoJAkEAnRxTqRFDbHLejtgLvgjIL52IAURRrliRbN9iyy4t1YqyfOHC7EF/Np11DoVGiBQZrbnPtI7OnNalfIf/l1cTKg==";

        //用于解密的公钥
        final String enPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCJ24IhJQ54nOYQjl49j9lmwUaJJs9RMoyOwfcEmyXrzKE50XyT3IUxYmfB65Zo4PTHb5OndJQnoJfabvHZVeNKj+9Tmi2BXMnQh3BEN2a6HRXBnkySUbLMf9stHrcoOvDsJrZ0PLA1oIZHEoLyKZD/NFqwA0Xng+Rjtf/o14FvIQIDAQAB";
        //用于解密的私钥
        final String enPriKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAInbgiElDnic5hCOXj2P2WbBRokmz1EyjI7B9wSbJevMoTnRfJPchTFiZ8Hrlmjg9Mdvk6d0lCegl9pu8dlV40qP71OaLYFcydCHcEQ3ZrodFcGeTJJRssx/2y0etyg68OwmtnQ8sDWghkcSgvIpkP80WrADReeD5GO1/+jXgW8hAgMBAAECgYBCkMCT+o2zRad9ZREyTqxeBoNlpFzEy1C9egEpszSrWEKdZX7u8rNJtkd9hqE5AS6QwlqcqBkFzXClo56aH/PAjIF/2dAhAhrdvNABrxB2h/PdUkTL5XCck1TNy04jzUgxULW/7BScQ0K68A7LNu7282ZzhIG0tYF0aCBObsLE8QJBANuC/iQIoT4aOrhMDwcHeRajgQrB7TekAw1BmOoXOGqzVOHl08b6Gv/NaYXM9QUwK84thpobjApl9+RTZ83jSm0CQQCgxdX9JVibTSRxKjj3XtxiqHnA6n+9zmiZAcgsV2Uo7bMnqsUPJ0CkgAZ4JA5DIDrni1wDM1O9NCRPH7SiKAcFAkBhaVkUbov3fjZOsNn+WY+fv0E1n+eASJVeHZ0ZTOKpXxmtAYuggj7XA7XvPYwCGGVoIoXX/59+wc9nEKhBErtlAkBbJk7gKuBFjELw9eM+PEXumV4OBeVOk0uyE9SNby8nOTytbKA0qyh3Gy6PxsFfRVKgG96a4erEBl/fjDY5CUCRAkEAkZh2Gl1QEnEO2SR/hNnKI60KpGWzt0JNva2EvUZV8eChK8LUqLktggM3M6BOV0jSxpP6YKM+X3eZeFpgvUO4iA==";


        String orignal = "abcd中文测试加标点符号！@#￥%……&*（+——）（*&~，。，；,,/;lkk;ki;'[p]./,'467646789";
        String signStr = RSAUtils.sign(orignal, privateKey);
        System.out.println("签名值：" + signStr);

        boolean verify = RSAUtils.verify(orignal, signStr, publicKey);
        System.out.println("verify:" + verify);

        String enTxt = RSAUtils.encrypt(orignal, enPublicKey);
        String deTxt = RSAUtils.decrypt(enTxt, enPriKey);
        System.out.println("RSA加密：" + enTxt);
        System.out.println("RSA解密：" + deTxt);
    }

}
