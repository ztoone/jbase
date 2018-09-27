package com.jingyou.jybase.service.sys;

import com.jingyou.jybase.framework.core.bean.sys.RoleBean;
import com.jingyou.jybase.framework.core.bean.sys.UserBean;
import com.jingyou.jybase.framework.core.tree.PermissionTree;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public interface RoleService {
    /**
     * 取所有角色
     * @return
     */
    public List<RoleBean> findAll();

    /**
     * 分页查询角色
     * @param page
     * @return
     */
    public List<RoleBean> pageQuery(Conditions cond,Page page);

    /**
     * 根据主键取角色
     * @param id
     * @return
     */
    public RoleBean findByPK(String id);

    /**
     * 保存角色
     * @param role
     * @return
     */
    public boolean save(RoleBean role);

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    public boolean deleteByIds(String[] ids);

    /**
     * 更新角色
     * @param role
     * @return
     */
    public boolean update(RoleBean role);

    /**
     * 检查角色名是否重复
     * @param name
     * @return
     */
    public boolean check(String name);

    /**
     * 取权限树
     * @return
     */
    public PermissionTree getRootNode();

    /**
     * 取默认角色
     * @return
     */
    public RoleBean getDefaultRole();

    /**
     * 取管理员角色
     * @return
     */
    public RoleBean getAdminRole();

    /**
     * 取超级管理员角色
     * @return
     */
    public RoleBean getSupermanRole();

    /**
     * 取拥有角色的所有用户
     * @return
     */
    public List<UserBean> getUsers(RoleBean role);
    public List<UserBean> getUsers(String roleId);
}
