package com.jingyou.jybase.service.sys;

import com.jingyou.jybase.framework.core.bean.sys.RoleResBean;

import java.util.List;

/**
 * Created by Administrator on 2016/6/8 0008.
 */
public interface RoleResService {

    /**
     * 批量新增或修改角色-资源关联关系
     * @param resBeanList
     * @return
     */
    public boolean saveOrUpdate(List<RoleResBean> resBeanList);

    /**
     * 取角色拥有的所有资源
     * @param roleId
     * @return
     */
    public List<RoleResBean> getListByRoleId(String roleId);

    /**
     * 取角色拥有的所有菜单
     * @param roleId
     * @return
     */
    public List<RoleResBean> getResourcesByRoleId(String roleId);

    /**
     * 取角色拥有的所有按钮
     * @param roleId
     * @return
     */
    public List<RoleResBean> getBtnsByRoleId(String roleId);

    /**
     * 根据角色ID，资源ID取角色-资源关系
     * @param roleId
     * @param resId
     * @return
     */
    public RoleResBean getRoleRes(String roleId,String resId);

    /**
     * 是否在数据库中存在
     * @param roleRes
     * @return
     */
    public RoleResBean getRoleRes(RoleResBean roleRes);

    /**
     * 角色分配资源
     * @param roleResList
     * @return
     */
    public boolean assignResource(List<RoleResBean> roleResList,String[] delIds);
}
