package com.github.itdachen.framework.jjwt.properties;

import com.github.itdachen.framework.jjwt.enums.JwtTokenEnumType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description:
 * Created by 王大宸 on 2023/04/12 22:17
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "jjwt")
public class JJwtProperties {

    /**
     * 生成 token 方式, 默认使用 RSA, 私钥加密, 公钥解密
     */
    private JwtTokenEnumType type = JwtTokenEnumType.RSA;

    /**
     * 签发者
     */
    private String issuer = "com.github.itdachen";

    /**
     * 有效时间, 定义默认有效期为 300 分钟 单位：毫秒
     */
    private long expires = 300 * 60 * 1000L;

    /**
     * 加解密秘钥
     */
    private String secretKey = "tNTaMC1B0Kzdl1q0LKJy7MooRbuwaebzfcS3nGOsPAoSvt3w==";

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
        return expires;
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
