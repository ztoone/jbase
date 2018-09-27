package com.jingyou.jybase.framework.util.server.vo;

/**
 * Created by zhongjy on 2016/06/12.
 */
public class ServerVo {
    private CpuVo cpuVo;
    private MemoryVo memoryVo;
    private DiskVo diskVo;
    private NetVo netVo;
    private JvmVo jvmVo;
    public NetVo getNetVo() {
        return netVo;
    }

    public void setNetVo(NetVo netVo) {
        this.netVo = netVo;
    }

    public DiskVo getDiskVo() {
        return diskVo;
    }

    public CpuVo getCpuVo() {
        return cpuVo;
    }

    public MemoryVo getMemoryVo() {
        return memoryVo;
    }

    public void setCpuVo(CpuVo cpuVo) {
        this.cpuVo = cpuVo;
    }

    public void setMemoryVo(MemoryVo memoryVo) {
        this.memoryVo = memoryVo;
    }

    public void setDiskVo(DiskVo diskVo) {
        this.diskVo = diskVo;
    }

    public JvmVo getJvmVo() {
        return jvmVo;
    }

    public void setJvmVo(JvmVo jvmVo) {
        this.jvmVo = jvmVo;
    }
}
