package com.github.itdachen.framework.autoconfigure.properties.oss;

import com.github.itdachen.framework.autoconfigure.constants.DefaultEncryptKey;
import com.github.itdachen.framework.autoconfigure.properties.oss.enums.OssTypeEnum;
import com.github.itdachen.framework.autoconfigure.properties.oss.properties.AliYunOssProperties;
import com.github.itdachen.framework.autoconfigure.properties.oss.properties.LocalOssProperties;

/**
 * Description: 文件上传配置
 * Created by 王大宸 on 2023-07-06 15:26
 * Created with IntelliJ IDEA.
 */
public class FlyOssProperties {

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
