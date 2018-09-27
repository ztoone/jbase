package com.jingyou.jybase.service.sys.impl;

import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.bean.sys.DictionaryItemBean;
import com.jingyou.jybase.framework.core.dao.sys.DictionaryItemDao;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.service.sys.DictionaryItemService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
@Transactional
@Service
public class DictionaryItemServiceImpl implements DictionaryItemService {
    private Logger logger = LoggerFactory.getLogger(DictionaryItemServiceImpl.class);
    @Autowired
    private DictionaryItemDao itemDao;

    @Override
    public List<DictionaryItemBean> pageQuery(Conditions cond,Page page){
        return itemDao.pageQuery(cond,page);
    }

    @Override
    public boolean save(DictionaryItemBean item) {
        boolean bo = false;
        try {
            itemDao.save(item);
            bo = true;
        } catch (Exception e) {
            logger.error("字典项添加失败!", e);
        }
        return bo;
    }

    @Override
    public boolean update(DictionaryItemBean item) {
        boolean bo = false;
        try {
            itemDao.update(item);
            bo = true;
        } catch (Exception e) {
            logger.error("字典项更新失败!", e);
        }
        return bo;
    }

    @Override
    public boolean deleteByIds(String[] ids) {
        return itemDao.deletes(StringUtil.toString(ids),"id");
    }

    @Override
    public boolean check(String dictId, String name) {
        Criteria criterion = itemDao.getCriteria();
        criterion.add(Restrictions.eq("dictId", dictId));
        criterion.add(Restrictions.eq("name",name));
        if(null == criterion.uniqueResult())
            return false;
        return true;
    }

    @Override
    public boolean checkCode(String dictId, String code) {
        Criteria criterion = itemDao.getCriteria();
        criterion.add(Restrictions.eq("dictId", dictId));
        criterion.add(Restrictions.eq("code",code));
        if(null == criterion.uniqueResult())
            return false;
        return true;
    }

    @Override
    public DictionaryItemBean findByPK(String id) {
        return itemDao.findByPK(id);
    }

    @Override
    public List<DictionaryItemBean> findByDictId(String dictId) {
        return itemDao.list("dictId",dictId, Order.asc("name"));
    }
}
