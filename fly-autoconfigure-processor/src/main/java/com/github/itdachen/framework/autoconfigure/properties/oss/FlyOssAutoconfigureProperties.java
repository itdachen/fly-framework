package com.github.itdachen.framework.autoconfigure.properties.oss;

import com.github.itdachen.framework.autoconfigure.properties.oss.enums.OssTypeEnum;
import com.github.itdachen.framework.autoconfigure.properties.oss.properties.AliYunOssAutoconfigureProperties;
import com.github.itdachen.framework.autoconfigure.properties.oss.properties.FlyLocalOssAutoconfigureProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 文件上传配置
 * Created by 王大宸 on 2023-07-06 15:26
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.oss")
public class FlyOssAutoconfigureProperties {

    /**
     * 文件上传类型
     */
    private OssTypeEnum type = OssTypeEnum.LOCAL;

    /**
     * 本地文件上传
     */
    private FlyLocalOssAutoconfigureProperties local = new FlyLocalOssAutoconfigureProperties();

    /**
     * 阿里云
     */
    private AliYunOssAutoconfigureProperties ali = new AliYunOssAutoconfigureProperties();


    public OssTypeEnum getType() {
        return type;
    }

    public void setType(OssTypeEnum type) {
        this.type = type;
    }

    public FlyLocalOssAutoconfigureProperties getLocal() {
        return local;
    }

    public void setLocal(FlyLocalOssAutoconfigureProperties local) {
        this.local = local;
    }

    public AliYunOssAutoconfigureProperties getAli() {
        return ali;
    }

    public void setAli(AliYunOssAutoconfigureProperties ali) {
        this.ali = ali;
    }
}
