package com.github.itdachen.framework.server.hardware;


import com.github.itdachen.framework.server.utils.Arith;

import java.io.Serializable;

/**
 * CUP 信息
 *
 * @author 剑鸣秋朔
 * @date 2023/02/07 11:05
 */
public class CpuInfo implements Serializable {
    private static final long serialVersionUID = 7087135696593871653L;

    /**
     * 核心数
     */
    private int cpuNum;

    /**
     * CPU总的使用率
     */
    private double total;

    /**
     * CPU系统使用率
     */
    private double sys;

    /**
     * CPU用户使用率
     */
    private double used;

    /**
     * CPU当前等待率
     */
    private double wait;

    /**
     * CPU当前空闲率
     */
    private double free;

    public int getCpuNum() {
        return cpuNum;
    }

    public void setCpuNum(int cpuNum) {
        this.cpuNum = cpuNum;
    }

    public double getTotal() {
        return Arith.round(Arith.mul(total, 100), 2);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSys() {
        return Arith.round(Arith.mul(sys / total, 100), 2);
    }

    public void setSys(double sys) {
        this.sys = sys;
    }

    public double getUsed() {
        return Arith.round(Arith.mul(used / total, 100), 2);
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public double getWait() {
        return Arith.round(Arith.mul(wait / total, 100), 2);
    }

    public void setWait(double wait) {
        this.wait = wait;
    }

    public double getFree() {
        return Arith.round(Arith.mul(free / total, 100), 2);
    }

    public void setFree(double free) {
        this.free = free;
    }


}
