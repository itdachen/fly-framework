package com.itdachen.framework.security.constants;

/**
 * Description: 安全认证常量
 * Created by 王大宸 on 2022-09-23 10:06
 * Created with IntelliJ IDEA.
 */
public class SecurityConstants {

    /**
     * 默认的处理验证码的url前缀
     */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
    /**
     * 当请求需要身份认证时，默认跳转的url
     */
    public static final String DEFAULT_UN_AUTHENTICATION_URL = "/authentication/require";
    /**
     * 默认的用户名密码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";
    /**
     * 默认的手机验证码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";
    /**
     * 默认的OPENID登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_OPENID = "/authentication/openid";
    /**
     * 账号绑定
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_BIND = "/authentication/binding";
    /**
     * 微信小程序 code 登录
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_APPLET = "/authentication/applet";

    /**
     * 微信小程序 code 注册
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_APPLET_REGISTER = "/authentication/applet/register";

    /**
     * 默认登录页面
     */
    public static final String DEFAULT_LOGIN_PAGE_URL = "/signIn";
    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_IMAGE = "imageCode";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
    /**
     * openid参数名
     */
    public static final String DEFAULT_PARAMETER_NAME_OPENID = "openId";
    /**
     * code 参数名
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE = "code";
    /**
     * providerId参数名
     */
    public static final String DEFAULT_PARAMETER_NAME_PROVIDER_ID = "providerId";

    /**
     * 登录账号参数名
     */
    public static final String DEFAULT_PARAMETER_NAME_USERNAME = "username";
    /**
     * 登录密码参数名
     */
    public static final String DEFAULT_PARAMETER_NAME_PASSWORD = "password";

    /**
     * session失效默认的跳转地址
     */
    public static final String DEFAULT_SESSION_INVALID_URL = "/session/invalid";

    /**
     * 登录账号参数名
     */
    public static final String DEFAULT_PARAMETER_NICK_NAME = "nickname";

    /**
     * 登录账号参数名
     */
    public static final String DEFAULT_PARAMETER_AVATAR = "avatar";

}
