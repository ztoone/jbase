package com.jingyou.jybase.service.sys.impl;

import com.jingyou.jybase.common.Const;
import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.common.util.UUID;
import com.jingyou.jybase.framework.core.bean.sys.BtnBean;
import com.jingyou.jybase.framework.core.bean.sys.RoleBean;
import com.jingyou.jybase.framework.core.bean.sys.RoleResBean;
import com.jingyou.jybase.framework.core.bean.sys.UserBean;
import com.jingyou.jybase.framework.core.dao.sys.RoleDao;
import com.jingyou.jybase.framework.core.dao.sys.RoleResDao;
import com.jingyou.jybase.framework.core.dao.sys.UserRoleDao;
import com.jingyou.jybase.framework.core.tree.PermissionTree;
import com.jingyou.jybase.framework.core.tree.ResourceTree;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.service.sys.BtnService;
import com.jingyou.jybase.service.sys.ResourceService;
import com.jingyou.jybase.service.sys.RoleService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
@Transactional
@Service
public class RoleServiceImpl implements RoleService {
    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RoleResDao roleResDao;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private BtnService btnService;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public List<RoleBean> findAll() {
        return roleDao.list("enabled",1);
    }

    @Override
    public List<RoleBean> pageQuery(Conditions cond,Page page) {
        return roleDao.pageQuery(cond,page);
    }

    @Override
    public RoleBean findByPK(String id) {
        return roleDao.findByPK(id);
    }

    @Override
    public boolean save(RoleBean role) {
        boolean bo = false;
        try {
            roleDao.save(role);
            RoleResBean roleResBean = new RoleResBean();
            roleResBean.setType(Const.PERMISSION_TYPE_RESOURCE);
            roleResBean.setResId(Const.TREE_ROOT_ID);
            roleResBean.setRoleId(role.getId());
            roleResDao.save(roleResBean);//添加角色时默认给角色添加root资源
            bo = true;
        } catch (Exception e) {
            logger.error("参数添加失败!", e);
        }
        return bo;
    }

    @Override
    public boolean deleteByIds(String[] ids) {
        boolean bo = roleDao.deletes(StringUtil.toString(ids),"id");
        bo = bo && roleResDao.deleteRoleRes(ids);
        bo = bo && userRoleDao.deleteByRoleIds(ids);
        return bo;
    }

    @Override
    public boolean update(RoleBean role) {
        boolean bo = false;
        try {
            roleDao.update(role);
            bo = true;
        } catch (Exception e) {
            logger.error("角色更新失败!", e);
        }
        return bo;
    }

    @Override
    public boolean check(String name) {
        Criteria criterion = roleDao.getCriteria();
        criterion.add(Restrictions.eq("name", name));
        if(null == criterion.uniqueResult())
            return false;
        return true;
    }

    @Override
    public PermissionTree getRootNode(){
        ResourceTree resourceTree = resourceService.getRootNode(false);
        PermissionTree root = new PermissionTree();
        root.setId(resourceTree.getId());
        root.setResId(resourceTree.getId());
        root.setName(resourceTree.getName());
        root.setPid(resourceTree.getPid());
        root.setType("resource");
        root.setIconCls(resourceTree.getIconCls());
        t(root, resourceTree);
        return root;
    }

    private void t(PermissionTree node,ResourceTree resourceTree){
        List<ResourceTree> resChildren = resourceTree.getChildren();
        if(resChildren != null && resChildren.size() > 0){
            List<PermissionTree> permissionTrees = new ArrayList<PermissionTree>();
            PermissionTree perssion = null;
            for(ResourceTree res : resChildren){
                perssion = new PermissionTree();
                perssion.setId(res.getId());
                perssion.setResId(res.getId());
                perssion.setName(res.getName());
                perssion.setPid(res.getPid());
                perssion.setType(Const.PERMISSION_TYPE_RESOURCE);
                perssion.setIconCls(res.getIconCls());
                permissionTrees.add(perssion);
                t(perssion,res);
            }
            node.setChildren(permissionTrees);
        }else{
            List<BtnBean> btns = btnService.findByResId(resourceTree.getId(),false);
            List<PermissionTree> permissionTrees = new ArrayList<PermissionTree>();
            PermissionTree perssion = null;
            for(BtnBean btn : btns){
                perssion = new PermissionTree();
                perssion.setId(btn.getId());
                perssion.setResId(btn.getId());
                perssion.setName(btn.getName());
                perssion.setPid(resourceTree.getId());
                perssion.setType(Const.PERMISSION_TYPE_BUTTON);
                perssion.setIconCls(btn.getIcon());
                permissionTrees.add(perssion);
            }
            node.setChildren(permissionTrees);
        }
    }

    @Override
    public RoleBean getDefaultRole(){
        return roleDao.queryObj("name",Const.ROLE_NAME_DEFAULT);
    }

    @Override
    public RoleBean getAdminRole(){
        return roleDao.queryObj("name",Const.ROLE_NAME_ADMIN);
    }

    @Override
    public RoleBean getSupermanRole(){
        return roleDao.queryObj("name",Const.ROLE_NAME_SUPERMAN);
    }

    @Override
    public List<UserBean> getUsers(RoleBean role){
        return getUsers(role.getId());
    }

    public List<UserBean> getUsers(String roleId){
        return roleDao.getUsers(roleId);
    }
}