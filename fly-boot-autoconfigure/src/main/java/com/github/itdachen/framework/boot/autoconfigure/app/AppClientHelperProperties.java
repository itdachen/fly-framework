package com.github.itdachen.framework.boot.autoconfigure.app;

import com.github.itdachen.framework.boot.autoconfigure.AppContextHelper;

/**
 * AppProperties
 *
 * @author 王大宸
 * @date 2024-06-19 16:10
 */
public class AppClientHelperProperties {

    /***
     * 获取应用信息
     *
     * @author 王大宸
     * @date 2024/6/19 16:12
     * @return com.github.itdachen.boot.autoconfigure.app.AppInfoProperties
     */
    public AppInfoProperties properties() {
        return AppContextHelper.getBean(AppInfoProperties.class);
    }

    /***
     * 获取平台信息
     *
     * @author 王大宸
     * @date 2024/6/19 16:12
     * @return com.github.itdachen.boot.autoconfigure.app.PlatformInfoProperties
     */
    public PlatformInfoProperties platform() {
        return AppContextHelper.getBean(PlatformInfoProperties.class);
    }


}
