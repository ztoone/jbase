package com.jingyou.jybase.service.sys;

import com.jingyou.jybase.framework.core.bean.sys.SysParamBean;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
public interface SysParamService {

    public List<SysParamBean> findAll();

    public List<SysParamBean> pageQuery(Conditions cond,Page page);

    public SysParamBean findByPK(String id);

    public boolean save(SysParamBean param);

    public boolean deleteByIds(String[] ids);

    public boolean update(SysParamBean param);

    public boolean check(String key);

    public String getValueByKey(String key);
}
