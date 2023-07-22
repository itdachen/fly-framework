package com.github.itdachen.framework.jwt.utils;

import com.github.itdachen.framework.jwt.constants.EncryptionType;
import com.github.itdachen.framework.jwt.constants.RsaKeyConstant;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 公钥/私钥加密处理
 * Created by 王大宸 on 2022-06-29 10:19
 * Created with IntelliJ IDEA.
 */
public class RsaKeyHelper {

    /**
     * 获取公钥
     *
     * @param filename
     * @throws Exception
     */
    public PublicKey getPublicKey(String filename) throws Exception {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        DataInputStream dis = new DataInputStream(resourceAsStream);
        byte[] keyBytes = new byte[resourceAsStream.available()];
        dis.readFully(keyBytes);
        dis.close();
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(EncryptionType.RSA);
        return kf.generatePublic(spec);
    }

    /**
     * 获取密钥
     *
     * @param filename
     * @return
     * @throws Exception
     */
    public PrivateKey getPrivateKey(String filename) throws Exception {
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream(filename);
        DataInputStream dis = new DataInputStream(resourceAsStream);
        byte[] keyBytes = new byte[resourceAsStream.available()];
        dis.readFully(keyBytes);
        dis.close();
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance(EncryptionType.RSA);
        return kf.generatePrivate(spec);
    }

    /**
     * 获取公钥
     *
     * @param publicKey
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(byte[] publicKey) throws Exception {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKey);
        KeyFactory kf = KeyFactory.getInstance(EncryptionType.RSA);
        return kf.generatePublic(spec);
    }

    /**
     * 获取密钥
     *
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(byte[] privateKey) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKey);
        KeyFactory kf = KeyFactory.getInstance(EncryptionType.RSA);
        return kf.generatePrivate(spec);
    }

    /**
     * 生存 rsa 公钥和密钥
     *
     * @param publicKeyFilename
     * @param privateKeyFilename
     * @param password
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public void generateKey(String publicKeyFilename, String privateKeyFilename, String password) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EncryptionType.RSA);
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        FileOutputStream fos = new FileOutputStream(publicKeyFilename);
        fos.write(publicKeyBytes);
        fos.close();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        fos = new FileOutputStream(privateKeyFilename);
        fos.write(privateKeyBytes);
        fos.close();
    }

    /**
     * 生存 rsa 公钥
     *
     * @param password
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static byte[] generatePublicKey(String password) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EncryptionType.RSA);
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        return keyPair.getPublic().getEncoded();
    }

    /**
     * 生存 rsa 私钥
     *
     * @param password
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static byte[] generatePrivateKey(String password) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EncryptionType.RSA);
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        return keyPair.getPrivate().getEncoded();
    }

    public static Map<String, byte[]> generateKey(String password) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EncryptionType.RSA);
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        Map<String, byte[]> map = new HashMap<String, byte[]>();
        map.put(RsaKeyConstant.PUB_KEY, publicKeyBytes);
        map.put(RsaKeyConstant.PRI_KEY, privateKeyBytes);
        return map;
    }

    public static String toHexString(byte[] b) {
        return Base64.getEncoder().encodeToString(b);
    }

    public static byte[] toBytes(String s) {
        return Base64.getDecoder().decode(s);
    }

//    public static void main(String[] args) throws NoSuchAlgorithmException {
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(EncryptionType.RSA);
//        SecureRandom secureRandom = new SecureRandom("123".getBytes());
//        keyPairGenerator.initialize(1024, secureRandom);
//        KeyPair keyPair = keyPairGenerator.genKeyPair();
//        System.out.println(keyPair.getPublic().getEncoded());
//    }


}
