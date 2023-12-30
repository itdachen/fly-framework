/**
 * 使用注解启动配置:
 * spring:
 *   datasource:
 *     defaultDataSourceName: ds0
 *     names: ds0,ds1
 *     dynamic:
 *        ds0:
 *          key: ds0
 *          driverClassName:
 *          url:
 *          username:
 *          password
 *        ds1:
 *          key: ds1
 *          driverClassName:
 *          url:
 *          username:
 *          password
 *
 *
 * @author 王大宸
 * @date 2023-12-30 20:10
 */
package com.github.itdachen.boot.datasource;