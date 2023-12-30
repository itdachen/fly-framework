package com.github.itdachen.boot.datasource.dynamic;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.itdachen.boot.autoconfigure.datasource.entity.DataSourceInfo;
import com.github.itdachen.boot.datasource.context.DataSourceContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 实现动态数据源，根据 AbstractRoutingDataSource 路由到不同数据源中
 *
 * @author 王大宸
 * @date 2023-12-30 17:33
 */
public class DynamicDataSourceRouting extends AbstractRoutingDataSource {
    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRouting.class);

    private final Map<Object, Object> targetDataSourceMap;

    /***
     * DynamicDataSourceRouting
     *
     * @author 王大宸
     * @date 2023/12/30 19:04
     * @param defaultDataSource 默认数据源
     * @param targetDataSources 所有数据源
     * @return
     */
    public DynamicDataSourceRouting(DataSource defaultDataSource,
                                    Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultDataSource);
        super.setTargetDataSources(targetDataSources);
        this.targetDataSourceMap = targetDataSources;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }

    /***
     * 添加数据源信息
     *
     * @author 王大宸
     * @date 2023/12/30 19:37
     * @param dataSources 数据源实体集合
     * @return java.lang.Boolean 返回添加结果
     */
    public Boolean createDataSource(List<DataSourceInfo> dataSources) {
        try {
            if (null == dataSources || dataSources.isEmpty()) {
                return Boolean.FALSE;
            }

            for (DataSourceInfo dataSourceInfo : dataSources) {
                //校验数据库是否可以连接
                Class.forName(dataSourceInfo.getDriverClassName());
                DriverManager.getConnection(dataSourceInfo.getUrl(), dataSourceInfo.getUsername(), dataSourceInfo.getPassword());
                //定义数据源
                DruidDataSource dataSource = new DruidDataSource();

                dataSource.setName(dataSourceInfo.getKey());
                dataSource.setUrl(dataSourceInfo.getUrl());
                dataSource.setUsername(dataSourceInfo.getUsername());
                dataSource.setPassword(dataSourceInfo.getPassword());
                dataSource.setDriverClassName(dataSourceInfo.getDriverClassName());

                //    BeanUtils.copyProperties(dataSourceInfo, dataSource);

                //申请连接时执行validationQuery检测连接是否有效，这里建议配置为TRUE，防止取到的连接不可用
                dataSource.setTestOnBorrow(true);
                //建议配置为true，不影响性能，并且保证安全性。
                //申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
                dataSource.setTestWhileIdle(true);
                //用来检测连接是否有效的sql，要求是一个查询语句。
                dataSource.setValidationQuery("SELECT 1 FROM DUAL");
                dataSource.init();
                this.targetDataSourceMap.put(dataSourceInfo.getKey(), dataSource);
            }
            super.setTargetDataSources(this.targetDataSourceMap);
            // 将TargetDataSources中的连接信息放入resolvedDataSources管理
            super.afterPropertiesSet();
            return Boolean.TRUE;

        } catch (ClassNotFoundException | SQLException e) {
            logger.error("---程序报错---:{}", e.getMessage());
            return Boolean.FALSE;
        }
    }

    /**
     * 校验数据源是否存在
     *
     * @param key 数据源保存的key
     * @return 返回结果，true：存在，false：不存在
     */
    public boolean existsDataSource(String key) {
        return Objects.nonNull(this.targetDataSourceMap.get(key));
    }

}
