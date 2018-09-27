package com.jingyou.jybase.framework.util.server;

import com.google.common.net.InetAddresses;
import com.jingyou.jybase.common.util.NumberUtil;
import com.jingyou.jybase.framework.util.server.vo.*;
import org.hyperic.sigar.*;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

/**
 * Created by zhongjy on 2016/06/12.
 */
public class SigarUtil {

    private final static long _1M = 1024L * 1024L;//以byte为最小单位
    private final static long _1G = 1024L * 1024L;// 以kb为最小单位
    private final static long _1KB = 1000L;//以byte为最小单位,网络流量按1000算
    private Sigar sigar = new Sigar();

    /**
     * 获取电脑信息
     * @return
     * @throws SigarException
     * @throws Throwable
     */
    public ServerVo getServer() throws SigarException,Throwable{
        ServerVo computerVo = new ServerVo();
        computerVo.setCpuVo(getCpu());
        computerVo.setDiskVo(getDisk());
        computerVo.setMemoryVo(getMemory());
        computerVo.setNetVo(getNet());
        computerVo.setJvmVo(getJvm());
        return computerVo;
    }

    /**
     * 获取cpu信息，支持多核cpu
     * @return
     * @throws SigarException
     * @throws Throwable
     */
    public CpuVo getCpu() throws SigarException,Throwable{
        CpuVo cpuVo = new CpuVo();
        cpuVo.setCpuNum(sigar.getCpuInfoList().length);
        CpuInfo ci = sigar.getCpuInfoList()[0];
        cpuVo.setCpuInfo(ci.getVendor() + " " + ci.getModel());

        CpuPerc perc = sigar.getCpuPerc();
        cpuVo.setFree(perc.getIdle());
        cpuVo.setUsed(perc.getCombined());
        cpuVo.setFreePercent(NumberUtil.percent(cpuVo.getFree(), cpuVo.getFree() + cpuVo.getUsed(), 0));
        cpuVo.setUsedPercent(NumberUtil.percent(cpuVo.getUsed(), cpuVo.getFree() + cpuVo.getUsed(),0));

        List<CpuVo> cpus= new ArrayList<CpuVo>(cpuVo.getCpuNum());
        CpuPerc[] cpuArray = sigar.getCpuPercList();
        for (CpuPerc cpu : cpuArray) {
            cpus.add(new CpuVo(cpu.getIdle(), cpu.getCombined()));
        }
        cpuVo.setCpus(cpus);
        return cpuVo;
    }

    /**
     * 获取memory信息
     * @return
     * @throws SigarException
     * @throws Throwable
     */
    public MemoryVo getMemory() throws SigarException,Throwable{
        MemoryVo memoryVo = new MemoryVo();
        Mem mem = sigar.getMem();
        memoryVo.setTotal(mem.getTotal() / _1M);
        memoryVo.setUsed(mem.getUsed() / _1M);
        memoryVo.setFree(mem.getFree() / _1M);
        memoryVo.setFreePercent(NumberUtil.percent(memoryVo.getFree(), memoryVo.getFree() + memoryVo.getUsed(),0));
        memoryVo.setUsedPercent(NumberUtil.percent(memoryVo.getUsed(), memoryVo.getFree() + memoryVo.getUsed(),0));
        return memoryVo;
    }

    /**
     * 获取硬盘信息
     * @return
     * @throws SigarException
     * @throws Throwable
     */
    public DiskVo getDisk() throws SigarException,Throwable{
        DiskVo diskVo = new DiskVo();
        List<DiskVo> disks = new ArrayList<DiskVo>();
        FileSystem[] fsArray = sigar.getFileSystemList();
        FileSystemUsage usage = null;
        DiskVo dv = null;
        for (FileSystem fs : fsArray) {
            if (fs.getType() == 2) {// 只加载本地硬盘
                usage = sigar.getFileSystemUsage(fs.getDirName());
                dv = new DiskVo();
                dv.setDiskName(fs.getDevName());
                dv.setDiskFileType(fs.getSysTypeName());
                dv.setTotal(usage.getTotal() / _1G);
                dv.setUsed(usage.getUsed() / _1G);
                dv.setFree(usage.getFree() / _1G);
                disks.add(dv);
                diskVo.setTotal(diskVo.getTotal() + usage.getTotal());
                diskVo.setUsed(diskVo.getUsed() + usage.getUsed());
                diskVo.setFree(diskVo.getFree() + usage.getFree());
            }
        }
        diskVo.setDisks(disks);
        diskVo.setTotal(diskVo.getTotal() / _1G);
        diskVo.setUsed(diskVo.getUsed() / _1G);
        diskVo.setFree(diskVo.getFree() / _1G);
        diskVo.setFreePercent(NumberUtil.percent(diskVo.getFree(), diskVo.getFree() + diskVo.getUsed(),0));
        diskVo.setUsedPercent(NumberUtil.percent(diskVo.getUsed(), diskVo.getFree() + diskVo.getUsed(),0));
        return diskVo;
    }

    /**
     * 获取操作系统信息
     * @return
     * @throws SigarException
     * @throws Throwable
     */
    public OsVo getOs() throws SigarException,Throwable{
        OsVo osVo = new OsVo();
        OperatingSystem os = OperatingSystem.getInstance();
        osVo.setOsType(os.getVendorName());
        osVo.setBit(os.getDataModel());
        osVo.setArch(os.getArch());
        osVo.setPatch(os.getPatchLevel());
        osVo.setSystemTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        return osVo;
    }

    /**
     * 获取网卡流量信息,支持多网卡
     * @return
     * @throws SigarException
     * @throws Throwable
     */
    public NetVo getNet()throws SigarException,Throwable{
        NetVo netVo = new NetVo();
        String[] ifNames = sigar.getNetInterfaceList();
        List<NetVo> nets = new ArrayList<NetVo>();
        NetVo net = null;
        for(int i=0;i<ifNames.length;i++){
            String name = ifNames[i];
            NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
            String address = ifconfig.getAddress();
            InetAddress forUriString = InetAddresses.forUriString(address);
            boolean bo=!forUriString.isAnyLocalAddress() && !forUriString.isLoopbackAddress();
            if(bo){
                net = new NetVo();
                NetInterfaceStat ifstat = sigar.getNetInterfaceStat(name);
                net.setName(name);
                net.setDescription(ifconfig.getDescription());
                net.setIn(ifstat.getRxBytes()/_1KB);
                net.setOut(ifstat.getTxBytes()/_1KB);
                netVo.setIn(netVo.getIn() + ifstat.getRxBytes());
                netVo.setOut(netVo.getOut() + ifstat.getTxBytes());
                nets.add(net);
            }
        }
        netVo.setNets(nets);
        netVo.setIn(netVo.getIn()/_1KB);
        netVo.setOut(netVo.getOut()/_1KB);
        return netVo;
    }

    public JvmVo getJvm(){
        JvmVo jvmVo = new JvmVo();
        Properties props = System.getProperties();
        jvmVo.setName(props.getProperty("java.vm.name"));
        jvmVo.setVersion(props.getProperty("java.version"));
        Runtime runtime = Runtime.getRuntime();
        jvmVo.setTotal(runtime.totalMemory() / _1M);
        jvmVo.setMax(runtime.maxMemory()  / _1M);
        jvmVo.setUsed((runtime.totalMemory() - runtime.freeMemory())  / _1M);
        jvmVo.setFree(runtime.freeMemory()  / _1M);
        return jvmVo;
    }
}
