package com.github.itdachen.framework.webmvc.utils;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型转换
 *
 * @author 剑鸣秋朔
 * @date 2025/10/17 0:48
 */
public class ConvertBeanUtils {

    /***
     * 将一个对象转成目标对象
     *
     * @author 剑鸣秋朔
     * @date 2025/10/17 0:56
     * @param source 源数据
     * @param targetClass 目标数据类类型, 例如: User.class
     * @return T
     */
    public static <T> T convert(Object source, Class<T> targetClass) {
        if (source == null) {
            return null;
        }
        T t = newInstance(targetClass);
        BeanUtils.copyProperties(source, t);
        return t;
    }

    /***
     * 将List对象转换成目标对象，注意实现是ArrayList
     *
     * @author 剑鸣秋朔
     * @date 2025/10/17 0:56
     * @param sourceList sourceList
     * @param targetClass targetClass
     * @return java.util.List<T>
     */
    public static <K, T> List<T> convertList(List<K> sourceList, Class<T> targetClass) {
        if (sourceList == null) {
            return null;
        }
        if (sourceList.isEmpty()) {
            return new ArrayList<>();
        }
        List<T> targetList = new ArrayList<T>((int) (sourceList.size() / 0.75) + 1);
        for (K source : sourceList) {
            targetList.add(convert(source, targetClass));
        }
        return targetList;
    }


    /***
     * 根据类类型获取实例
     *
     * @author 剑鸣秋朔
     * @date 2025/10/17 0:56
     * @param targetClass targetClass
     * @return T
     */
    private static <T> T newInstance(Class<T> targetClass) {
        try {
            return targetClass.newInstance();
        } catch (Exception e) {
            throw new BeanInstantiationException(targetClass, "instantiation error" , e);
        }
    }


}
