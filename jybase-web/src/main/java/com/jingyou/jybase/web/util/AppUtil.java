package com.jingyou.jybase.web.util;

import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.bean.sys.OrgBean;
import com.jingyou.jybase.framework.core.bean.sys.UserBean;
import com.jingyou.jybase.framework.util.SpringBeanUtil;
import com.jingyou.jybase.service.sys.OrgService;
import com.jingyou.jybase.service.sys.UserService;
import org.apache.shiro.SecurityUtils;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public  class AppUtil {
    private static UserService userService = SpringBeanUtil.getBean(UserService.class);
    private static OrgService orgService = SpringBeanUtil.getBean(OrgService.class);

    /**
     * 取当前登陆用户名
     * @return
     */
    public static String getCurrentUserName(){
        return SecurityUtils.getSubject().getPrincipal().toString();
    }

    /**
     * 取当前登陆用户
     * @return
     */
    public static UserBean getCurrentUser(){
        String account = getCurrentUserName();
        if(!StringUtil.isBlank(account)){
            return userService.findByAccount(account);
        }
        return null;
    }

    /**
     * 取当前登陆用户所属机构
     * @return
     */
    public static OrgBean getCurrentOrg(){
        UserBean user = getCurrentUser();
        if(user != null){
            return orgService.findByPK(user.getOrgId());
        }
        return null;
    }

    /**
     * 取当前登陆用户所属机构
     * @return
     */
    public static String getCurrentOrgName(){
        OrgBean org = getCurrentOrg();
        if(org != null)
            return org.getName();
        return null;
    }

    /**
     * 取顶级单位
     * @return
     */
    public static OrgBean getRootOrg(){
        OrgBean curr = getCurrentOrg();
        return orgService.getRootOrg(curr);
    }
}
