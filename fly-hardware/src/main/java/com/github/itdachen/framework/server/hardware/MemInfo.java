package com.github.itdachen.framework.server.hardware;

import com.github.itdachen.framework.server.utils.Arith;

import java.io.Serializable;

/**
 * 内存信息
 *
 * @author 剑鸣秋朔
 * @date 2023/02/07 11:05
 */
public class MemInfo implements Serializable {
    private static final long serialVersionUID = 7395793183333748191L;

    /**
     * 内存总量
     */
    private double total;

    /**
     * 已用内存
     */
    private double used;

    /**
     * 剩余内存
     */
    private double free;

    public double getTotal() {
        return Arith.div(total, (1024 * 1024 * 1024), 2);
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public double getUsed() {
        return Arith.div(used, (1024 * 1024 * 1024), 2);
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public double getFree() {
        return Arith.div(free, (1024 * 1024 * 1024), 2);
    }

    public void setFree(long free) {
        this.free = free;
    }

    public double getUsage() {
        return Arith.mul(Arith.div(used, total, 4), 100);
    }

}
