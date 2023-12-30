package com.github.itdachen.boot.autoconfigure.datasource.constants;

/**
 * Description: 数据库连接池加密, 默认加密 kay
 * Created by 王大宸 on 2023-09-11 9:40
 * Created with IntelliJ IDEA.
 */
public class DataSourceSecretKey {

    /**
     * 默认加密 key
     */
    public static final String SECRET_KEY = "#!SAGA2020*&@";

    /**
     * hikari 数据源
     */
    public static final String DATASOURCE_TYPE_HIKARI = "com.zaxxer.hikari.HikariDataSource";

    /**
     * druid 数据源
     */
    public static final String DATASOURCE_TYPE_DRUID = "com.alibaba.druid.pool.DruidDataSource";

}
