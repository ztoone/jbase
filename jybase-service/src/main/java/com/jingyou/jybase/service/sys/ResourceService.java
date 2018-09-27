package com.jingyou.jybase.service.sys;

import com.jingyou.jybase.framework.core.bean.sys.ResourceBean;
import com.jingyou.jybase.framework.core.tree.ResourceTree;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public interface ResourceService {

    /**
     * 获取所有资源,包括禁用的
     * @return
     */
    public List<ResourceBean> findAllResource();

    /**
     * 获取所有可用的资源
     * @return
     */
    public List<ResourceBean> findResource();

    /**
     * 取资源树根节点
     * @return
     */
    public  ResourceTree getRootNode(boolean showEnabled);

    public ResourceTree getRootNode(List<ResourceBean> resources);
    /**
     * 根据主键取资源
     * @return
     */
    public  ResourceBean findByPK(String id);

    /**
     * 取资源根节点,懒加载
     * @return
     */
    public ResourceBean findRoot();

    /**
     * 是否有子节点
     * @param pid
     * @return
     */
    public boolean hasChildren(String pid);

    /**
     * 取子资源
     * @param pid
     * @return
     */
    public List<ResourceBean> findChildren(String pid);

    /**
     * 添加菜单
     * @param resourceBean
     */
    public boolean save(ResourceBean resourceBean);

    /**
     * 批量删除菜单
     * @param ids
     */
    public boolean deleteByIds(String[] ids);

    /**
     * 更新菜单
     * @param resourceBean
     */
    public boolean update(ResourceBean resourceBean);

    /**
     * 根据URL取菜单
     * @param url
     * @return
     */
    public ResourceBean findByUrl(String url);

}
