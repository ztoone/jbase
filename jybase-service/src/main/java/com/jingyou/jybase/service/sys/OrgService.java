package com.jingyou.jybase.service.sys;

import com.jingyou.jybase.framework.core.bean.sys.OrgBean;
import com.jingyou.jybase.framework.core.tree.OrgTree;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public interface OrgService {

    /**
     * 获取所有机构，包括禁用的
     * @return
     */
    @Deprecated
    public List<OrgBean> findAllOrg();

    /**
     * 根据当前机构获取所有机构，包括禁用的
     * @return
     */
    public List<OrgBean> findAllOrg(OrgBean currOrg);

    /**
     * 取所有可用的机构
     * @return
     */
    @Deprecated
    public List<OrgBean> findResource();

    /**
     * 根据当前机构取所有可用的机构
     * @return
     */
    public List<OrgBean> findResource(OrgBean currOrg);
    /**
     * 取机构树根节点
     * @return
     */
    @Deprecated
    public OrgTree getRootNode(boolean showEnabled);

    /**
     * 取机构树根节点,行业根据当前登陆机构加载机构树
     * @return
     */
    public OrgTree getRootNode(OrgBean currOrg,boolean showEnabled);

    /**
     * 根据主键取机构
     * @return
     */
    public  OrgBean findByPK(String id);

    /**
     * 取机构根节点,懒加载
     * @return
     */
    public OrgBean findRoot();

    /**
     * 是否有子机构
     * @param pid
     * @return
     */
    public boolean hasChildren(String pid);

    /**
     * 取子机构
     * @param pid
     * @return
     */
    public List<OrgBean> findChildren(String pid,boolean showEnabled);

    /**
     * 取所有子机构,包括孙子节点
     * @param id
     * @return
     */
    public List<OrgBean> findAllChildrenById(String id);

    /**
     * 取所有子机构,包括孙子节点
     * @param bsid
     * @return
     */
    public List<OrgBean> findAllChildrenByBsid(String bsid);

    /**
     * 添加
     * @param org
     */
    public boolean save(OrgBean org);

    /**
     * 批量删除
     * @param ids
     */
    public boolean deleteByIds(String[] ids);

    /**
     * 更新
     * @param org
     */
    public boolean update(OrgBean org);

    /**
     * 机构下是否有用户
     * @param id
     * @return
     */
    public boolean hasUser(String id);

    /**
     * 部门编码校验，全局唯一
     * @param code
     * @return
     */
    public boolean check(String code);

    /**
     * 部门名称校验，同一部门下唯一
     * @param pid
     * @param name
     * @return
     */
    public boolean check(String pid,String name);

    /**
     * 取顶级单位
     * @param bsid
     * @return
     */
    public OrgBean getRootOrg(String bsid);

    /**
     * 取顶级单位
     * @param org
     * @return
     */
    public OrgBean getRootOrg(OrgBean org);

    /**
     * 根据bsid取单位
     * @param bsid
     * @return
     */
    public OrgBean getOrgByBsid(String bsid);
}
