package com.github.itdachen.framework.crypto.hmac;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * 签名及校验工具类
 *
 * @author 王大宸
 * @date 2024-11-04 10:43
 */
public class HmacSHA1Utils {


    private static final String SIGN_ALGORITHM = "HmacSHA1";

    /***
     * 使用 HmacSHA1 算法进行签名
     *
     * @author 王大宸
     * @date 2024/11/4 10:32
     * @param secretKey 密钥
     * @param data      数据
     * @return java.lang.String
     */
    public static String signWithHmacSha1(String secretKey, String data) {
        try {
            SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), SIGN_ALGORITHM);
            Mac mac = Mac.getInstance(SIGN_ALGORITHM);
            mac.init(signingKey);
            return Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /***
     * 验证签名
     *
     * @author 王大宸
     * @date 2024/11/4 10:32
     * @param secretKey 密钥
     * @param data 数据
     * @param hmac 签名
     * @return boolean
     */
    public static boolean verify(String secretKey, String data, String hmac) {
        String calculatedHmac = signWithHmacSha1(secretKey, data);
        return calculatedHmac.equals(hmac);
    }


}
