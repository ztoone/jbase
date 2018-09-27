package com.jingyou.jybase.framework.core.dao.sys;

import com.jingyou.jybase.framework.core.base.BaseHibernateDao;
import com.jingyou.jybase.framework.core.bean.sys.RoleResBean;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public interface RoleResDao extends BaseHibernateDao<RoleResBean,String> {
    /**
     * 删除角色下所有资源
     * @param roleIds
     * @return
     */
    public boolean deleteRoleRes(String[] roleIds);
}
