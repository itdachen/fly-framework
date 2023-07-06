package com.github.itdachen.framework.autoconfigure.properties.security.code;

/**
 * Description:
 * Created by 王大宸 on 2022-10-16 13:40
 * Created with IntelliJ IDEA.
 */
public class ValidateCodeProperties {

    private ImageCodeProperties image = new ImageCodeProperties();

    private SmsCodeProperties sms = new SmsCodeProperties();

    public ImageCodeProperties getImage() {
        return image;
    }

    public void setImage(ImageCodeProperties image) {
        this.image = image;
    }

    public SmsCodeProperties getSms() {
        return sms;
    }

    public void setSms(SmsCodeProperties sms) {
        this.sms = sms;
    }

}
