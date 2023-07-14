package com.github.itdachen.framework.cloud.jwt.crypto.key;

/**
 * Description: 私钥/公钥对象
 * Created by 王大宸 on 2023/04/30 14:55
 * Created with IntelliJ IDEA.
 */
public class JwtSecretKey {

    /**
     * 私钥
     */
    private String privateKey;

    /**
     * 公钥
     */
    private String publicKey;

    public JwtSecretKey() {
    }

    public JwtSecretKey(String privateKey, String publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }


    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

}
