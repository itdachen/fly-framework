package com.github.itdachen.framework.file.properties;

import com.github.itdachen.framework.file.enums.OssTypeEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description:
 * Created by 王大宸 on 2023-06-12 22:20
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "oss")
public class OssCloudProperties {

    /**
     * 文件上传类型
     */
    private OssTypeEnum type = OssTypeEnum.LOCAL;

    /**
     * 本地文件上传
     */
    private LocalOssProperties local = new LocalOssProperties();

    /**
     * 阿里云
     */
    private AliYunOssProperties ali = new AliYunOssProperties();


    public OssTypeEnum getType() {
        return type;
    }

    public void setType(OssTypeEnum type) {
        this.type = type;
    }

    public LocalOssProperties getLocal() {
        return local;
    }

    public void setLocal(LocalOssProperties local) {
        this.local = local;
    }

    public AliYunOssProperties getAli() {
        return ali;
    }

    public void setAli(AliYunOssProperties ali) {
        this.ali = ali;
    }
}
