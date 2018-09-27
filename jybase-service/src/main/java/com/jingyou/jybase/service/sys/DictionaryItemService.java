package com.jingyou.jybase.service.sys;

import com.jingyou.jybase.framework.core.bean.sys.DictionaryItemBean;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public interface DictionaryItemService {
    public List<DictionaryItemBean> pageQuery(Conditions cond,Page page);
    public boolean save(DictionaryItemBean item);
    public boolean update(DictionaryItemBean item);
    public boolean deleteByIds(String[] ids);
    public boolean check(String dictId,String name);
    public boolean checkCode(String dictId,String code);
    public DictionaryItemBean findByPK(String id);
    public List<DictionaryItemBean> findByDictId(String dictId);
}
