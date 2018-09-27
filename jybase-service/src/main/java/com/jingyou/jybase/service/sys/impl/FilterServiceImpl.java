package com.jingyou.jybase.service.sys.impl;

import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.bean.sys.FilterBean;
import com.jingyou.jybase.framework.core.dao.sys.FilterDao;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.service.sys.FilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
@Transactional
@Service
public class FilterServiceImpl implements FilterService{
    private Logger logger = LoggerFactory.getLogger(FilterServiceImpl.class);
    @Autowired
    private FilterDao filterDao;

    @Override
    public List<FilterBean> pageQuery(Conditions cond,Page page){
        return filterDao.pageQuery(cond,page);
    }

    @Override
    public FilterBean findByPK(String id) {
        return filterDao.findByPK(id);
    }

    @Override
    public boolean save(FilterBean filter) {
        boolean bo = false;
        try {
            filterDao.save(filter);
            bo = true;
        } catch (Exception e) {
            logger.error("参数添加失败!", e);
        }
        return bo;
    }

    @Override
    public FilterBean findByClzName(String clzName){
        return filterDao.queryObj("clzName",clzName);
    }

    @Override
    public boolean deleteByIds(String[] ids) {
        return filterDao.deletes(StringUtil.toString(ids),"id");
    }

    @Override
    public boolean update(FilterBean filter) {
        boolean bo = false;
        try {
            filterDao.update(filter);
            bo = true;
        } catch (Exception e) {
            logger.error("角色更新失败!", e);
        }
        return bo;
    }

    @Override
    public boolean check(String clzName) {
        if(null == findByClzName(clzName))
            return false;
        return true;
    }
}