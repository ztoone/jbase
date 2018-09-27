package com.jingyou.jybase.service.sys;

import com.jingyou.jybase.framework.core.bean.sys.LogBean;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public interface LogService {
    /**
     * 分页查询
     * @return
     */
    public List<LogBean> pageQuery(Conditions cond,Page page);

    /**
     * 添加
     * @param log
     */
    public boolean save(LogBean log);

    /**
     * 批量删除
     * @param ids
     */
    public boolean deleteByIds(String[] ids);

}
