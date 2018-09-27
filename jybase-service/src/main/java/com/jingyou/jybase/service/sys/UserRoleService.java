package com.jingyou.jybase.service.sys;

import com.jingyou.jybase.framework.core.bean.sys.UserRoleBean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public interface UserRoleService {
    /**
     * 批量新增或修改用户-角色关联关系
     * @param userRoles
     * @return
     */
    public boolean saveOrUpdate(List<UserRoleBean> userRoles);

    /**
     * 取用户拥有的所有角色
     * @param userId
     * @return
     */
    public List<UserRoleBean> getListByUserId(String userId);

    /**
     * 根据用户ID，角色ID取用户-角色关联关系
     * @param userId
     * @param roleId
     * @return
     */
    public UserRoleBean getUserRole(String userId,String roleId);

    public UserRoleBean getUserRole(UserRoleBean userRole);

    /**
     * 分配角色
     * @param userRoles
     * @param delIds
     * @return
     */
    public boolean assginRole(List<UserRoleBean> userRoles,String[] delIds);
}
