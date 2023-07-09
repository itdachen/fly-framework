package com.github.itdachen.framework.security.websecurity.management;

import com.github.itdachen.framework.security.websecurity.IFlySecurityCsrf;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Created by 王大宸 on 2023-07-09 13:22
 * Created with IntelliJ IDEA.
 */
@Component
public class FlySecurityCsrf implements IFlySecurityCsrf {

    @Override
    public void csrf(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                // .cors(cors -> cors.configurationSource(corsConfigurationSource())) // 跨越配置
                .headers(header -> header
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                );
    }

}
