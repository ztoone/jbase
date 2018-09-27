package com.jingyou.jybase.framework.core.dao.sys.impl;

import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.base.BaseHibernateDaoImpl;
import com.jingyou.jybase.framework.core.bean.sys.UserRoleBean;
import com.jingyou.jybase.framework.core.dao.sys.UserRoleDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
@Repository
@Transactional
public class UserRoleDaoImpl extends BaseHibernateDaoImpl<UserRoleBean, String> implements UserRoleDao {
    @Override
    public boolean deleteByUserIds(String[] userIds){
        try {
            String hql = "delete UserRoleBean ur where ur.userId in(" + StringUtil.toString(userIds) + ")";
            getSession().createQuery(hql).executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteByRoleIds(String[] roleIds){
        try {
            String hql = "delete UserRoleBean ur where ur.roleId in(" + StringUtil.toString(roleIds) + ")";
            getSession().createQuery(hql).executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
