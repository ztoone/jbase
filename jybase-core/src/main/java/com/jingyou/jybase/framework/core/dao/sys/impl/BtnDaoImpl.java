package com.jingyou.jybase.framework.core.dao.sys.impl;

import com.jingyou.jybase.framework.core.base.BaseHibernateDaoImpl;
import com.jingyou.jybase.framework.core.bean.sys.BtnBean;
import com.jingyou.jybase.framework.core.dao.sys.BtnDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/6/6 0006.
 */
@Transactional
@Repository
public class BtnDaoImpl extends BaseHibernateDaoImpl<BtnBean,String> implements BtnDao{
}
