package com.jingyou.jybase.framework.core.dao.sys.impl;

import com.jingyou.jybase.framework.core.base.BaseHibernateDaoImpl;
import com.jingyou.jybase.framework.core.bean.sys.LogBean;
import com.jingyou.jybase.framework.core.dao.sys.LogDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
@Transactional
 @Repository
public class LogDaoImpl extends BaseHibernateDaoImpl<LogBean,String> implements LogDao {
}
