package com.github.itdachen.framework.autoconfigure.jwt;

/**
 * Description:
 * Created by 王大宸 on 2023-07-22 14:50
 * Created with IntelliJ IDEA.
 */
public enum KeyCacheType {

    /**
     * 基于内存, 单体环境下
     */
    MEMORY,

    /**
     * 基于 redis 存储, 用户分布式环境下
     */
    REDIS,

}
