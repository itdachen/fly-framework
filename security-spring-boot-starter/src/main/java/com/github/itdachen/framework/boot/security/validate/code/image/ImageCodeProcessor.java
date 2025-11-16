package com.github.itdachen.framework.boot.security.validate.code.image;

import com.github.itdachen.framework.boot.security.validate.code.processor.impl.AbstractValidateCodeProcessor;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * Description: 图片验证码处理器
 * Created by 剑鸣秋朔 on 2021-11-26 22:36
 * Created with IntelliJ IDEA.
 */
//@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor  extends AbstractValidateCodeProcessor<ImageCode> {

    /***
     * 发送图形验证码，将其写到响应中
     *
     * @author 剑鸣秋朔
     * @date 2021/11/26 22:37
     * @param request
     * @param imageCode
     * @return void
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }
}
