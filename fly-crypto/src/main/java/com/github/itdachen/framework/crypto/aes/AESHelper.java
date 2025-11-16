package com.github.itdachen.framework.crypto.aes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Description:
 * Created by 剑鸣秋朔 on 2023/05/08 9:51
 * Created with IntelliJ IDEA.
 */
public class AESHelper {

    private static final String charset = "UTF-8";

    public static String encryptString(String content, String key, String iv){
        try {
            byte[] contentBytes = content.getBytes(charset);
            byte[] keyBytes = key.getBytes(charset);

            byte[] encryptData = cipherAction(contentBytes,keyBytes,iv,Cipher.ENCRYPT_MODE);

            byte[] base64Bytes = Base64.getEncoder().encode(encryptData);
            return new String(base64Bytes,charset);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    public static String decryptString(String encryptString, String key, String iv){
        try {
            byte[] decryptBytes = Base64.getDecoder().decode(encryptString);
            byte[] keyBytes = key.getBytes(charset);

            byte[] decryptData = cipherAction(decryptBytes,keyBytes,iv,Cipher.DECRYPT_MODE);
            return new String(decryptData,charset);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "";

    }


    private static byte[] cipherAction(byte[] contentBytes, byte[] keyBytes, String iv, int mode) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
            byte[] initParam = iv.getBytes(charset);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(initParam);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(mode, secretKey, ivParameterSpec);

            return cipher.doFinal(contentBytes);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String orignal = "abcd中文测试加标点符号！@#￥%……&*（+——）（*&~，。，；,,/;lkk;ki;'[p]./,'467646789";
        final String aespublicKey = "1234567887654321";
        final String aespublicIv = "8888777766665544";
        String aesEn = AESHelper.encryptString(orignal, aespublicKey, aespublicIv);
        String aesDe = AESHelper.decryptString(aesEn, aespublicKey, aespublicIv);
        System.out.println("AES密文：" + aesEn);
        System.out.println("AES解密：" + aesDe);
    }

}
