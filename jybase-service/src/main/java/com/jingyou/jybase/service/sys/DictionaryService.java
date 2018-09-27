package com.jingyou.jybase.service.sys;

import com.jingyou.jybase.framework.core.bean.sys.DictionaryBean;
import com.jingyou.jybase.framework.core.tree.DictTree;

import java.util.List;

/**
 * Created by Administrator on 2016/7/8 0008.
 */
public interface DictionaryService {
    public boolean save(DictionaryBean item);
    public boolean update(DictionaryBean item);
    public boolean deleteByIds(String[] ids);
    public boolean delete(String id);
    public boolean check(String name);
    public DictionaryBean findByPK(String id);
    public List<DictionaryBean> findAll();
    public List<DictionaryBean> findChildren(String pid);
    public boolean hasChildren(String pid);
    public DictTree getRootNode();
}
