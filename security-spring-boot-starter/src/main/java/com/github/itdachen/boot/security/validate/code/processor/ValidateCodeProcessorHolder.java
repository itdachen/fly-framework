package com.github.itdachen.boot.security.validate.code.processor;

import com.github.itdachen.boot.security.exception.ValidateCodeException;
import com.github.itdachen.boot.security.validate.code.enums.ValidateCodeType;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description:
 * Created by 王大宸 on 2021-11-26 22:38
 * Created with IntelliJ IDEA.
 */
//@Component
public class ValidateCodeProcessorHolder {

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
        if (processor == null) {
            throw new ValidateCodeException("验证码处理器" + name + "不存在");
        }
        return processor;
    }


}
