package com.jingyou.jybase.framework.core.dao.sys.impl;

import com.jingyou.jybase.framework.core.base.BaseHibernateDaoImpl;
import com.jingyou.jybase.framework.core.bean.sys.DictionaryBean;
import com.jingyou.jybase.framework.core.dao.sys.DictionaryDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
@Transactional
@Repository
public class DictionaryDaoImpl extends BaseHibernateDaoImpl<DictionaryBean,String> implements DictionaryDao {
}
