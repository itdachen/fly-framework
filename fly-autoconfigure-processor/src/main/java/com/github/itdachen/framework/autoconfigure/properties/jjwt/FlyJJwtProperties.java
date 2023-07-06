package com.github.itdachen.framework.autoconfigure.properties.jjwt;

import com.github.itdachen.framework.autoconfigure.constants.DefaultEncryptKey;
import com.github.itdachen.framework.autoconfigure.properties.jjwt.enums.JwtTokenEnumType;

/**
 * Description: Jwt 配置
 * Created by 王大宸 on 2023-07-06 15:27
 * Created with IntelliJ IDEA.
 */
public class FlyJJwtProperties {

    /**
     * 是否加密
     */
    private Boolean encrypt = false;

    /**
     * 加解密秘钥
     */
    private String secretKey = DefaultEncryptKey.AES_KEY;

    /**
     * 生成 token 方式, 默认使用 RSA, 私钥加密, 公钥解密
     */
    private JwtTokenEnumType type = JwtTokenEnumType.RSA;

    /**
     * 签发者
     */
    private String issuer = "com.github.itdachen";

    /**
     * 有效时间, 定义默认有效期为 300 分钟 单位：分钟
     */
    private long expires = 18000;

    public Boolean getEncrypt() {
        return encrypt;
    }

    public void setEncrypt(Boolean encrypt) {
        this.encrypt = encrypt;
    }

    public JwtTokenEnumType getType() {
        return type;
    }

    public void setType(JwtTokenEnumType type) {
        this.type = type;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public long getExpires() {
        return expires * 1000;
    }

    public void setExpires(long expires) {
        this.expires = expires;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }


}
