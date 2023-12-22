package com.github.itdachen.framework.jjwt.core;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * Description: jwt token 用户信息
 * Created by 王大宸 on 2023/04/30 14:51
 * Created with IntelliJ IDEA.
 */
public class JwtTokenInfo implements IJwtInfo {

    private String username;
    private String userId;
    private String nickName;
    private String subject;
    private String tenantId;
    private String token;
    private String tokenId;
    private Date expireTime;
    private Map<String, String> otherInfo;


    public JwtTokenInfo(String username, String userId, String nickName) {
        this.username = username;
        this.userId = userId;
        this.nickName = nickName;
        this.tokenId = UUID.randomUUID().toString().replaceAll("-", "");
    }

    public JwtTokenInfo(String username,
                        String userId,
                        String nickName,
                        String tokenId,
                        String subject,
                        Date expireTime,
                        Map<String, String> otherInfo) {
        this.username = username;
        this.userId = userId;
        this.nickName = nickName;
        this.tokenId = tokenId;
        this.subject = subject;
        this.expireTime = expireTime;
        this.otherInfo = otherInfo;
    }

    public JwtTokenInfo(Builder builder) {
        this.username = builder.username;
        this.userId = builder.userId;
        this.nickName = builder.nickName;
        this.subject = builder.subject;
        this.tenantId = builder.tenantId;
        this.tokenId = builder.tokenId;
        this.token = builder.token;
        this.expireTime = builder.expireTime;
        this.otherInfo = builder.otherInfo;
    }

    public static class Builder {
        private String username;
        private String userId;
        private String nickName;
        private String subject;
        private String tenantId;
        private String tokenId = UUID.randomUUID().toString().replaceAll("-", "");
        private String token;
        private Date expireTime;
        private Map<String, String> otherInfo;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder tenantId(String tenantId) {
            this.tenantId = tenantId;
            return this;
        }

        public Builder tokenId(String tokenId) {
            this.tokenId = tokenId;
            return this;
        }

        public Builder token(String token) {
            this.token = token;
            return this;
        }

        public Builder expireTime(Date expireTime) {
            this.expireTime = expireTime;
            return this;
        }

        public Builder otherInfo(Map<String, String> otherInfo) {
            this.otherInfo = otherInfo;
            return this;
        }

        public JwtTokenInfo build() {
            return new JwtTokenInfo(this);
        }
    }

    @Override
    public String getUniqueName() {
        return username;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public String getNickName() {
        return nickName;
    }

    @Override
    public String getTenantId() {
        return tenantId;
    }

    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getTokenId() {
        return tokenId;
    }

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public Date getExpireTime() {
        return expireTime;
    }

    @Override
    public Map<String, String> getOtherInfo() {
        return otherInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JwtTokenInfo jwtInfo = (JwtTokenInfo) o;

        if (username != null ? !username.equals(jwtInfo.username) : jwtInfo.username != null) {
            return false;
        }
        return userId != null ? userId.equals(jwtInfo.userId) : jwtInfo.userId == null;

    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public void setOtherInfo(Map<String, String> otherInfo) {
        this.otherInfo = otherInfo;
    }
}
