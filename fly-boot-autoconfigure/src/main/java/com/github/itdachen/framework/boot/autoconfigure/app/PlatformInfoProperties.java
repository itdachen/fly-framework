package com.github.itdachen.framework.boot.autoconfigure.app;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 平台配置
 *
 * @author 剑鸣秋朔
 * @date 2024/4/3 22:11
 */
@ConfigurationProperties(prefix = "fly.plat")
public class PlatformInfoProperties {

    /**
     * 平台ID
     */
    private String id = "webapp";

    /**
     * 平台名称
     */
    private String title = "webapp";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


}
