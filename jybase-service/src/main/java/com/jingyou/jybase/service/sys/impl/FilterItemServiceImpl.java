package com.jingyou.jybase.service.sys.impl;

import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.bean.sys.FilterItemBean;
import com.jingyou.jybase.framework.core.dao.sys.FilterItemDao;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.service.sys.FilterItemService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
@Transactional
@Service
public class FilterItemServiceImpl implements FilterItemService{
    private Logger logger = LoggerFactory.getLogger(FilterItemServiceImpl.class);
    @Autowired
    private FilterItemDao itemDao;

    @Override
    public List<FilterItemBean> pageQuery(Conditions cond,Page page){
        return itemDao.pageQuery(cond,page);
    }

    @Override
    public List<FilterItemBean> findByFilerId(String filterId,boolean showDisabled){
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("filterId",filterId);
        if(!showDisabled){
            param.put("enabled",1);
        }
        return itemDao.list(param, Order.asc("order"));
    }

    @Override
    public FilterItemBean findByPK(String id) {
        return itemDao.findByPK(id);
    }

    @Override
    public boolean save(FilterItemBean item) {
        boolean bo = false;
        try {
            itemDao.save(item);
            bo = true;
        } catch (Exception e) {
            logger.error("参数属性失败!", e);
        }
        return bo;
    }

    @Override
    public boolean deleteByIds(String[] ids) {
        return itemDao.deletes(StringUtil.toString(ids),"id");
    }

    @Override
    public boolean update(FilterItemBean item) {
        boolean bo = false;
        try {
            itemDao.update(item);
            bo = true;
        } catch (Exception e) {
            logger.error("属性更新失败!", e);
        }
        return bo;
    }

    @Override
    public boolean check(String filterId,String field) {
        Criteria criterion = itemDao.getCriteria();
        criterion.add(Restrictions.eq("filterId",filterId));
        criterion.add(Restrictions.eq("field",field));
        if(null == criterion.uniqueResult())
            return false;
        return true;
    }
}