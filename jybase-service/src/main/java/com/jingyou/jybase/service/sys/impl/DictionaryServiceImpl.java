package com.jingyou.jybase.service.sys.impl;

import com.jingyou.jybase.common.Const;
import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.bean.sys.DictionaryBean;
import com.jingyou.jybase.framework.core.dao.sys.DictionaryDao;
import com.jingyou.jybase.framework.core.dao.sys.DictionaryItemDao;
import com.jingyou.jybase.framework.core.tree.DictTree;
import com.jingyou.jybase.service.sys.DictionaryService;
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
 * Created by Administrator on 2016/7/8 0008.
 */
@Transactional
@Service
public class DictionaryServiceImpl implements DictionaryService {
    private Logger logger = LoggerFactory.getLogger(DictionaryServiceImpl.class);
    @Autowired
    private DictionaryDao dictionaryDao;
    @Autowired
    private DictionaryItemDao itemDao;
    @Override
    public boolean save(DictionaryBean dict) {
        boolean bo = false;
        try {
            dictionaryDao.save(dict);
            bo = true;
        } catch (Exception e) {
            logger.error("字典添加失败!", e);
        }
        return bo;
    }

    @Override
    public boolean update(DictionaryBean dict) {
        boolean bo = false;
        try {
            dictionaryDao.update(dict);
            bo = true;
        } catch (Exception e) {
            logger.error("字典更新失败!", e);
        }
        return bo;
    }

    @Override
    public boolean deleteByIds(String[] ids) {
        return dictionaryDao.deletes(StringUtil.toString(ids),"id");
    }

    @Override
    public boolean delete(String id) {
        boolean bo = false;
        try {
            dictionaryDao.delete(dictionaryDao.findByPK(id));
            itemDao.deleteByDictId(id);
            bo = true;
        } catch (Exception e) {
            logger.error("字典删除失败!", e);
        }
        return bo;
    }

    @Override
    public boolean check(String name) {
        Criteria criterion = dictionaryDao.getCriteria();
        criterion.add(Restrictions.eq("name",name));
        criterion.add(Restrictions.eq("pid", Const.TREE_ROOT_ID));
        if(null == criterion.uniqueResult())
            return false;
        return true;
    }

    @Override
    public DictionaryBean findByPK(String id) {
        return dictionaryDao.findByPK(id);
    }

    @Override
    public List<DictionaryBean> findAll() {
        return dictionaryDao.queryAll();
    }

    @Override
    public List<DictionaryBean> findChildren(String pid) {
        return dictionaryDao.list("pid",pid);
    }

    @Override
    public boolean hasChildren(String pid){
        List<DictionaryBean> children = findChildren(pid);
        if(children !=null && children.size() > 0){
            return true;
        }
        return false;
    }

    @Override
    public DictTree getRootNode() {
        List<DictionaryBean> dicts = findAll();
        DictTree root = null;
        for(DictionaryBean dict : dicts){
            if(dict.getId().equals(Const.TREE_ROOT_ID)){
                root = getDictTree(dict, dicts);
                break;
            }
        }
        return root;
    }

    private DictTree getDictTree(DictionaryBean dict,List<DictionaryBean> dicts){
        DictTree tree = new DictTree();
        tree.setId(dict.getId());
        tree.setName(dict.getName());
        tree.setPid(dict.getPid());
        tree.setChildren(getChildren(tree.getId(),dicts));
        return tree;
    }

    private List<DictTree> getChildren(String pid,List<DictionaryBean> dicts){
        List<DictTree> children = new ArrayList<DictTree>();
        DictTree tree = null;
        for(DictionaryBean dict : dicts){
            if(!StringUtil.isEmpty(dict.getPid()) && dict.getPid().equals(pid)){
                tree = getDictTree(dict, dicts);
                children.add(tree);
            }
        }
        return children;
    }
}
