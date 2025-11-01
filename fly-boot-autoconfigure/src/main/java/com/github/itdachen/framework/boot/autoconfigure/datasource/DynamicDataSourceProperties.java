package com.github.itdachen.framework.boot.autoconfigure.datasource;

import com.github.itdachen.framework.boot.autoconfigure.datasource.entity.DataSourceInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;


/***
* 多数据源配置
*
* @author 王大宸
* @date 2023-12-30 20:10
*/
@ConfigurationProperties(prefix = "spring.datasource")
public class DynamicDataSourceProperties {


    /**
     * 数据源节点名称
     */
    private String names;

    /**
     * 默认数据源
     */
    private String defaultDataSourceName;

    /**
     * 数据源
     */
    private Map<String, DataSourceInfo> dynamic;


    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getDefaultDataSourceName() {
        return defaultDataSourceName;
    }

    public void setDefaultDataSourceName(String defaultDataSourceName) {
        this.defaultDataSourceName = defaultDataSourceName;
    }

    public Map<String, DataSourceInfo> getDynamic() {
        return dynamic;
    }

    public void setDynamic(Map<String, DataSourceInfo> dynamic) {
        this.dynamic = dynamic;
    }

}
