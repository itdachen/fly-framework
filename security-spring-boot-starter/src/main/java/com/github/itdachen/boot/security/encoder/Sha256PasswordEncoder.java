package com.github.itdachen.boot.security.encoder;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

/***
 * 自定义加密配置
 *
 * @author 王大宸
 * @date 2022/10/16 13:54
 */
public class Sha256PasswordEncoder implements PasswordEncoder {
    private static final Logger logger = LoggerFactory.getLogger(Sha256PasswordEncoder.class);

    /***
     * 加密
     *
     * @author 王大宸
     * @date 2024/5/4 21:38
     * @param charSequence charSequence
     * @return java.lang.String
     */
    @Override
    public String encode(CharSequence charSequence) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(charSequence.toString().getBytes(StandardCharsets.UTF_8));
            return new String(Base64.encodeBase64(digest));
        } catch (Exception e) {
            logger.error("密码加密失败...");
            return null;
        }
    }

    /***
     * 密码校验
     *
     * @author 王大宸
     * @date 2024/5/4 21:29
     * @param charSequence charSequence
     * @param s s
     * @return boolean
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return this.encode(charSequence.toString()).equals(s);
    }

}
