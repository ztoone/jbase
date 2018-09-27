package com.jingyou.jybase.framework.util.server.vo;

/**
 * Created by zhongjy on 2016/06/12.
 */

import java.util.List;

public class CpuVo {
    // cpu信息
    private String cpuInfo;
    // cpu个数
    private int cpuNum;
    // cpu空闲
    private double free;

    // cpu空闲率
    private double freePercent;

    // cpu使用
    private double used;

    // cpu使用率
    private double usedPercent;

    // 多核cpu
    private List<CpuVo> cpus;

    public CpuVo(){}

    public void setCpus(List<CpuVo> cpus) {
        this.cpus = cpus;
    }

    public void setCpuInfo(String cpuInfo) {
        this.cpuInfo = cpuInfo;
    }

    public void setCpuNum(int cpuNum) {
        this.cpuNum = cpuNum;
    }

    public void setFree(double free) {
        this.free = free;
    }

    public void setFreePercent(double freePercent) {
        this.freePercent = freePercent;
    }

    public void setUsed(double used) {
        this.used = used;
    }

    public void setUsedPercent(double usedPercent) {
        this.usedPercent = usedPercent;
    }

    public CpuVo(double free, double used) {
        this.free = free;
        this.used = used;
    }

    public int getCpuNum() {
        return cpuNum;
    }

    public List<CpuVo> getCpus() {
        return cpus;
    }

    public double getFree() {
        return free;
    }

    public double getUsed() {
        return used;
    }

    public String getCpuInfo() {
        return cpuInfo;
    }

    public double getFreePercent() {
        return freePercent;
    }

    public double getUsedPercent() {
        return usedPercent;
    }

}

