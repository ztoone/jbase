package com.jingyou.jybase.service.util;

import com.jingyou.jybase.framework.core.bean.sys.OrgBean;
import com.jingyou.jybase.framework.core.dao.sys.OrgDao;
import com.jingyou.jybase.framework.util.SpringBeanUtil;
import com.jingyou.jybase.service.sys.OrgService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Administrator on 2016/7/12 0012.
 */
public class OrgUtil {
    private OrgUtil(){}
    private static OrgUtil instance = new OrgUtil();
    public static OrgUtil getInstance(){
        return instance;
    }

    private OrgService orgService = SpringBeanUtil.getBean(OrgService.class);
    public synchronized void genBsid(OrgBean org){
        String bsid = "";
        String pid = org.getPid();
        String pbsid = orgService.findByPK(pid).getBsid();
        List<OrgBean> children =  orgService.findChildren(pid,true);
        if(children != null && children.size() > 0){
            Collections.sort(children, new Comparator<OrgBean>() {
                public int compare(OrgBean o1, OrgBean o2) {
                    return o2.getBsid().compareTo(o1.getBsid());
                }
            });
            String maxBrotherBsid = children.get(0).getBsid();
            maxBrotherBsid = maxBrotherBsid.substring(maxBrotherBsid.lastIndexOf("-") + 1, maxBrotherBsid.length());
            int myBsid = Integer.parseInt(maxBrotherBsid) + 1;
            bsid = pbsid + "-" + myBsid;
        }else{
            bsid = (pbsid + "-1001");
        }
        org.setBsid(bsid);
    }
}
