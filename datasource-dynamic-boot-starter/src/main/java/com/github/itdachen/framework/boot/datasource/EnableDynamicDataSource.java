package com.github.itdachen.framework.boot.datasource;

import com.github.itdachen.framework.boot.datasource.dynamic.DynamicDataSourceRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 动态数据源, 适用于多数据源的情况
 * 如果是主从情况, 建议使用 sharding-jdbc-spring-boot-starter
 * <p>
 * 使用注意:
 * * 1、Spring Boot 启动类, 需要排除默认数据源:
 * * @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class,DataSourceTransactionManagerAutoConfiguration.class})
 * *
 * * 2、使用类上添加 @DynamicDataSource(value="slave") 注解, 注解可用于类上, 也可以用于方法上, 方法上优先级大于类上, 默认数据源可以不用添加注解
 * <p>
 * 可以不使用这个注解, 在项目启动的时候只初始化默认数据源, 其他数据源从默认数据源中加载
 *
 * @author 剑鸣秋朔
 * @date 2023-12-30 19:17
 */
@Inherited
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({DynamicDataSourceRegister.class})
public @interface EnableDynamicDataSource {
}
