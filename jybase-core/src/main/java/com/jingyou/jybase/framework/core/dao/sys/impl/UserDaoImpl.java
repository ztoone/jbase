package com.jingyou.jybase.framework.core.dao.sys.impl;

import com.jingyou.jybase.framework.core.base.BaseHibernateDaoImpl;
import com.jingyou.jybase.framework.core.bean.sys.BtnBean;
import com.jingyou.jybase.framework.core.bean.sys.ResourceBean;
import com.jingyou.jybase.framework.core.bean.sys.RoleBean;
import com.jingyou.jybase.framework.core.bean.sys.UserBean;
import com.jingyou.jybase.framework.core.dao.sys.UserDao;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
@Transactional
@Repository
public class UserDaoImpl extends BaseHibernateDaoImpl<UserBean, String> implements UserDao {
    @Override
    public List<RoleBean> getRolesByUserId(String userId) {
        String sql = "select r.* from sys_role r left join sys_user_role ur on r.id = ur.role_id where r.enabled = 1 and ur.user_id = ?";
        Session session = getSession();
        SQLQuery query = session.createSQLQuery(sql);
        return query.addEntity(RoleBean.class).setString(0,userId).list();
    }

    @Override
    public List<ResourceBean> getRessByUserId(String userId) {
        String sql = "select distinct res.* from sys_resource res left join sys_role_res rr on res.id = rr.res_id left join sys_user_role ur on rr.role_id = ur.role_id left join sys_role r on r.id = ur.role_id " +
                "where res.enabled = 1 and res.deleted = 0 and r.enabled = 1 and  rr.type = 'resource' and ur.user_id = ? order by res.orders";
        Session session = getSession();
        SQLQuery query = session.createSQLQuery(sql).addEntity(ResourceBean.class);
        return query.setString(0,userId).list();
    }

    @Override
    public List<BtnBean> getBtns(String userId,String resId){
        String sql = "select distinct btn.* from sys_res_btn btn left join sys_role_res rr on btn.id = rr.res_id left join sys_user_role ur on ur.role_id = rr.role_id left join sys_role r on r.id = ur.role_id " +
                "where btn.enabled = 1 and r.enabled = 1 and rr.type = 'button' and ur.user_id = ? and btn.res_id = ? order by btn.orders" ;
        Session session = getSession();
        SQLQuery query = session.createSQLQuery(sql).addEntity(BtnBean.class);
        return query.setString(0,userId).setString(1,resId).list();
    }
}
