package com.jingyou.jybase.framework.core.dao.sys.impl;

import com.jingyou.jybase.framework.core.base.BaseHibernateDaoImpl;
import com.jingyou.jybase.framework.core.bean.sys.FilterBean;
import com.jingyou.jybase.framework.core.dao.sys.FilterDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/6/15 0015.
 */
@Transactional
@Repository
public class FilterDaoImpl extends BaseHibernateDaoImpl<FilterBean,String> implements FilterDao {
}
