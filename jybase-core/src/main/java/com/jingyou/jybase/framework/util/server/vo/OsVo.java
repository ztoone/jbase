package com.jingyou.jybase.framework.util.server.vo;

import java.util.Date;

/**
 * Created by zhongjy on 2016/06/12.
 */
public class OsVo {
    //系统类型
    private String osType;

    //系统补丁版本
    private String patch;

    //内核类型
    private String arch;
    //位数
    private String bit;

    //系统时间
    private String systemTime;

    public String getOsType() {
        return osType;
    }

    public String getPatch() {
        return patch;
    }

    public String getArch() {
        return arch;
    }

    public String getBit() {
        return bit;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public void setPatch(String patch) {
        this.patch = patch;
    }

    public void setArch(String arch) {
        this.arch = arch;
    }

    public void setBit(String bit) {
        this.bit = bit;
    }

    public String getSystemTime() {
        return systemTime;
    }

    public void setSystemTime(String systemTime) {
        this.systemTime = systemTime;
    }
}
