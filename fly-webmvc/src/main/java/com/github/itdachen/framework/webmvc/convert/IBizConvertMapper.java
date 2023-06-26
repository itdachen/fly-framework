package com.github.itdachen.framework.webmvc.convert;

/**
 * Description: 类型转换
 * Created by 王大宸 on 2023/04/27 14:12
 * Created with IntelliJ IDEA.
 */
public interface IBizConvertMapper<T, D, V> {

    /***
     * DTO 转 Entity
     *
     * @author 王大宸
     * @date 2023/6/26 9:09
     * @param d d
     * @return T
     */
    T toJavaObject(D d);

    /***
     * Entity 转 VO
     *
     * @author 王大宸
     * @date 2023/6/26 9:10
     * @param t t
     * @return V
     */
    V toJavaObjectVo(T t);

}
