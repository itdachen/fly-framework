package com.github.itdachen.framework.boot.autoconfigure.datasource.entity;

/**
 * 数据库连接信息
 *
 * @author 剑鸣秋朔
 * @date 2023-12-30 17:46
 */
public class DataSourceInfo {

    /**
     * 数据库 key，即保存 Map 中的 key
     */
    private String key;

    /**
     * 数据库驱动
     */
    private String driverClassName;

    /**
     * 数据库地址
     */
    private String url;

    /**
     * 数据库用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
