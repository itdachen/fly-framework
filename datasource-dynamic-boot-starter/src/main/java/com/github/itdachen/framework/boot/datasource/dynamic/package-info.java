/**
 * 配置文件样例
 *
 * spring:
 *   datasource:
 *     type: com.alibaba.druid.pool.DruidDataSource
 *     druid:
 *       master:
 *         url: jdbc:mysql://xxxxxx:3306/test1?characterEncoding=utf-8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull&useSSL=false
 *         username: root
 *         password: 123456
 *         driver-class-name: com.mysql.cj.jdbc.Driver
 *       slave:
 *         url: jdbc:mysql://xxxxx:3306/test2?characterEncoding=utf-8&allowMultiQueries=true&zeroDateTimeBehavior=convertToNull&useSSL=false
 *         username: root
 *         password: 123456
 *         driver-class-name: com.mysql.cj.jdbc.Driver
 *       initial-size: 15
 *       min-idle: 15
 *       max-active: 200
 *       max-wait: 60000
 *       time-between-eviction-runs-millis: 60000
 *       min-evictable-idle-time-millis: 300000
 *       validation-query: ""
 *       test-while-idle: true
 *       test-on-borrow: false
 *       test-on-return: false
 *       pool-prepared-statements: false
 *       connection-properties: false
 *
 *
 *
 * 配置数据源:
 *  @Configuration
 * public class MasterSlaveDateSourceConfig {
 *
 *     @Bean
 *     @ConfigurationProperties("spring.datasource.druid.master")
 *     public DataSource masterDataSource(){
 *         return DruidDataSourceBuilder.create().build();
 *     }
 *
 *     @Bean
 *     @ConfigurationProperties("spring.datasource.druid.slave")
 *     public DataSource slaveDataSource(){
 *         return DruidDataSourceBuilder.create().build();
 *     }
 *
 *     @Bean(name = "dynamicDataSource")
 *     @Primary
 *     public DynamicDataSource createDynamicDataSource(){
 *         Map<Object,Object> dataSourceMap = new HashMap<>();
 *         DataSource defaultDataSource = masterDataSource();
 *         dataSourceMap.put("master",defaultDataSource);
 *         dataSourceMap.put("slave",slaveDataSource());
 *         return new DynamicDataSource(defaultDataSource,dataSourceMap);
 *     }
 *
 * }
 *
 *
 *
 *
 * @author 剑鸣秋朔
 * @date 2023-12-27 21:24
 */
package com.github.itdachen.framework.boot.datasource.dynamic;