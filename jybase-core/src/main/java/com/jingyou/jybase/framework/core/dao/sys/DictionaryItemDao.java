package com.jingyou.jybase.framework.core.dao.sys;

import com.jingyou.jybase.framework.core.base.BaseHibernateDao;
import com.jingyou.jybase.framework.core.bean.sys.DictionaryItemBean;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public interface DictionaryItemDao extends BaseHibernateDao<DictionaryItemBean,String> {
    public void deleteByDictId(String dictId);
}
