package com.jingyou.jybase.framework.core.dao.sys.impl;

import com.jingyou.jybase.framework.core.base.BaseHibernateDaoImpl;
import com.jingyou.jybase.framework.core.bean.sys.OrgBean;
import com.jingyou.jybase.framework.core.dao.sys.OrgDao;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
@Transactional
@Repository
public class OrgDaoImpl extends BaseHibernateDaoImpl<OrgBean, String> implements OrgDao {
    /**
     * 取所有子机构,包括孙子节点
     * @param bsid
     * @return
     */
    public List<OrgBean> findAllChildrenByBsid(String bsid){
        String hql = "from OrgBean o where o.enabled=1 and o.bsid like '" + bsid + "%'";
        return query(hql);
    }
}
