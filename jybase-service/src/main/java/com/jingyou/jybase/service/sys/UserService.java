package com.jingyou.jybase.service.sys;

import com.jingyou.jybase.framework.core.bean.sys.BtnBean;
import com.jingyou.jybase.framework.core.bean.sys.ResourceBean;
import com.jingyou.jybase.framework.core.bean.sys.RoleBean;
import com.jingyou.jybase.framework.core.bean.sys.UserBean;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public interface UserService {

    /**
     * 取所有用户
     * @return
     */
    public List<UserBean> findAll();

    /**
     * 分页查询
     * @return
     */
    public List<UserBean> pageQuery(Conditions cond,Page page);

    /**
     * 根据主键取用户
     * @return
     */
    public UserBean findByPK(String id);

    /**
     * 取系统管理呗
     * @return
     */
    public UserBean findAdmin();
    /**
     * 根据账号名取用户
     * @return
     */
    public UserBean findByAccount(String account);


    /**
     * 添加
     * @param user
     */
    public boolean save(UserBean user);

    /**
     * 批量删除
     * @param ids
     */
    public boolean deleteByIds(String[] ids);

    /**
     * 更新
     * @param user
     */
    public boolean update(UserBean user);

    /**
     * 取用户所拥有的角色
     * @param userId
     * @return
     */
    public List<RoleBean> getRolesByUserId(String userId);

    /**
     * 取用户所拥有的菜单
     * @param userId
     * @return
     */
    public List<ResourceBean> getRessByUserId(String userId);

    /**
     * 取用户所拥有的菜单下的按钮
     * @param userId
     * @return
     */
    public List<BtnBean> getBtns(String userId,String resId);

    /**
     * 检查用户账号是否重复
     * @param account
     * @return
     */
    public boolean check(String account);

    /**
     * 改变对象状态为游离状态
     * @param user
     */
    public void evict(UserBean user);
}
