package com.github.itdachen.framework.boot.autoconfigure.datasource;

import com.github.itdachen.framework.boot.autoconfigure.AppContextHelper;

/**
 * 数据源配置
 *
 * @author 剑鸣秋朔
 * @date 2024-06-19 16:53
 */
public class DataSourceHelperProperties {

    public DataSourceProperties properties() {
        return AppContextHelper.getBean(DataSourceProperties.class);
    }

    public DynamicDataSourceProperties dynamic() {
        return AppContextHelper.getBean(DynamicDataSourceProperties.class);
    }

}
