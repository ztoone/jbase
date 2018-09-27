package com.jingyou.jybase.framework.core.dao.sys.impl;

import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.base.BaseHibernateDaoImpl;
import com.jingyou.jybase.framework.core.bean.sys.RoleResBean;
import com.jingyou.jybase.framework.core.dao.sys.RoleResDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
@Transactional
@Repository
public class RoleResDaoImpl extends BaseHibernateDaoImpl<RoleResBean, String> implements RoleResDao {
    @Override
    public boolean deleteRoleRes(String[] roleIds){
        try {
            String hql = "delete RoleResBean ur where ur.roleId in(" + StringUtil.toString(roleIds) +")";
            getSession().createQuery(hql).executeUpdate();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
