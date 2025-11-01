package com.github.itdachen.framework.boot.security.validate.code.processor;

import com.github.itdachen.framework.boot.security.exception.ValidateCodeException;
import com.github.itdachen.framework.boot.security.validate.code.enums.ValidateCodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Description:
 * Created by 王大宸 on 2021-11-26 22:38
 * Created with IntelliJ IDEA.
 */
//@Component
public class ValidateCodeProcessorHolder {
    private static final Logger logger = LoggerFactory.getLogger(ValidateCodeProcessorHolder.class);

    private final Map<String, ValidateCodeProcessor> validateCodeProcessors;

    public ValidateCodeProcessorHolder(Map<String, ValidateCodeProcessor> validateCodeProcessors) {
        this.validateCodeProcessors = validateCodeProcessors;
    }

    public ValidateCodeProcessor findValidateCodeProcessor(ValidateCodeType type) {
        return findValidateCodeProcessor(type.toString().toLowerCase());
    }

    public ValidateCodeProcessor findValidateCodeProcessor(String type) {
        String name = type.toLowerCase() + ValidateCodeProcessor.class.getSimpleName();
        ValidateCodeProcessor processor = validateCodeProcessors.get(name);
        if (null == processor) {
            logger.error("验证码处理器 " + name + " 不存在！！！");
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }


}
