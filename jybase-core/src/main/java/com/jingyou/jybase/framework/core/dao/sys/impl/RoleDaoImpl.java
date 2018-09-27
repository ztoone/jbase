package com.jingyou.jybase.framework.core.dao.sys.impl;

import com.jingyou.jybase.framework.core.base.BaseHibernateDaoImpl;
import com.jingyou.jybase.framework.core.bean.sys.RoleBean;
import com.jingyou.jybase.framework.core.bean.sys.UserBean;
import com.jingyou.jybase.framework.core.dao.sys.RoleDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
@Transactional
@Repository
public class RoleDaoImpl extends BaseHibernateDaoImpl<RoleBean, String> implements RoleDao {
    @Override
    public List<UserBean> getUsers(String roleId){
        String sql = "select t1.* from sys_user t1\n" +
                "left join sys_user_role t2 on t1.id = t2.user_id\n" +
                "left join sys_role t3 on t2.role_id = t3.id\n" +
                "where t3.id = ?";
        return getSession().createSQLQuery(sql).addEntity(UserBean.class).setParameter(0,roleId).list();
        //String hql = " from UserBean u left join UserRoleBean ur on u.id = ur.userId left join RoleBean r on ur.roleId = r.id where r.id = ?";
        //return getSession().createQuery(hql).list();
    }
}
