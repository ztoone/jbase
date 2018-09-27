package com.jingyou.jybase.service.sys;

import com.jingyou.jybase.framework.core.bean.sys.BtnBean;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;

import java.util.List;

/**
 * Created by Administrator on 2016/6/6 0006.
 */
public interface BtnService {
    /**
     * 分页查询
     * @return
     */
    public List<BtnBean> pageQuery(Conditions cond,Page page);

    /**
     * 取菜单下所有按钮
     * @return
     */
    public List<BtnBean> findByResId(String resId,boolean showDisabled);

    /**
     * 根据主键取按钮
     * @return
     */
    public BtnBean findByPK(String id);

    /**
     * 添加
     * @param btn
     */
    public boolean save(BtnBean btn);

    /**
     * 批量删除
     * @param ids
     */
    public boolean deleteByIds(String[] ids);

    /**
     * 更新
     * @param btn
     */
    public boolean update(BtnBean btn);
}
