package com.github.itdachen.framework.weixin.wxpay.config;

import com.github.itdachen.framework.weixin.properties.IWeChatPayProperties;
import com.github.itdachen.framework.weixin.wxpay.config.utils.PrivateKeyUtils;
import com.github.itdachen.framework.weixin.properties.autoconfigure.pay.WeChatPayConfigurationProperties;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.Verifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.cert.CertificatesManager;
import com.wechat.pay.contrib.apache.httpclient.exception.HttpCodeException;
import com.wechat.pay.contrib.apache.httpclient.exception.NotFoundException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;

/**
 * Description: 微信支付配置
 * Created by 王大宸 on 2023/04/11 21:28
 * Created with IntelliJ IDEA.
 */
@Configuration
@ConditionalOnProperty(prefix = "weixin.pay", name = "enabled", havingValue = "true")
public class WeChatPayConfigBean {
    private final IWeChatPayProperties wxPayProperties;

    public WeChatPayConfigBean(IWeChatPayProperties wxPayProperties) {
        this.wxPayProperties = wxPayProperties;
    }

    /**
     * 获取签名验证器
     */
    @Bean
    public Verifier getVerifier() throws Exception {
        // 获取商户私钥
        final PrivateKey privateKey = PrivateKeyUtils.getPrivateKeyPath(wxPayProperties.getPrivateKeyPath());
        // 私钥签名对象
        PrivateKeySigner privateKeySigner = new PrivateKeySigner(wxPayProperties.getMchSerialNo(), privateKey);
        // 身份认证对象
        WechatPay2Credentials wechatPay2Credentials = new WechatPay2Credentials(wxPayProperties.getMchId(), privateKeySigner);
        // 获取证书管理器实例
        CertificatesManager certificatesManager = CertificatesManager.getInstance();
        try {
            // 向证书管理器增加需要自动更新平台证书的商户信息
            certificatesManager.putMerchant(wxPayProperties.getMchId(),
                    wechatPay2Credentials,
                    wxPayProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
        } catch (IOException | GeneralSecurityException | HttpCodeException e) {
            e.printStackTrace();
        }
        try {
            return certificatesManager.getVerifier(wxPayProperties.getMchId());
        } catch (NotFoundException e) {
            e.printStackTrace();
            throw new Exception("获取签名验证器失败");
        }
    }

    /**
     * 获取微信支付的远程请求对象
     *
     * @return Http请求对象
     */
    @Bean
    public CloseableHttpClient getWxPayClient() throws Exception {
        // 获取签名验证器
        Verifier verifier = getVerifier();
        // 获取商户私钥
        final PrivateKey privateKey = PrivateKeyUtils.getPrivateKeyPath(wxPayProperties.getPrivateKeyPath());
        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder
                .create()
                .withMerchant(wxPayProperties.getMchId(), wxPayProperties.getMchSerialNo(), privateKey)
                .withValidator(new WechatPay2Validator(verifier));
        return builder.build();
    }


}
