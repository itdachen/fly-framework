package com.github.itdachen.boot.security;

import com.github.itdachen.boot.autoconfigure.security.properties.code.SecurityImageCodeProperties;
import com.github.itdachen.boot.autoconfigure.security.properties.code.SecuritySmsCodeProperties;
import com.github.itdachen.boot.security.validate.ValidateCodeGenerator;
import com.github.itdachen.boot.security.validate.code.image.ImageCodeGenerator;
import com.github.itdachen.boot.security.validate.code.sms.DefaultSmsCodeSender;
import com.github.itdachen.boot.security.validate.code.sms.SmsCodeGenerator;
import com.github.itdachen.boot.security.validate.code.sms.SmsCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 验证码 bean 配置
 * Created by 王大宸 on 2021-11-27 10:31
 * Created with IntelliJ IDEA.
 */
@Configuration
public class ValidateCodeAutoConfiguration {

    private final SecurityImageCodeProperties imageCodeProperties;
    private final SecuritySmsCodeProperties smsCodeProperties;

    public ValidateCodeAutoConfiguration(SecurityImageCodeProperties imageCodeProperties,
                                         SecuritySmsCodeProperties smsCodeProperties) {
        this.imageCodeProperties = imageCodeProperties;
        this.smsCodeProperties = smsCodeProperties;
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
    public SmsCodeGenerator smsCodeGenerator() {
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




}