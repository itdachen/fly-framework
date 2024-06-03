package com.github.itdachen.boot.autoconfigure;

import com.github.itdachen.boot.autoconfigure.app.AppInfoProperties;
import com.github.itdachen.boot.autoconfigure.app.PlatformInfoProperties;

/**
 * 应用信息帮手
 *
 * @author 王大宸
 * @date 2024-06-03 15:49
 */
public class AppHelper {

    /***
     * 获取应用信息
     *
     * @author 王大宸
     * @date 2024/6/3 15:52
     * @return com.github.itdachen.boot.autoconfigure.app.AppInfoProperties
     */
    public static AppInfoProperties appInfo() {
        return AppContextHelper.getBean(AppInfoProperties.class);
    }

    /***
     * 获取平台信息
     *
     * @author 王大宸
     * @date 2024/6/3 15:52
     * @return com.github.itdachen.boot.autoconfigure.app.PlatformInfoProperties
     */
    public static PlatformInfoProperties platformInfo() {
        return AppContextHelper.getBean(PlatformInfoProperties.class);
    }


}
