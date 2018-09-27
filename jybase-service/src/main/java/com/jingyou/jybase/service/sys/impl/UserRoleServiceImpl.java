package com.jingyou.jybase.service.sys.impl;

import com.jingyou.jybase.common.util.ObjectUtil;
import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.bean.sys.UserRoleBean;
import com.jingyou.jybase.framework.core.dao.sys.UserRoleDao;
import com.jingyou.jybase.service.sys.UserRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
@Transactional
@Service
public class UserRoleServiceImpl implements UserRoleService{
    private Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);
    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public boolean saveOrUpdate(List<UserRoleBean> userRoles) {
        boolean bo = false;
        try {
            userRoleDao.saveOrUpdate(userRoles);
            bo = true;
        } catch (Exception e) {
            logger.error("用户角色分配失败!", e);
        }
        return bo;
    }

    @Override
    public List<UserRoleBean> getListByUserId(String userId) {
        return userRoleDao.list("userId",userId);
    }

    @Override
    public UserRoleBean getUserRole(String userId, String roleId) {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("userId",userId);
        param.put("roleId",roleId);
        return userRoleDao.queryObj(param);
    }

    @Override
    public UserRoleBean getUserRole(UserRoleBean userRole){
        return getUserRole(userRole.getUserId(), userRole.getRoleId());
    }

    @Override
    public boolean assginRole(List<UserRoleBean> userRoles, String[] delIds) {
        try {
            if(userRoles != null && userRoles.size() > 0){
                for(UserRoleBean userRole : userRoles){
                    UserRoleBean newObj = getUserRole(userRole);
                    if(!ObjectUtil.isNull(newObj)){
                        userRole.setId(newObj.getId());
                    }
                }
                saveOrUpdate(userRoles);
            }
            userRoleDao.deletes(StringUtil.toString(delIds),"id");
            return true;
        } catch (Exception e) {
            logger.error("用户角色分配失败!", e);
        }
        return false;
    }
}
