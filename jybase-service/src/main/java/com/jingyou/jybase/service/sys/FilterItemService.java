package com.jingyou.jybase.service.sys;

import com.jingyou.jybase.framework.core.bean.sys.FilterItemBean;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
public interface FilterItemService {
    /**
     * 分页查询
     * @return
     */
    public List<FilterItemBean> pageQuery(Conditions cond,Page page);

    public List<FilterItemBean> findByFilerId(String filterId,boolean showDisabled);

    public FilterItemBean findByPK(String id);

    public boolean save(FilterItemBean item);

    public boolean deleteByIds(String[] ids);

    public boolean update(FilterItemBean item);

    public boolean check(String filterId,String field);
}
