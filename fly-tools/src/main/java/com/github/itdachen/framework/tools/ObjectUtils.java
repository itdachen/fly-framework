package com.github.itdachen.framework.tools;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Description: 对象转换工具类
 * Created by 王大宸 on 2021-12-01 17:12
 * Created with IntelliJ IDEA.
 */
public class ObjectUtils implements ApplicationContextAware {

    private final static Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (null == ObjectUtils.applicationContext) {
            ObjectUtils.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取Bean
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /***
     * Description：obj 转 Object
     *
     * @author 王大宸
     * @date 2020-2-8 14:54
     * @param obj                传入对象
     * @param destinationClass  目标对象
     * @return T
     */
    public static <T> T objToClass(Object obj, Class<T> destinationClass) {
        if (obj == null) {
            return null;
        }
        return MAPPER.map(obj, destinationClass);
    }

    /***
     * 功能说明：对象转换
     *
     * @author 王大宸
     * @date 2019/4/4 23:37
     * @param source 资源对象
     * @param destination 目标对象实例
     * @return void
     */
    public static void objToObj(Object source, Object destination) {
        MAPPER.map(source, destination);
    }

    /***
     * 功能说明： 对象装换
     *
     * @author 王大宸
     * @date 2019/4/4 23:37
     * @param sourceList  资源对象列表
     * @param destinationClass  目标对象类型
     * @return java.util.List<T>
     */
    public static <T> List<T> mapList(Collection sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<>();
        for (Object sourceObject : sourceList) {
            destinationList.add(MAPPER.map(sourceObject, destinationClass));
        }
        return destinationList;
    }

    /***
     * 功能说明： 对象转 map
     *
     * @author 王大宸
     * @date 2020/7/16 22:25
     * @param obj
     * @return Map<String, Object>
     */
    public static Map<String, Object> objToMap(Object obj) throws IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> clazz = obj.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = nvl(field.get(obj));
            map.put(fieldName, value);
        }
        return map;
    }

    public static Object nvl(Object value) {
        return value != null ? value : "";
    }


    /***
     * 功能说明： 将 object 转成 list<T>
     *
     * @author 王大宸
     * @date 2019/4/4 23:37
     * @param obj    资源对象列表
     * @param clazz  目标对象类型
     * @return java.util.List<T>
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return null;
    }

    public static <T> List<T> objToList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<T>();
        if (obj instanceof ArrayList<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
        }
        return result;
    }
}
