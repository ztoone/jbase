package com.jingyou.jybase.service.sys.impl;

import com.jingyou.jybase.common.Const;
import com.jingyou.jybase.common.util.ObjectUtil;
import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.bean.sys.RoleResBean;
import com.jingyou.jybase.framework.core.dao.sys.RoleResDao;
import com.jingyou.jybase.service.sys.RoleResService;
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
public class RoleResServiceImpl implements RoleResService {
    private Logger logger = LoggerFactory.getLogger(RoleResServiceImpl.class);
    @Autowired
    private RoleResDao roleResDao;
    @Override
    public boolean saveOrUpdate(List<RoleResBean> resBeanList) {
        boolean bo = false;
        try {
            roleResDao.saveOrUpdate(resBeanList);
            bo = true;
        } catch (Exception e) {
            logger.error("角色资源分配失败!", e);
        }
        return bo;
    }

    @Override
    public List<RoleResBean> getListByRoleId(String roleId) {
        return  roleResDao.list("roleId",roleId);
    }

    @Override
    public List<RoleResBean> getResourcesByRoleId(String roleId) {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("roleId",roleId);
        param.put("type", Const.PERMISSION_TYPE_RESOURCE);
        return roleResDao.list(param);
    }

    @Override
    public List<RoleResBean> getBtnsByRoleId(String roleId) {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("roleId",roleId);
        param.put("type", Const.PERMISSION_TYPE_BUTTON);
        return roleResDao.list(param);
    }

    @Override
    public RoleResBean getRoleRes(String roleId,String resId){
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("roleId",roleId);
        param.put("resId",resId);
        return roleResDao.queryObj(param);
    }

    @Override
    public RoleResBean getRoleRes(RoleResBean roleRes){
        return getRoleRes(roleRes.getRoleId(),roleRes.getResId());
    }

    @Override
    public boolean assignResource(List<RoleResBean> roleResList,String[] delIds){
        try {
            if(roleResList != null && roleResList.size() > 0){
                for(RoleResBean roleRes : roleResList){
                    RoleResBean newObj = getRoleRes(roleRes);
                    if(!ObjectUtil.isNull(newObj)){
                        roleRes.setId(newObj.getId());
                    }
                }
                saveOrUpdate(roleResList);
            }
            roleResDao.deletes(StringUtil.toString(delIds),"id");
            return true;
        } catch (Exception e) {
            logger.error("角色资源分配失败!", e);
        }
        return false;
    }
}
