package com.jingyou.jybase.framework.core.dao.sys;

import com.jingyou.jybase.framework.core.base.BaseHibernateDao;
import com.jingyou.jybase.framework.core.bean.sys.RoleBean;
import com.jingyou.jybase.framework.core.bean.sys.UserBean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public interface RoleDao extends BaseHibernateDao<RoleBean,String> {
    public List<UserBean> getUsers(String roleId);
}
