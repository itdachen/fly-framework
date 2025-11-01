package com.github.itdachen.framework.boot.security.validate;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Description: 验证码生成接口
 * Created by 王大宸 on 2021-11-26 22:33
 * Created with IntelliJ IDEA.
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);

}
