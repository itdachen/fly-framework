package com.github.itdachen.boot.datasource.dynamic.aspect;

import com.github.itdachen.boot.datasource.context.DataSourceContextHolder;
import com.github.itdachen.framework.context.annotation.DynamicDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * DynamicDataSourceAspect
 *
 * @author 王大宸
 * @date 2023-12-30 17:40
 */
@Aspect
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(com.github.itdachen.framework.context.annotation.DynamicDataSource)")
    public void dynamicDataSource() {

    }

    @Around("dynamicDataSource()")
    public Object datasourceAround(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<?> targetClass = point.getTarget().getClass();
        Method method = signature.getMethod();

        String dataSourceKey = "";

        /* 作用在类上 */
        DynamicDataSource targetDataSource = targetClass.getAnnotation(DynamicDataSource.class);
        if (null != targetDataSource) {
            dataSourceKey = targetDataSource.value();
        }
        /* 作用在方法上, 方法上的优先级最高 */
        DynamicDataSource methodDataSource = method.getAnnotation(DynamicDataSource.class);
        if (null != methodDataSource) {
            dataSourceKey = methodDataSource.value();
        }
        DataSourceContextHolder.setDataSource(dataSourceKey);

        try {
            return point.proceed();
        } finally {
            DataSourceContextHolder.removeDataSource();
        }
    }

}
