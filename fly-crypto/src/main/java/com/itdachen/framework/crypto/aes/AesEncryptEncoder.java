package com.itdachen.framework.crypto.aes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Description: AES 对称加密
 * Created by 王大宸 on 2023/01/28 15:14
 * Created with IntelliJ IDEA.
 */
public class AesEncryptEncoder {
    private static final Logger logger = LoggerFactory.getLogger(AesEncryptEncoder.class);

    //算法名
    private static final String KEY_ALGORITHM = "AES";
    //加解密算法/模式/填充方式
    //可以任意选择，为了方便后面与iOS端的加密解密，采用与其相同的模式与填充方式
    //ECB模式只用密钥即可对数据进行加密解密，CBC模式需要添加一个参数iv
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5PADDING";

    private static final String CHARSET_NAME = "UTF-8";
    private static final String ALGORITHM = "MD5";

    /* 明文后面,在加一个明文后缀 */
    private static final String SUFFIX = "GADGAEWVS";


    /***
     * 对明文密钥进行二次封装，生成最终密钥
     *
     * @author 王大宸
     * @date 2022/1/5 14:42
     * @param secretKey 明文密钥
     * @return javax.crypto.spec.SecretKeySpec
     */
    private static SecretKeySpec setKey(String secretKey) throws NoSuchAlgorithmException {
        // 添加明文后缀
        secretKey = secretKey + SUFFIX;
        //先MD5加密
        MessageDigest md = MessageDigest.getInstance(ALGORITHM);
        byte[] arr = md.digest(secretKey.getBytes());
        return new SecretKeySpec(arr, KEY_ALGORITHM);
    }

    /***
     * 字符串加密
     *
     * @author 王大宸
     * @date 2022/1/5 14:43
     * @param str         需要加密的字符串
     * @param secretKey   明文密钥
     * @return java.lang.String
     */
    public static String encryptStr(String str, String secretKey) {
        try {
            SecretKeySpec md5key = setKey(secretKey);
            //创建密码器
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, md5key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes(CHARSET_NAME)));
        } catch (Exception e) {
            logger.error("AES 加密错误：" + e.toString());
            return null;
        }
    }

    /***
     * 字符串解密
     *
     * @author 王大宸
     * @date 2022/1/5 14:44
     * @param str         加密的字符串
     * @param secretKey   明文密钥
     * @return java.lang.String
     */
    public static String decryptStr(String str, String secretKey) {
        try {
            SecretKeySpec md5key = setKey(secretKey);
            //创建密码器
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, md5key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(str)));
        } catch (Exception e) {
            logger.error("AES 解密错误：" + e.toString());
            return null;
        }
    }

    //测试
//    public static void main(String[] args) {
//        //明文密钥
//        final String secretKey = "www.tpyyes.com";
//        //需要加密的字符串
//        String str = "Hello world!";
//        //加密
//        String encryptedStr = encryptStr(str, secretKey);
//        //解密
//        String decryptedStr = decryptStr(encryptedStr, secretKey);
//        //输出加密后字符串
//        System.out.println(encryptedStr);
//        //输出解密后字符串
//        System.out.println(decryptedStr);
//    }

}
