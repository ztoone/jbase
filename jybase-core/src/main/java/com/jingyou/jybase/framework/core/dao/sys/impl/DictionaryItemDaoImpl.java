package com.jingyou.jybase.framework.core.dao.sys.impl;

import com.jingyou.jybase.framework.core.base.BaseHibernateDaoImpl;
import com.jingyou.jybase.framework.core.bean.sys.DictionaryItemBean;
import com.jingyou.jybase.framework.core.dao.sys.DictionaryItemDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
@Transactional
@Repository
public class DictionaryItemDaoImpl extends BaseHibernateDaoImpl<DictionaryItemBean,String> implements DictionaryItemDao {
    @Override
    public void deleteByDictId(String dictId){
        String hql = "delete DictionaryItemBean d where d.dictId = ?";
        getSession().createQuery(hql).setParameter(0,dictId).executeUpdate();
    }
}
