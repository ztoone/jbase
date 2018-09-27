package com.jingyou.jybase.framework.core.dao.sys.impl;

import com.jingyou.jybase.framework.core.base.BaseHibernateDaoImpl;
import com.jingyou.jybase.framework.core.bean.sys.ResourceBean;
import com.jingyou.jybase.framework.core.dao.sys.ResourceDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
@Transactional
@Repository
public class ResourceDaoImpl extends BaseHibernateDaoImpl<ResourceBean,String> implements ResourceDao {
}
