package com.jingyou.jybase.framework.core.dao.sys;

import com.jingyou.jybase.framework.core.base.BaseHibernateDao;
import com.jingyou.jybase.framework.core.bean.sys.OrgBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public interface OrgDao extends BaseHibernateDao<OrgBean,String>{
    /**
     * 取所有子机构,包括孙子节点
     * @param bsid
     * @return
     */
    public List<OrgBean> findAllChildrenByBsid(String bsid);
}
