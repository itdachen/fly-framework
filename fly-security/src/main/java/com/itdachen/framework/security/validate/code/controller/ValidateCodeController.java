package com.itdachen.framework.security.validate.code.controller;

import com.itdachen.framework.security.constants.SecurityConstants;
import com.itdachen.framework.security.validate.code.processor.ValidateCodeProcessor;
import com.itdachen.framework.security.validate.code.processor.ValidateCodeProcessorHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Description: 验证码请求接口
 * Created by 王大宸 on 2021-11-27 10:18
 * Created with IntelliJ IDEA.
 */
@RestController
public class ValidateCodeController {

    private final ValidateCodeProcessorHolder validateCodeProcessorHolder;

    public ValidateCodeController(ValidateCodeProcessorHolder validateCodeProcessorHolder) {
        this.validateCodeProcessorHolder = validateCodeProcessorHolder;
    }

    /***
     * ，根据验证码类型不同，调用不同的 {@link ValidateCodeProcessor}接口实现
     *
     * @author 王大宸
     * @date 2021/11/27 10:23
     * @param request
     * @param response
     * @param type
     * @return void
     */
    @GetMapping(SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type)
            throws Exception {
        validateCodeProcessorHolder.findValidateCodeProcessor(type).create(new ServletWebRequest(request, response));
    }

}
