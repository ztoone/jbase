package com.jingyou.jybase.service.sys.impl;

import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.bean.sys.SysParamBean;
import com.jingyou.jybase.framework.core.dao.sys.SysParamDao;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.service.sys.SysParamService;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
public class SysParamServiceImpl implements SysParamService{
    private Logger logger = LoggerFactory.getLogger(OrgServiceImpl.class);
    @Autowired
    private SysParamDao sysParamDao;

    @Override
    public List<SysParamBean> pageQuery(Conditions cond,Page page){
        return sysParamDao.pageQuery(cond,page);
    }

    @Override
    public List<SysParamBean> findAll() {
        return sysParamDao.queryAll();
    }

    @Override
    public SysParamBean findByPK(String id) {
        return sysParamDao.findByPK(id);
    }

    @Override
    public boolean save(SysParamBean param) {
        boolean bo = false;
        try {
            sysParamDao.save(param);
            bo = true;
        } catch (Exception e) {
            logger.error("参数添加失败!", e);
        }
        return bo;
    }

    @Override
    public boolean deleteByIds(String[] ids) {
        return sysParamDao.deletes(StringUtil.toString(ids),"id");
    }

    @Override
    public boolean update(SysParamBean param) {
        boolean bo = false;
        try {
            sysParamDao.update(param);
            bo = true;
        } catch (Exception e) {
            logger.error("角色更新失败!", e);
        }
        return bo;
    }

    @Override
    public boolean check(String key) {
        Criteria criterion = sysParamDao.getCriteria();
        criterion.add(Restrictions.eq("key",key));
        if(null == criterion.uniqueResult())
            return false;
        return true;
    }

    @Override
    public String getValueByKey(String key){
        Criteria criterion = sysParamDao.getCriteria();
        criterion.add(Restrictions.eq("key",key));
        Object obj = criterion.uniqueResult();
        if(null == obj)
            return null;
        return ((SysParamBean)obj).getValue();
    }
}