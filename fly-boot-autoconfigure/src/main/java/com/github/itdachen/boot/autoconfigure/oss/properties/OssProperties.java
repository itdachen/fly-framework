package com.github.itdachen.boot.autoconfigure.oss.properties;

import com.github.itdachen.boot.autoconfigure.oss.enums.OssTypeEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Description: 文件上传配置
 * Created by 王大宸 on 2023-07-06 15:26
 * Created with IntelliJ IDEA.
 */
@ConfigurationProperties(prefix = "fly.oss")
public class OssProperties {

    /**
     * 文件上传类型
     */
    private OssTypeEnum type = OssTypeEnum.LOCAL;


    public OssTypeEnum getType() {
        return type;
    }

    public void setType(OssTypeEnum type) {
        this.type = type;
    }

}
