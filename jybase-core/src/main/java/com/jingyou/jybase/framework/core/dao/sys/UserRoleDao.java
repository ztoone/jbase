package com.jingyou.jybase.framework.core.dao.sys;

import com.jingyou.jybase.framework.core.base.BaseHibernateDao;
import com.jingyou.jybase.framework.core.bean.sys.UserRoleBean;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public interface UserRoleDao extends BaseHibernateDao<UserRoleBean,String> {
    /**
     * 删除用户下所有角色
     * @param userIds
     * @return
     */
    public boolean deleteByUserIds(String[] userIds);

    public boolean deleteByRoleIds(String[] userIds);
}
