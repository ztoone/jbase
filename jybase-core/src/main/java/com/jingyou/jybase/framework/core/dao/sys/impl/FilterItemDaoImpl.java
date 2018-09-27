package com.jingyou.jybase.framework.core.dao.sys.impl;

import com.jingyou.jybase.framework.core.base.BaseHibernateDaoImpl;
import com.jingyou.jybase.framework.core.bean.sys.FilterItemBean;
import com.jingyou.jybase.framework.core.dao.sys.FilterItemDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/6/15 0015.
 */
@Transactional
@Repository
public class FilterItemDaoImpl extends BaseHibernateDaoImpl<FilterItemBean,String> implements FilterItemDao {
}
