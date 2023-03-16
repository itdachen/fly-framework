package com.github.itdachen.framework.security.encoder;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description: MD5 加密
 * Created by 王大宸 on 2023/02/01 9:10
 * Created with IntelliJ IDEA.
 */
public class Md5PasswordEncoder implements PasswordEncoder {

    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f'};


    @Override
    public String encode(CharSequence rawPassword) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(rawPassword.toString().getBytes());
            return byteArray2HexString(messageDigest.digest()).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return this.encode(rawPassword.toString()).equals(encodedPassword);
    }

    private static String byteArray2HexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(HEX_DIGITS[(b & 0xf0) >> 4]).append(HEX_DIGITS[(b & 0x0f)]);
        }
        return sb.toString();
    }


//    public static void main(String[] args) {
//        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
//        System.err.println(encoder.encode("123456"));
//    }

}
