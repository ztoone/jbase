package com.jingyou.jybase.framework.core.dao.sys.impl;

import com.jingyou.jybase.framework.core.base.BaseHibernateDaoImpl;
import com.jingyou.jybase.framework.core.bean.sys.SysParamBean;
import com.jingyou.jybase.framework.core.dao.sys.SysParamDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
@Transactional
@Repository
public class SysParamDaoImpl extends BaseHibernateDaoImpl<SysParamBean, String>  implements SysParamDao{
}
