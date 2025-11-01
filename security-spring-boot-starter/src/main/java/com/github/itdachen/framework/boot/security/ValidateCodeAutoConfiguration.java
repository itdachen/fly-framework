package com.github.itdachen.framework.boot.security;

import com.github.itdachen.framework.boot.autoconfigure.security.properties.code.SecurityImageCodeProperties;
import com.github.itdachen.framework.boot.autoconfigure.security.properties.code.SecuritySmsCodeProperties;
import com.github.itdachen.framework.boot.security.handler.AuthenticationFailureHandler;
import com.github.itdachen.framework.boot.security.validate.ValidateCodeGenerator;
import com.github.itdachen.framework.boot.security.validate.code.filter.DefaultImageAbstractValidateCodeGeneratingFilter;
import com.github.itdachen.framework.boot.security.validate.code.filter.DefaultSmsAbstractValidateCodeGeneratingFilter;
import com.github.itdachen.framework.boot.security.validate.code.filter.ValidateCodeFilter;
import com.github.itdachen.framework.boot.security.validate.code.image.ImageCodeGenerator;
import com.github.itdachen.framework.boot.security.validate.code.image.ImageCodeProcessor;
import com.github.itdachen.framework.boot.security.validate.code.processor.ValidateCodeProcessor;
import com.github.itdachen.framework.boot.security.validate.code.processor.ValidateCodeProcessorHolder;
import com.github.itdachen.framework.boot.security.validate.code.repository.ValidateCodeRepository;
import com.github.itdachen.framework.boot.security.validate.code.repository.impl.SessionValidateCodeRepository;
import com.github.itdachen.framework.boot.security.validate.code.sms.DefaultSmsCodeSender;
import com.github.itdachen.framework.boot.security.validate.code.sms.SmsCodeGenerator;
import com.github.itdachen.framework.boot.security.validate.code.sms.SmsCodeProcessor;
import com.github.itdachen.framework.boot.security.validate.code.sms.SmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 验证码 bean 配置
 * Created by 王大宸 on 2021-11-27 10:31
 * Created with IntelliJ IDEA.
 */
@Configuration
public class ValidateCodeAutoConfiguration {
    private static final String IMAGE_VALIDATE_CODE_PROCESSOR = "imageValidateCodeProcessor";
    private static final String SMS_VALIDATE_CODE_PROCESSOR = "smsValidateCodeProcessor";

    private final SecurityImageCodeProperties imageCodeProperties;
    private final SecuritySmsCodeProperties smsCodeProperties;
    private final AuthenticationFailureHandler authenticationFailureHandler;
    private final Environment env;

    public ValidateCodeAutoConfiguration(SecurityImageCodeProperties imageCodeProperties,
                                         SecuritySmsCodeProperties smsCodeProperties,
                                         AuthenticationFailureHandler authenticationFailureHandler,
                                         Environment env) {
        this.imageCodeProperties = imageCodeProperties;
        this.smsCodeProperties = smsCodeProperties;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.env = env;
    }

    /***
     * 图片验证码图片生成器
     *
     * ConditionalOnMissingBean 在 Spring 启动的时候,会先在容器里面找一下是否有一个 imageValidateCodeGenerator 的存在
     * 就好比说,在 demo 模块下,声明了一个名字为 imageCodeGenerator 的Bean,系统默认使用 demo 里面的 Bean
     * 如果已经存在,就不会使用现在配置的这个 Bean ,而是使用找到的那个 Bean
     * 以增量的方式去适应变化
     *
     * @author 王大宸
     * @date 2021/11/27 10:32
     * @return com.github.itdachen.boot.security.validate.ValidateCodeGenerator
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(imageCodeProperties);
        return codeGenerator;
    }

    /***
     * 短信验证码生成
     *
     * @author 王大宸
     * @date 2023/12/23 1:29
     * @return com.github.itdachen.boot.security.validate.code.sms.SmsCodeGenerator
     */
    @Bean
    @ConditionalOnMissingBean(name = "smsValidateCodeGenerator")
    public ValidateCodeGenerator smsValidateCodeGenerator() {
        return new SmsCodeGenerator(smsCodeProperties);
    }

    /***
     * 短信验证码发送器(需要调用方自行实现发送验证码的调用接口)
     * 这里写的是接口的类名,在系统中,找到了 SmsCodeSender 接口的实现,就不会走我们默认设计的 send 方法
     *
     * @author 王大宸
     * @date 2021/11/27 10:33
     * @return com.github.itdachen.boot.security.validate.code.sms.SmsCodeSender
     */
    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender() {
        return new DefaultSmsCodeSender();
    }

    /***
     * 浏览器验证码处理器
     *
     * @author 王大宸
     * @date 2024/11/27 16:54
     * @return com.github.itdachen.boot.security.validate.code.repository.ValidateCodeRepository
     */
    @Bean
    @ConditionalOnMissingBean(ValidateCodeRepository.class)
    public ValidateCodeRepository sessionValidateCodeRepository() {
        return new SessionValidateCodeRepository();
    }

    /***
     * 图片验证码处理器
     *
     * @author 王大宸
     * @date 2024/11/27 16:53
     * @return com.github.itdachen.boot.security.validate.code.processor.ValidateCodeProcessor
     */
    @Bean(IMAGE_VALIDATE_CODE_PROCESSOR)
    public ValidateCodeProcessor imageValidateCodeProcessor() {
        return new ImageCodeProcessor();
    }

    /***
     * 短信验证码处理器
     *
     * @author 王大宸
     * @date 2024/11/27 16:53
     * @return com.github.itdachen.boot.security.validate.code.processor.ValidateCodeProcessor
     */
    @Bean(SMS_VALIDATE_CODE_PROCESSOR)
    public ValidateCodeProcessor smsValidateCodeProcessor() {
        return new SmsCodeProcessor();
    }

    /***
     * 获取验证码处理
     *
     * @author 王大宸
     * @date 2024/11/27 16:54
     * @return com.github.itdachen.boot.security.validate.code.processor.ValidateCodeProcessorHolder
     */
    @Bean("validateCodeProcessorHolder")
    public ValidateCodeProcessorHolder validateCodeProcessorHolder() {
        Map<String, ValidateCodeProcessor> validateCodeProcessors = new HashMap<>();
        validateCodeProcessors.put(IMAGE_VALIDATE_CODE_PROCESSOR, imageValidateCodeProcessor());
        validateCodeProcessors.put(SMS_VALIDATE_CODE_PROCESSOR, smsValidateCodeProcessor());
        return new ValidateCodeProcessorHolder(validateCodeProcessors);
    }

    /***
     * 验证码过滤器
     *
     * @author 王大宸
     * @date 2024/11/27 16:53
     * @return com.github.itdachen.boot.security.validate.code.filter.ValidateCodeFilter
     */
    @Bean("validateCodeFilter")
    public ValidateCodeFilter validateCodeFilter() {
        return new ValidateCodeFilter(authenticationFailureHandler,
                imageCodeProperties,
                smsCodeProperties,
                validateCodeProcessorHolder(),
                env);
    }

    /***
     * 获取图形验证码
     *
     * @author 王大宸
     * @date 2024/11/28 16:05
     * @return com.github.itdachen.boot.security.validate.code.filter.DefaultImageAbstractValidateCodeGeneratingFilter
     */
    @Bean("imageValidateCodeGeneratingFilter")
    public DefaultImageAbstractValidateCodeGeneratingFilter imageAbstractValidateCodeGeneratingFilter() {
        return new DefaultImageAbstractValidateCodeGeneratingFilter(validateCodeProcessorHolder());
    }

    /***
     * 获取短信验证码
     *
     * @author 王大宸
     * @date 2024/11/28 16:06
     * @return com.github.itdachen.boot.security.validate.code.filter.DefaultSmsAbstractValidateCodeGeneratingFilter
     */
    @Bean("smsValidateCodeGeneratingFilter")
    public DefaultSmsAbstractValidateCodeGeneratingFilter smsAbstractValidateCodeGeneratingFilter() {
        return new DefaultSmsAbstractValidateCodeGeneratingFilter(validateCodeProcessorHolder());
    }

}
