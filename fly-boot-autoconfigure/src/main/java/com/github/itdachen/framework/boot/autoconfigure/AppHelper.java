package com.github.itdachen.framework.boot.autoconfigure;

import com.github.itdachen.framework.boot.autoconfigure.app.AppClientHelperProperties;
import com.github.itdachen.framework.boot.autoconfigure.cloud.CloudHelperProperties;
import com.github.itdachen.framework.boot.autoconfigure.datasource.DataSourceHelperProperties;
import com.github.itdachen.framework.boot.autoconfigure.jwt.JwtAppClientHelperProperties;
import com.github.itdachen.framework.boot.autoconfigure.oss.OssHelperProperties;
import com.github.itdachen.framework.boot.autoconfigure.security.SecurityHelperProperties;
import com.github.itdachen.framework.boot.autoconfigure.wechat.WeChatHelperProperties;

/**
 * 获取配置信息
 *
 * @author 王大宸
 * @date 2024-06-03 15:49
 */
public class AppHelper {

    /***
     * 应用信息
     *
     * @author 王大宸
     * @date 2024/6/19 16:14
     * @return com.github.itdachen.boot.autoconfigure.app.AppClientProperties
     */
    public static AppClientHelperProperties app() {
        return new AppClientHelperProperties();
    }

    /***
     * 数据源
     *
     * @author 王大宸
     * @date 2024/6/19 17:02
     * @return com.github.itdachen.boot.autoconfigure.datasource.DataSourceHelperProperties
     */
    public static DataSourceHelperProperties dataSource() {
        return new DataSourceHelperProperties();
    }

    /***
     * jwt 配置
     *
     * @author 王大宸
     * @date 2024/6/19 17:03
     * @return com.github.itdachen.boot.autoconfigure.jwt.JwtAppClientHelperProperties
     */
    public static JwtAppClientHelperProperties jwt() {
        return new JwtAppClientHelperProperties();
    }


    /***
     * 微服务配置
     *
     * @author 王大宸
     * @date 2024/6/19 16:42
     * @return com.github.itdachen.boot.autoconfigure.cloud.CloudHelperProperties
     */
    public static CloudHelperProperties cloud() {
        return new CloudHelperProperties();
    }

    /***
     * 文件上传配置
     *
     * @author 王大宸
     * @date 2024/6/19 16:42
     * @return com.github.itdachen.boot.autoconfigure.oss.OssHelperProperties
     */
    public static OssHelperProperties oss() {
        return new OssHelperProperties();
    }

    /***
     * Spring Security 安全认证配置
     *
     * @author 王大宸
     * @date 2024/6/19 16:44
     * @return com.github.itdachen.boot.autoconfigure.security.SecurityHelperProperties
     */
    public static SecurityHelperProperties security() {
        return new SecurityHelperProperties();
    }

    /***
     * 微信配置
     *
     * @author 王大宸
     * @date 2024/11/21 11:23
     * @return com.github.itdachen.boot.autoconfigure.wechat.WeChatHelperProperties
     */
    public static WeChatHelperProperties weChat() {
        return new WeChatHelperProperties();
    }


}
