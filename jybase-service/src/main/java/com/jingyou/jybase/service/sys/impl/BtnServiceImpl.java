package com.jingyou.jybase.service.sys.impl;

import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.bean.sys.BtnBean;
import com.jingyou.jybase.framework.core.dao.sys.BtnDao;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.service.sys.BtnService;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/6 0006.
 */
@Transactional
@Service
public class BtnServiceImpl implements BtnService {
    private Logger logger = LoggerFactory.getLogger(BtnServiceImpl.class);
    @Autowired
    private BtnDao btnDao;

    @Override
    public List<BtnBean> pageQuery(Conditions cond,Page page){
        return btnDao.pageQuery(cond,page);
    }

    @Override
    public List<BtnBean> findByResId(String resId,boolean showDisabled) {
        Map<String,Object> param = new HashMap<String, Object>();
        param.put("resId",resId);
        if(!showDisabled){
            param.put("enabled",1);
        }
        return btnDao.list(param, Order.asc("order"));
    }

    @Override
    public BtnBean findByPK(String id) {
        return btnDao.findByPK(id);
    }

    @Override
    public boolean save(BtnBean btn) {
        boolean bo = false;
        try {
            btnDao.save(btn);
            bo = true;
        } catch (Exception e) {
            logger.error("按钮添加失败!", e);
        }
        return bo;
    }

    @Override
    public boolean deleteByIds(String[] ids) {
        return btnDao.deletes(StringUtil.toString(ids), "id");
    }

    @Override
    public boolean update(BtnBean btn) {
        boolean bo = false;
        try {
            btnDao.update(btn);
            bo = true;
        } catch (Exception e) {
            logger.error("按钮更新失败!", e);
        }
        return bo;
    }
}
