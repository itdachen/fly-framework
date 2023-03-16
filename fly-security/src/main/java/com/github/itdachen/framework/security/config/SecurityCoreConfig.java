package com.github.itdachen.framework.security.config;

import com.github.itdachen.framework.security.properties.SecurityBrowserProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Description: 项目配置文件
 * Created by 王大宸 on 2022-10-16 13:43
 * Created with IntelliJ IDEA.
 */
@Configuration
@EnableConfigurationProperties(SecurityBrowserProperties.class)
public class SecurityCoreConfig {

    /***
     * 指定项目加密策略
     *
     * @author 王大宸
     * @date 2022/10/16 13:55
     * @return org.springframework.security.crypto.password.PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /***
     * 多种加密解析器
     * 数据库存放密码形式: {id}密码
     * 例如: bcrypt 加密形式: {bcrypt}$2a$10$W0iZ.jnBsq37Z968RLIvXu9H6d31lAXQG3PbCrL/dOiuLrLZ9qCIC
     *      SHA-256 加密形式: {SHA-256}Bsq37Z968RLIvXu9H6d31lAXQG3PbCrL/dOiuLrLZ9qCIC
     * @author 王大宸
     * @date 2022/9/23 9:49
     * @return org.springframework.security.crypto.password.PasswordEncoder
     */
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        // 默认编码算法的 Id
//        String idForEncode = "bcrypt";
//        // 要支持的多种编码器
//        Map<String, PasswordEncoder> encoders = Map.of(
//                idForEncode, new BCryptPasswordEncoder(),
//                "SHA-1", new MessageDigestPasswordEncoder("SHA-1"),
//                "SHA-256", new Sha256PasswordEncoder()
//        );
//        return new DelegatingPasswordEncoder(idForEncode, encoders);
//    }

}
