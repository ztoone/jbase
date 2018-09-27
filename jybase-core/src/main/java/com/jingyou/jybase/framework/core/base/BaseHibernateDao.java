package com.jingyou.jybase.framework.core.base;

import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public interface BaseHibernateDao<T,PK extends Serializable> {
    public Session getSession();
    public abstract Criteria getCriteria(Conditions cond,Page page);
    public abstract Criteria getCriteria();
    /**
     * 查询所有
     * @return
     */
    public List<T> queryAll();

    /**
     * 根据指定查询条件查询
     * @return 对象集合
     */
    public List<T> list(Criterion... criterions);
    public List<T> list(Order order,Criterion... criterions);
    public List<T> list(Criterion criterion, Order order);
    public List<T> list(Criterion[] criterions,Order[] orders);
    public List<T> list(List<Criterion> criterions,List<Order> orders);

    /**
     * 根据某一字段进行eq查询
     * @param propertyName
     * @param value
     * @return
     */
    public List<T> list(String propertyName,Object value);
    public List<T> list(String propertyName,Object value,Order order);

    /**
     * 根据某多个字段进行eq查询
     * @param map
     * @return
     */
    public List<T> list(Map<String,Object> map,Order ... orders);

    /**
     * 根据主键取对象
     * @param id
     * @return
     */
    public T findByPK(PK id);

    /**
     * 根据某一字段进行eq查询
     * @param propertyName
     * @param value
     * @return
     */
    public T queryObj(String propertyName,Object value);

    /**
     * 根据某多个字段进行eq查询
     * @param map
     * @return
     */
    public T queryObj(Map<String,Object> map);
    /**
     * 分页查询
     * @param page
     * @return
     */
    public List<T> pageQuery(Conditions cond,Page page);

    /**
     * 根据条件查询数量
     * @return
     */
    public int count(Criterion... criterions);

    /**
     * 保存新对象映射数据
     * <p>
     *
     * @param object
     *            orm类实例
     */
    public Serializable save(T object);

    public void save(List<T> objects);

    /**
     * 保存或更新对象映射数据
     * <p>
     *
     * @param object
     *            orm类实例
     */
    public void saveOrUpdate(T object);
    public void saveOrUpdate(List<T> objects);

    /**
     * 更新对象映射数据
     * <p>
     *
     * @param object
     *            orm类实例
     */
    public void update(T object);

    /**
     * 删除对象映射数据
     * <p>
     *
     * @param object
     *            orm类实例
     */
    public void delete(T object);


    /**
     * 批量删除
     * @param ids
     * @param propertyName
     * @return
     */
    public boolean deletes(String ids,String propertyName);


}
