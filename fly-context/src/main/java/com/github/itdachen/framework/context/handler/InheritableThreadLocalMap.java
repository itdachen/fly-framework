package com.github.itdachen.framework.context.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现父子线程之间的线程本地变量传递
 * A --> threadLocal ("userId",1001)
 * A --> new Thread(B) --> B线程属于 A 线程的子线程，threadLocal get("userId")
 *
 * @author 王大宸
 * @date 2025-10-21 10:49
 */
public class InheritableThreadLocalMap<T extends Map<Object, Object>> extends InheritableThreadLocal<Map<Object, Object>> {

    @Override
    protected Map<Object, Object> initialValue() {
        return new HashMap();
    }

    @Override
    protected Map<Object, Object> childValue(Map<Object, Object> parentValue) {
        if (parentValue != null) {
            return (Map<Object, Object>) ((HashMap<Object, Object>) parentValue).clone();
        } else {
            return null;
        }
    }

}
