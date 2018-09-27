package com.jingyou.jybase.framework.core.base;

import com.jingyou.jybase.common.util.ObjectUtil;
import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.hibernate.query.Condition;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA. User: noah Date: 4/27/13 Time: 4:24 PM To change
 * this template use File | Settings | File Templates.
 */

@Repository
@Transactional
@SuppressWarnings("unchecked")
public  class BaseHibernateDaoImpl<T,PK extends Serializable> implements BaseHibernateDao<T,PK> {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    private  Class<T> entityClass;
    protected SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public  BaseHibernateDaoImpl() {

        Class c = getClass();
        Type type = c.getGenericSuperclass();
        if ((type instanceof ParameterizedType)) {
            Type[] parameterizedType = ((ParameterizedType)type).getActualTypeArguments();
            this.entityClass = ((Class)parameterizedType[0]);
        }
    }

    @Override
    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    /**
     * 获取查询对象Criteria
     * @return
     */
    @Override
    public Criteria getCriteria(Conditions cond,Page page){
        Criteria criteria = getCriteria();
        List<Condition> conds = cond.getConds();
        if(conds != null && conds.size() > 0){
            for(Condition c : conds){
                if(ObjectUtil.isNotEmpty(c.getValues()) && !StringUtil.isBlank(c.getValues().get(0))){
                    analyseCond(c,page);
                }
            }
        }
        return criteria;
    }

    @Override
    public Criteria getCriteria(){
        return getSession().createCriteria(entityClass);
    }

    private void analyseCond(Condition cond,Page page){
        Object value = null;
        try {
            Field field = entityClass.getDeclaredField(cond.getProperty());
            Class<?> fieldType = field.getType();
                if (fieldType == Integer.class|| fieldType == int.class) {
                    value = Integer.parseInt(cond.getValues().get(0));
                }else if(fieldType == long.class || fieldType == Long.class){
                    value = Long.parseLong(cond.getValues().get(0));
                }else if(fieldType == Date.class){
                    value = dataFormat.parse(cond.getValues().get(0));
                }else {
                    value = cond.getValues().get(0);
                }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        switch(cond.getRestriction()){
            case Like:
                if(!ObjectUtil.isNull(value)){
                    page.addCriterion(Restrictions.like(cond.getProperty(),"%" + cond.getValues().get(0) + "%"));
                }
                break;
            case Eq:
                if(!ObjectUtil.isNull(value)){
                    page.addCriterion(Restrictions.eq(cond.getProperty(),value));
                }
                break;
            case Between:
                if(!ObjectUtil.isNull(value) && !ObjectUtil.isNull(cond.getValues().get(1))){
                    page.addCriterion(Restrictions.between(cond.getProperty(),cond.getValues().get(0),cond.getValues().get(1)));
                }
                break;
            case Gt:
                if(!ObjectUtil.isNull(value)){
                    page.addCriterion(Restrictions.gt(cond.getProperty(), value));
                }
                break;
            case Lt:
                if(!ObjectUtil.isNull(value)){
                    page.addCriterion(Restrictions.lt(cond.getProperty(), value));
                }
                break;
            case Ge:
                if(!ObjectUtil.isNull(value)){
                    page.addCriterion(Restrictions.ge(cond.getProperty(), value));
                }
                break;
            case Le:
                if(!ObjectUtil.isNull(value)){
                    page.addCriterion(Restrictions.le(cond.getProperty(), value));
                }
                break;
            case LLike:
                if(!ObjectUtil.isNull(value)){
                    page.addCriterion(Restrictions.like(cond.getProperty(),"%" + cond.getValues().get(0)));
                }
                break;
            case RLike:
                if(!ObjectUtil.isNull(value)){
                    page.addCriterion(Restrictions.like(cond.getProperty(),cond.getValues().get(0) + "%"));
                }
                break;
        }
    }

    @Override
    public List<T> queryAll() {
        return getSession().createCriteria(entityClass).list();
    }

    @Override
    public T findByPK(PK id){
        return (T) getSession().createCriteria(entityClass).add(Restrictions.idEq(id)).uniqueResult();
    }

    @Override
    public List<T> list(String propertyName, Object value) {
        Criteria criteria = getSession().createCriteria(entityClass).add(Restrictions.eq(propertyName, value));
        return criteria.list();
    }

    @Override
    public List<T> list(String propertyName, Object value,Order order) {
        Criteria criteria = getSession().createCriteria(entityClass).add(Restrictions.eq(propertyName, value));
        criteria.addOrder(order);
        return criteria.list();
    }


    @Override
    public List<T> list(Criterion... criterions) {
        Criteria criteria = getCriteria();
        for(Criterion criterion : criterions)
            criteria.add(criterion);
        return criteria.list();
    }

    @Override
    public List<T> list(Order order,Criterion... criterions) {
        Criteria criteria = getCriteria();
        for(Criterion criterion : criterions)
            criteria.add(criterion);
        criteria.addOrder(order);
        return criteria.list();
    }

    @Override
    public List<T> list(Criterion criterion, Order order){
        Criteria criteria = getCriteria();
        criteria.add(criterion);
        criteria.addOrder(order);
        return criteria.list();
    }

    @Override
    public List<T> list(Criterion[] criterions,Order[] orders){
        Criteria criteria = getCriteria();
        for(Criterion criterion : criterions)
            criteria.add(criterion);
        for(Order order : orders)
            criteria.addOrder(order);
        return criteria.list();
    }

    @Override
    public List<T> list(List<Criterion> criterions,List<Order> orders){
        Criteria criteria = getCriteria();
        for(Criterion criterion : criterions)
            criteria.add(criterion);
        for(Order order : orders)
            criteria.addOrder(order);
        return criteria.list();
    }

    @Override
    public T queryObj(String propertyName, Object value) {
        Criteria criteria = getSession().createCriteria(entityClass).add(Restrictions.eq(propertyName, value));
        return (T) criteria.uniqueResult();
    }

    @Override
    public List<T> list(Map<String,Object> map,Order ... orders) {
        Criteria criteria = getSession().createCriteria(entityClass);
        Set<String> sets =  map.keySet();
        for(String obj : sets){
            criteria = criteria.add(Restrictions.eq(obj.toString(), map.get(obj)));
        }
        for(Order order : orders)
            criteria.addOrder(order);
        return criteria.list();
    }

    @Override
    public T queryObj(Map<String, Object> map) {
        Criteria criteria = getSession().createCriteria(entityClass);
        Set<String> sets =  map.keySet();
        for(String obj : sets){
            criteria = criteria.add(Restrictions.eq(obj.toString(), map.get(obj)));
        }
        return (T) criteria.uniqueResult();
    }

    public int count(Criterion... criterions){
        Criteria criteria = getCriteria();
        for(Criterion criterion : criterions)
            criteria.add(criterion);
        criteria.setProjection(Projections.rowCount());
        Long total = (Long) criteria.uniqueResult();
        return total.intValue();
    }

    @Override
    public List<T> pageQuery(Conditions cond,Page page){
        Criteria criteria = getCriteria(cond,page);
        if(page.getPage() <= 1)
            criteria.setFirstResult(0);
        else
            criteria.setFirstResult((page.getPage()-1)*page.getRows());
        criteria.setMaxResults(page.getRows());
        if(page.getOrderBy()!=null){
            criteria.addOrder(page.getOrderBy());
        }
        if(page.getOrders().size()>0){
            for(Order order : page.getOrders()){
                criteria.addOrder(order);
            }
        }
        if(page.getCriterions().size()>0){
            for(Criterion criterion : page.getCriterions()){
                criteria.add(criterion);
            }
        }

        if(ObjectUtil.isNotNull(page.getCriterions()) && ObjectUtil.isNotEmpty(page.getCriterions()))
            page.setTotalCount(count(page.getCriterions().toArray(new Criterion[]{})));
        else
            page.setTotalCount(count());
        return criteria.list();
    }

    @Override
    public void delete(T object) {
        if (object!=null) {
            getSession().delete(object);
        }
    }


    @Override
    public boolean deletes(String ids,String propertyName){
        boolean bo = false;
        try{
            bo = true;
            StringBuffer hql = new StringBuffer(" delete ");
            hql.append(entityClass.getName());
            hql.append(" where ");
            hql.append(propertyName);
            hql.append(" in (");
            hql.append(ids);
            hql.append(")");
            getSession().createQuery(hql.toString()).executeUpdate();
        }catch (Exception e){
            logger.error("批量删除失败!",e);
        }
        return bo;
    }


    @Override
    public Serializable save(T object) {
        return (PK) getSession().save(object);
    }

    @Override
    public void save(List<T> objects) {
        for (T obj : objects) {
            try {
                this.save(obj);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("批量新增失败!");
            }
        }
    }

    @Override
    public void saveOrUpdate(T object) {
        getSession().saveOrUpdate(object);
    }

    @Override
    public void saveOrUpdate(List<T> objects) {
        for (T obj : objects) {
            try {
                this.saveOrUpdate(obj);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("批量新增或修改失败!");
            }
        }
    }

    @Override
    public void update(T object) {
        getSession().update(object);
    }

    protected T find(String hql, Map<String, Object> params) {
        Query query = getSession().createQuery(hql);
        setParams(query, params);
        return (T) query.uniqueResult();
    }

    protected T find(String hql, Object... params) {
        Query query = getSession().createQuery(hql);
        setParams(query, params);
        return (T) query.uniqueResult();
    }

    protected List<T> query(String hql, Map<String, Object> params) {
        Query query = getSession().createQuery(hql);
        setParams(query, params);
        return query.list();
    }

    protected List<T> query(String hql, Object... params) {
        Query query = getSession().createQuery(hql);
        setParams(query, params);
        return query.list();
    }

    protected void setParams(Query query, Map<String, Object> params) {
        if (null != params) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                setParamsToQuery(query, entry.getKey(), entry.getValue());
            }
        }
    }

    protected void setParams(Query query, Object... params) {
        if (null != params) {
            for (int i = 0; i < params.length; i++) {
                this.setParamsToQuery(query, i, params[i]);
            }
        }
    }

    private void setParamsToQuery(Query query, String paramName,Object paramValue) {
        if (paramValue instanceof Integer) {
            query.setInteger(paramName, (Integer) paramValue);
        } else if (paramValue instanceof Long) {
            query.setLong(paramName, (Long) paramValue);
        } else if (paramValue instanceof Double) {
            query.setDouble(paramName, (Double) paramValue);
        } else if (paramValue instanceof BigDecimal) {
            query.setBigDecimal(paramName, (BigDecimal) paramValue);
        } else if (paramValue instanceof String) {
            query.setString(paramName, (String) paramValue);
        } else if (paramValue instanceof Date) {
            query.setDate(paramName, (Date) paramValue);
        } else if (paramValue instanceof Boolean) {
            query.setBoolean(paramName, (Boolean) paramValue);
        } else if (paramValue instanceof Character) {
            query.setCharacter(paramName, (Character) paramValue);
        } else if (paramValue instanceof Timestamp) {
            query.setTimestamp(paramName, (Timestamp) paramValue);
        } else if (paramValue instanceof Calendar) {
            query.setCalendar(paramName, (Calendar) paramValue);
        } else {
            query.setEntity(paramName, paramValue);
        }
    }

    private void setParamsToQuery(Query query, int index, Object paramValue) {
        if (paramValue instanceof Integer) {
            query.setInteger(index, (Integer) paramValue);
        } else if (paramValue instanceof Long) {
            query.setLong(index, (Long) paramValue);
        } else if (paramValue instanceof Double) {
            query.setDouble(index, (Double) paramValue);
        } else if (paramValue instanceof BigDecimal) {
            query.setBigDecimal(index, (BigDecimal) paramValue);
        } else if (paramValue instanceof String) {
            query.setString(index, (String) paramValue);
        } else if (paramValue instanceof Date) {
            query.setDate(index, (Date) paramValue);
        } else if (paramValue instanceof Boolean) {
            query.setBoolean(index, (Boolean) paramValue);
        } else if (paramValue instanceof Character) {
            query.setCharacter(index, (Character) paramValue);
        } else if (paramValue instanceof Timestamp) {
            query.setTimestamp(index, (Timestamp) paramValue);
        } else if (paramValue instanceof Calendar) {
            query.setCalendar(index, (Calendar) paramValue);
        } else {
            query.setEntity(index, paramValue);
        }
    }

    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}