package com.jingyou.jybase.framework.core.dao.sys;

import com.jingyou.jybase.framework.core.base.BaseHibernateDao;
import com.jingyou.jybase.framework.core.bean.sys.BtnBean;
import com.jingyou.jybase.framework.core.bean.sys.ResourceBean;
import com.jingyou.jybase.framework.core.bean.sys.RoleBean;
import com.jingyou.jybase.framework.core.bean.sys.UserBean;

import java.util.List;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public interface UserDao extends BaseHibernateDao<UserBean,String>{
    public List<RoleBean> getRolesByUserId(String userId);

    public List<ResourceBean> getRessByUserId(String userId);

    public List<BtnBean> getBtns(String userId,String resId);
}
