package com.github.itdachen.framework.boot.autoconfigure.oss.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 阿里云存储配置
 * Created by 剑鸣秋朔 on 2023-06-12 22:07
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.oss.ali")
public class OssAliYunAutoconfigureProperties {

    /**
     * 地域节点
     */
    private String endpoint = "oss-cn-chengdu.aliyuncs.com";

    /**
     * 用户 KeyId
     */
    private String accessKeyId = "";

    /**
     * 用户秘钥
     */
    private String accessKeySecret= "";

    /**
     * 存储空间名称
     */
    private String bucket = "fly";


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }
}
