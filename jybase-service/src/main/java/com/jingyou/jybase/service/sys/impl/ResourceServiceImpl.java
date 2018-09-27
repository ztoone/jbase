package com.jingyou.jybase.service.sys.impl;

import com.jingyou.jybase.common.Const;
import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.bean.sys.ResourceBean;
import com.jingyou.jybase.framework.core.dao.sys.ResourceDao;
import com.jingyou.jybase.framework.core.tree.ResourceTree;
import com.jingyou.jybase.service.sys.ResourceService;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
@Transactional
@Service
public class ResourceServiceImpl implements ResourceService {
    private Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);
    @Autowired
    private ResourceDao resourceDao;

    @Override
    public List<ResourceBean> findAllResource(){
        List<ResourceBean> resources = resourceDao.list(Restrictions.eq("deleted", 0), Order.asc("order"));
        return resources;
    }

    @Override
    public List<ResourceBean> findResource(){
        Criterion[] criterions = new Criterion[2];
        criterions[0] = Restrictions.eq("deleted",0);
        criterions[1] = Restrictions.eq("enabled",1);
        Order[] orders = new Order[1];
        orders[0] = Order.asc("order");
        List<ResourceBean> resources = resourceDao.list(criterions,orders);
        return resources;
    }

    @Override
    public ResourceTree getRootNode(boolean showEnabled){
        List<ResourceBean> resources = null;
        if(showEnabled)
            resources = findAllResource();
        else
            resources = findResource();
        ResourceTree root = null;
        for(ResourceBean res : resources){
            if(res.getId().equals("root")){
                root = getResourceTree(res,resources);
                break;
            }
        }
        return root;
    }

    @Override
    public ResourceTree getRootNode(List<ResourceBean> resources){
        ResourceTree root = null;
        for(ResourceBean res : resources){
            if(res.getId().equals("root")){
                root = getResourceTree(res,resources);
                break;
            }
        }
        return root;
    }

    @Override
    public  ResourceBean findByPK(String id){
        return resourceDao.findByPK(id);
    }

    @Override
    public ResourceBean findRoot(){
        return findByPK(Const.TREE_ROOT_ID);
    }

    @Override
    public List<ResourceBean> findChildren(String pid){
        return  resourceDao.list(Order.asc("order"),Restrictions.eq("pid",pid),Restrictions.eq("deleted",0));
    }

    private ResourceTree getResourceTree(ResourceBean res,List<ResourceBean> resources){
        ResourceTree tree = new ResourceTree();
        tree.setId(res.getId());
        tree.setName(res.getName());
        tree.setUrl(res.getUrl());
        tree.setIconCls(res.getIcon());
        tree.setDesc(res.getDesc());
        tree.setPid(res.getPid());
        tree.setEnabled(res.getEnabled());
        tree.setDeleted(res.getDeleted());
        tree.setChildren(getChildren(tree.getId(),resources));
        tree.setOrder(res.getOrder());
        return tree;
    }

    private List<ResourceTree> getChildren(String pid,List<ResourceBean> resources){
        List<ResourceTree> children = new ArrayList<ResourceTree>();
        ResourceTree tree = null;
        for(ResourceBean res : resources){
            if(!StringUtil.isEmpty(res.getPid()) && res.getPid().equals(pid)){
                tree = getResourceTree(res,resources);
                children.add(tree);
            }
        }
        return children;
    }

    @Override
    public boolean hasChildren(String pid){
        List<ResourceBean> children = findChildren(pid);
        if(children !=null && children.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean save(ResourceBean resourceBean){
        boolean bo = false;
        try {
            resourceDao.save(resourceBean);
            bo = true;
        } catch (Exception e) {
            logger.error("菜单添加失败!", e);
        }
        return bo;
    }

    @Override
    public boolean deleteByIds(String[] ids){
        ResourceBean res = resourceDao.findByPK(ids[0]);
        if (res == null)
            return false;
        res.setDeleted(1);
        return update(res);
    }

    @Override
    public boolean update(ResourceBean resourceBean){
        boolean bo = false;
        try {
            resourceDao.update(resourceBean);
            bo = true;
        } catch (Exception e) {
            logger.error("菜单更新失败!", e);
        }
        return bo;
    }

    @Override
    public ResourceBean findByUrl(String url){
        return resourceDao.queryObj("url",url);
    }
}
