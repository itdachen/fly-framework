package com.github.itdachen.framework.webmvc.convert;

/**
 * Description: 类型转换
 * Created by 王大宸 on 2023/04/27 14:12
 * Created with IntelliJ IDEA.
 */
public abstract class BizConvert<T, D, V> {

    public abstract T toJavaObject(D d);

    public abstract V toJavaObjectVo(T t);

}
