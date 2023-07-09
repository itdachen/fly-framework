package com.github.itdachen.framework.security;

import com.github.itdachen.framework.security.rememberme.CustomJdbcPersistentTokenRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Description: 密码加密模式, 这里不配置, 交给引入方配置
 * Created by 王大宸 on 2022-10-16 13:43
 * Created with IntelliJ IDEA.
 */
@Configuration
public class FlyWebSecurityConfiguration {
    private final DataSource dataSource;

    public FlyWebSecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /***
     * 记住我 持久化指定保存 session 的方法
     *
     * @author 王大宸
     * @date 2022/9/23 16:56
     * @return org.springframework.security.web.authentication.rememberme.PersistentTokenRepository
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        /* 原来的 JDBC 记住我持久化 */
//        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        /* 自定义记住我持久化 */
        final CustomJdbcPersistentTokenRepository tokenRepository = new CustomJdbcPersistentTokenRepository();

        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }


    //    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

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
