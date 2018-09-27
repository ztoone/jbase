package com.jingyou.jybase.framework.util.server.vo;

/**
 * Created by zhongjy on 2016/06/12.
 */

import java.util.List;

//定义：此处硬盘指的是分区
public class DiskVo {
    private String diskName;// 硬盘名
    private String diskFileType;// 硬盘文件类型
    private long total;// 硬盘大小
    private long used;// 硬盘已使用
    private double usedPercent;
    private long free;// 硬盘空闲
    private double freePercent;
    // 多块硬盘
    private List<DiskVo> disks;
    public String getDiskFileType() {
        return diskFileType;
    }

    public String getDiskName() {
        return diskName;
    }

    public List<DiskVo> getDisks() {
        return disks;
    }

    public long getFree() {
        return free;
    }

    public long getTotal() {
        return total;
    }

    public long getUsed() {
        return used;
    }

    public void setDiskName(String diskName) {
        this.diskName = diskName;
    }

    public void setDiskFileType(String diskFileType) {
        this.diskFileType = diskFileType;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public void setUsed(long used) {
        this.used = used;
    }

    public void setFree(long free) {
        this.free = free;
    }

    public void setDisks(List<DiskVo> disks) {
        this.disks = disks;
    }
    public double getFreePercent() {
        return freePercent;
    }

    public double getUsedPercent() {
        return usedPercent;
    }

    public void setUsedPercent(double usedPercent) {
        this.usedPercent = usedPercent;
    }

    public void setFreePercent(double freePercent) {
        this.freePercent = freePercent;
    }
}
