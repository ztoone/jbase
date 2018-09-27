package com.jingyou.jybase.service.sys.impl;

import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.bean.sys.LogBean;
import com.jingyou.jybase.framework.core.dao.sys.LogDao;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.service.sys.LogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
@Transactional
@Service
public class LogServiceImpl implements LogService {
    private Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired
    private LogDao logDao;
    @Override
    public List<LogBean> pageQuery(Conditions cond,Page page) {
        return logDao.pageQuery(cond,page);
    }

    @Override
    public boolean save(LogBean log) {
        boolean bo = false;
        try {
            logDao.save(log);
            bo = true;
        } catch (Exception e) {
            logger.error("日志记录失败!", e);
        }
        return bo;
    }

    @Override
    public boolean deleteByIds(String[] ids) {
        return logDao.deletes(StringUtil.toString(ids),"id");
    }
}
