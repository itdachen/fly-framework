package com.github.itdachen.framework.boot.wechat.applet.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * AppletCode2Session
 *
 * @author 剑鸣秋朔
 * @date 2024/12/1 15:37
 */
public class JsCode2Session implements Serializable {
    private static final Long serialVersionUID = 6829345754240159691L;


    /**
     * 用户唯一标识
     */
    private String openid;

    /**
     * 会话密钥
     */
    private String session_key;

    /**
     * 用户在开放平台的唯一标识符，若当前小程序已绑定到微信开放平台帐号下会返回
     */
    private String unionid;

    public JsCode2Session() {
    }

    public JsCode2Session(String openid, String session_key, String unionid) {
        this.openid = openid;
        this.session_key = session_key;
        this.unionid = unionid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("openid", getOpenid())
                .append("session_key", getSession_key())
                .append("unionid", getUnionid())
                .toString();

    }

}
