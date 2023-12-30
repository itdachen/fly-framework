package com.github.itdachen.boot.datasource.context;

/**
 * 通过get，set，remove方法来获取、设置、删除当前线程对应的数据源。
 *
 * @author 王大宸
 * @date 2023-12-30 17:31
 */
public class DataSourceContextHolder {


    /**
     * 此类提供线程局部变量。
     * 这些变量不同于它们的正常对应关系是每个线程访问一个线程(通过 get、set 方法)
     * 有自己的独立初始化变量的副本。
     */
    private static final ThreadLocal<String> DYNAMIC_DATASOURCE_HOLDER = new ThreadLocal<>();


    /***
     * 设置数据源
     *
     * @author 王大宸
     * @date 2023/12/30 19:00
     * @param dataSourceName 数据源名称
     * @return void
     */
    public static void setDataSource(String dataSourceName) {
        DYNAMIC_DATASOURCE_HOLDER.set(dataSourceName);
    }

    /***
     * 获取当前线程的数据源
     *
     * @author 王大宸
     * @date 2023/12/30 19:00
     * @return java.lang.String
     */
    public static String getDataSource() {
        return DYNAMIC_DATASOURCE_HOLDER.get();
    }

    /***
     * 删除当前数据源
     *
     * @author 王大宸
     * @date 2023/12/30 19:00
     * @return void
     */
    public static void removeDataSource() {
        DYNAMIC_DATASOURCE_HOLDER.remove();
    }


}
