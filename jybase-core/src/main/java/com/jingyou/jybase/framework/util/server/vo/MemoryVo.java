package com.jingyou.jybase.framework.util.server.vo;

/**
 * Created by zhongjy on 2016/06/12.
 */

public class MemoryVo {
    private long total;
    private long used;
    private double usedPercent;
    private long free;
    private double freePercent;
    public long getFree() {
        return free;
    }
    public long getTotal() {
        return total;
    }
    public long getUsed() {
        return used;
    }
    public double getUsedPercent() {
        return usedPercent;
    }
    public double getFreePercent() {
        return freePercent;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public void setUsedPercent(double usedPercent) {
        this.usedPercent = usedPercent;
    }

    public void setFree(long free) {
        this.free = free;
    }

    public void setFreePercent(double freePercent) {
        this.freePercent = freePercent;
    }
}