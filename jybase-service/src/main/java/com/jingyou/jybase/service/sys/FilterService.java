package com.jingyou.jybase.service.sys;

import com.jingyou.jybase.framework.core.bean.sys.FilterBean;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
public interface FilterService {

    public List<FilterBean> pageQuery(Conditions cond,Page page);

    public FilterBean findByPK(String id);

    public FilterBean findByClzName(String clzName);

    public boolean save(FilterBean filter);

    public boolean deleteByIds(String[] ids);

    public boolean update(FilterBean filter);

    public boolean check(String clzName);
}
