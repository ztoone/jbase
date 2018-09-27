package com.jingyou.jybase.service.sys.impl;

import com.jingyou.jybase.common.Const;
import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.bean.sys.OrgBean;
import com.jingyou.jybase.framework.core.dao.sys.OrgDao;
import com.jingyou.jybase.framework.core.dao.sys.SysParamDao;
import com.jingyou.jybase.framework.core.dao.sys.UserDao;
import com.jingyou.jybase.framework.core.tree.OrgTree;
import com.jingyou.jybase.service.sys.OrgService;
import com.jingyou.jybase.service.sys.SysParamService;
import com.jingyou.jybase.service.util.OrgUtil;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
@Transactional
@Service
public class OrgServiceImpl implements OrgService {
    private Logger logger = LoggerFactory.getLogger(OrgServiceImpl.class);
    @Autowired
    private OrgDao orgDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private SysParamService sysParamService;
    @Override
    @Deprecated
    public List<OrgBean> findAllOrg(){
        List<OrgBean> orgs = orgDao.list("deleted", 0, Order.asc("code"));
        return orgs;
    }

    @Override
    public List<OrgBean> findAllOrg(OrgBean currOrg){
        List<OrgBean> orgs = null;
        if(currOrg.getId().equals("root") || sysParamService.getValueByKey("systemType").equals("A")){//如果是根节点或者是公司报表
            orgs = orgDao.list(Order.asc("code"),Restrictions.eq("deleted",0));
        }else{
            String lkBsid = currOrg.getBsid().substring(0,9);
            orgs = orgDao.list(Order.asc("code"),Restrictions.eq("deleted",0),Restrictions.like("bsid",lkBsid+"%"));
        }
        return orgs;
    }

    @Override
    @Deprecated
    public List<OrgBean> findResource(){
        List<OrgBean> orgs = orgDao.list(Order.asc("code"),Restrictions.eq("deleted", 0),Restrictions.eq("enabled", 1));
        return orgs;
    }

    @Override
    public List<OrgBean> findResource(OrgBean currOrg){
        List<OrgBean> orgs = null;
        if(currOrg.getId().equals("root") || sysParamService.getValueByKey("systemType").equals("A")){//如果是根节点或者是公司报表
            orgs = orgDao.list(Order.asc("code"),Restrictions.eq("deleted", 0),Restrictions.eq("enabled", 1));
        }else{
            String lkBsid = currOrg.getBsid().substring(0,9);
            orgs = orgDao.list(Order.asc("code"),Restrictions.eq("deleted",0),Restrictions.eq("enabled", 1),Restrictions.like("bsid",lkBsid+"%"));
        }
        return orgs;
    }

    @Override
    public OrgTree getRootNode(boolean showEnabled){
        List<OrgBean> orgs = null;
        if(showEnabled)
            orgs = findAllOrg();
        else
            orgs = findResource();
        OrgTree root = null;
        for(OrgBean res : orgs){
            if(res.getId().equals("root")){
                root = getOrgTree(res,orgs);
                break;
            }
        }
        return root;
    }


    @Override
    public OrgTree getRootNode(OrgBean currOrg,boolean showEnabled){
        List<OrgBean> orgs = null;
        if(showEnabled)
            orgs = findAllOrg(currOrg);
        else
            orgs = findResource(currOrg);
        OrgTree root = null;
        String systemType = sysParamService.getValueByKey("systemType");
        if(systemType.equals("B")){//行业
            if(currOrg.getId().equals("root")){//当前是顶级机构
                for(OrgBean res : orgs) {
                    if(res.getId().equals("root")){
                        root = getOrgTree(res,orgs);
                        break;
                    }
                }
            }else{
                for(OrgBean res : orgs) {
                    if(res.getPid().equals("root")){
                        root = getOrgTree(res,orgs);
                        break;
                    }
                }
            }
        }else{//公司
            for(OrgBean res : orgs) {
                if(res.getId().equals("root")){
                    root = getOrgTree(res,orgs);
                    break;
                }
            }
        }
        return root;
    }

    @Override
    public  OrgBean findByPK(String id){
        return orgDao.findByPK(id);
    }

    @Override
    public OrgBean findRoot(){
        return findByPK(Const.TREE_ROOT_ID);
    }

    @Override
    public List<OrgBean> findChildren(String pid,boolean showEnabled){
        Map<String,Object> conds = new HashMap<String, Object>();
        conds.put("pid",pid);
        conds.put("deleted",0);
        if(!showEnabled){
            conds.put("enabled",1);
        }
        return orgDao.list(conds);
    }

    @Override
    public List<OrgBean> findAllChildrenById(String id){
        return orgDao.findAllChildrenByBsid(findByPK(id).getBsid());
    }

    @Override
    public List<OrgBean> findAllChildrenByBsid(String bsid){
        return orgDao.findAllChildrenByBsid(bsid);
    }

    private OrgTree getOrgTree(OrgBean org,List<OrgBean> orgs){
        OrgTree tree = new OrgTree();
        tree.setId(org.getId());
        tree.setName(org.getName());
        tree.setCode(org.getCode());
        tree.setBsid(org.getBsid());
        tree.setPid(org.getPid());
        tree.setType(org.getType());
        tree.setEnabled(org.getEnabled());
        tree.setDeleted(org.getDeleted());
        tree.setDesc(org.getDesc());
        tree.setIconCls(org.getLogo());
        tree.setChildren(getChildren(tree.getId(),orgs));
        return tree;
    }

    private List<OrgTree> getChildren(String pid,List<OrgBean> orgs){
        List<OrgTree> children = new ArrayList<OrgTree>();
        OrgTree tree = null;
        for(OrgBean org : orgs){
            if(!StringUtil.isEmpty(org.getPid()) && org.getPid().equals(pid)){
                tree = getOrgTree(org, orgs);
                children.add(tree);
            }
        }
        return children;
    }

    @Override
    public boolean hasChildren(String pid){
        List<OrgBean> children = findChildren(pid,false);
        if(children !=null && children.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean save(OrgBean org){
        boolean bo = false;
        try {
            OrgUtil.getInstance().genBsid(org);
            orgDao.save(org);
            bo = true;
        } catch (Exception e) {
            logger.error("机构添加失败!", e);
        }
        return bo;
    }

    @Override
    public boolean deleteByIds(String[] ids){
        OrgBean org = orgDao.findByPK(ids[0]);
        if(org == null)
            return false;
        org.setDeleted(1);
       return update(org);
    }

    @Override
    public boolean update(OrgBean org){
        boolean bo = false;
        try {
            orgDao.update(org);
            bo = true;
        } catch (Exception e) {
            logger.error("机构更新失败!", e);
        }
        return bo;
    }

    @Override
    public boolean hasUser(String id){
        int count = userDao.count(Restrictions.eq("orgId",id));
        if(count > 0)
            return true;
        return false;
    }

    @Override
    public boolean check(String code) {
        Criteria criterion = orgDao.getCriteria();
        criterion.add(Restrictions.eq("deleted",0));
        criterion.add(Restrictions.eq("code",code));
        if(null == criterion.uniqueResult())
            return false;
        return true;
    }

    @Override
    public boolean check(String pid, String name) {
        Criteria criterion = orgDao.getCriteria();
        criterion.add(Restrictions.eq("name",name));
        criterion.add(Restrictions.eq("deleted",0));
        criterion.add(Restrictions.eq("pid",pid));
        if(null == criterion.uniqueResult())
            return false;
        return true;
    }

    public OrgBean getRootOrg(String bsid){
        String topBsid = bsid.substring(0,9);
        return getOrgByBsid(topBsid);
    }

    public OrgBean getRootOrg(OrgBean org){
        if(org.getId().equals("root")){
            return  orgDao.findByPK("root");
        }
        return getRootOrg(org.getBsid());
    }

    public OrgBean getOrgByBsid(String bsid){
        return orgDao.queryObj("bsid",bsid);
    }
}
