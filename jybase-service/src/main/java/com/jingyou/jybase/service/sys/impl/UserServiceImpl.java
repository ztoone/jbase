package com.jingyou.jybase.service.sys.impl;

import com.jingyou.jybase.common.Const;
import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.common.util.UUID;
import com.jingyou.jybase.framework.core.bean.sys.*;
import com.jingyou.jybase.framework.core.dao.sys.UserDao;
import com.jingyou.jybase.framework.core.dao.sys.UserRoleDao;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.service.sys.RoleService;
import com.jingyou.jybase.service.sys.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/5/20 0020.
 */
@Transactional
@Service
public class UserServiceImpl implements UserService{
    private Logger logger = LoggerFactory.getLogger(OrgServiceImpl.class);
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleService roleService;

    @Override
    public List<UserBean> findAll(){
       return userDao.queryAll();
    }

    @Override
    public List<UserBean> pageQuery(Conditions cond,Page page){
        return userDao.pageQuery(cond,page);
    }

    @Override
    public UserBean findByPK(String id) {
        return userDao.findByPK(id);
    }

    @Override
    public UserBean findAdmin(){
        return findByAccount("admin");
    }

    @Override
    public UserBean findByAccount(String account){
       return userDao.queryObj("account", account);
    }

    @Override
    public boolean save(UserBean user) {
        boolean bo = false;
        try {
            userDao.save(user);
            UserRoleBean userRole = new UserRoleBean();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleService.getDefaultRole().getId());
            userRole.setRoleName(Const.ROLE_NAME_DEFAULT);
            userRoleDao.save(userRole);
            bo = true;
        } catch (Exception e) {
            logger.error("用户添加失败!", e);
        }
        return bo;
    }

    @Override
    public boolean deleteByIds(String[] ids) {
        boolean bo = userDao.deletes(StringUtil.toString(ids),"id");
        bo = bo && userRoleDao.deleteByUserIds(ids);
        return bo;
    }

    @Override
    public boolean update(UserBean user) {
        boolean bo = false;
        try {
            userDao.update(user);
            bo = true;
        } catch (Exception e) {
            logger.error("用户更新失败!", e);
        }
        return bo;
    }

    @Override
    public List<RoleBean> getRolesByUserId(String userId){
        return userDao.getRolesByUserId(userId);
    }

    @Override
    public List<ResourceBean> getRessByUserId(String userId){
        return userDao.getRessByUserId(userId);
    }

    @Override
    public List<BtnBean> getBtns(String userId,String resId){
        return userDao.getBtns(userId, resId);
    }

    @Override
    public boolean check(String account){
        if(findByAccount(account) == null)
            return false;
        return true;
    }

    @Override
    public void evict(UserBean user){
        userDao.getSession().evict(user);
    }
}
