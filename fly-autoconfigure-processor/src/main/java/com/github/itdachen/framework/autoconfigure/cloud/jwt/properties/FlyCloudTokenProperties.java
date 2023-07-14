package com.github.itdachen.framework.autoconfigure.cloud.jwt.properties;

import com.github.itdachen.framework.autoconfigure.cloud.jwt.enums.JwtTokenEnumType;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: Jwt 配置
 * Created by 王大宸 on 2023-07-06 15:27
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.cloud.auth.token")
public class FlyCloudTokenProperties {

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


}
