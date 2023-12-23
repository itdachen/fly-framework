package com.github.itdachen.framework.context.jwt;

import java.io.Serializable;
import java.util.Map;

/**
 * Description: 生成 token,返回数据类型
 * Created by 王大宸 on 2023/04/30 14:55
 * Created with IntelliJ IDEA.
 */
public class AccessTokenInfo implements Serializable {
    private static final long serialVersionUID = 4609010871941494233L;

    private String access_token;

    private String token_type;

    private String refresh_token;

    private Integer expires_in;

    private String scope;

    /**
     * 登录成功之后, 跳转的路径
     */
    private Map<String, Object> path;

    private Map<String, Object> info;

    public AccessTokenInfo(Builder builder) {
        this.access_token = builder.access_token;
        this.token_type = builder.token_type;
        this.refresh_token = builder.refresh_token;
        this.expires_in = builder.expires_in;
        this.scope = builder.scope;
        this.info = builder.info;
        this.path = builder.path;
    }

    public static class Builder {
        private String access_token;
        private String token_type;
        private String refresh_token;
        private Integer expires_in;
        private String scope;
        private Map<String, Object> path;
        private Map<String, Object> info;

        public Builder access_token(String access_token) {
            this.access_token = access_token;
            return this;
        }

        public Builder token_type(String token_type) {
            this.token_type = token_type;
            return this;
        }

        public Builder refresh_token(String refresh_token) {
            this.refresh_token = refresh_token;
            return this;
        }

        public Builder expires_in(Integer expires_in) {
            this.expires_in = expires_in;
            return this;
        }

        public Builder scope(String scope) {
            this.scope = scope;
            return this;
        }

        public Builder path(Map<String, Object> path) {
            this.path = path;
            return this;
        }

        public Builder info(Map<String, Object> info) {
            this.info = info;
            return this;
        }

        public AccessTokenInfo build() {
            return new AccessTokenInfo(this);
        }
    }

    public AccessTokenInfo(String access_token) {
        this.access_token = access_token;
    }

    public AccessTokenInfo(String access_token,
                           String token_type,
                           String refresh_token,
                           Integer expires_in,
                           String scope,
                           Map<String, Object> info) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.scope = scope;
        this.info = info;
    }

    public AccessTokenInfo(String access_token,
                           String token_type,
                           String refresh_token,
                           Integer expires_in,
                           String scope) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.scope = scope;
    }


    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }

    public Map<String, Object> getPath() {
        return path;
    }

    public void setPath(Map<String, Object> path) {
        this.path = path;
    }


}
