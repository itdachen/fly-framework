package com.itdachen.framework.security.validate.code.processor;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * Description:
 * Created by 王大宸 on 2021-11-26 22:37
 * Created with IntelliJ IDEA.
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /***
     * 创建校验码
     *
     * @author 王大宸
     * @date 2021/11/26 22:38
     * @param request
     * @return void
     */
    void create(ServletWebRequest request) throws Exception;

    /***
     * 校验验证码
     *
     * @author 王大宸
     * @date 2021/11/26 22:38
     * @param servletWebRequest
     * @return void
     */
    void validate(ServletWebRequest servletWebRequest);


}
