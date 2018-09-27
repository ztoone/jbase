package com.jingyou.jybase.framework.core.bean.sys;

import com.jingyou.jybase.framework.core.base.BaseBean;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/15 0015.
 */
@Entity
@Table(name = "SYS_FILTER_ITEM")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FilterItemBean extends BaseBean {
    private static final long serialVersionUID = 5233948756274014045L;
    @Column(name = "FILTER_ID")
    private String filterId;

    @Column(name = "FIELD")
    private String field; //属性

    @Column(name = "FIELD_NAME")
    private String fieldName;//属性名

    @Column(name = "FIELD_TYPE")
    private String fieldType;//类型：下拉、日期等

    @Column(name = "RESTRICTION")
    private String restriction;//比较方式

    @Column(name = "ORDERS")
    private Integer order;

    @Column(name = "ENABLED")
    private Integer enabled = 1;//是否启用 0:未启用  1:启用,默认启用

    @Column(name = "VALUE")
    private String value;//如果为下拉刚为下拉框内容,value:name;value:name (vale冒号name分号)

    public String getFilterId() {
        return filterId;
    }

    public void setFilterId(String filterId) {
        this.filterId = filterId;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getRestriction() {
        return restriction;
    }

    public void setRestriction(String restriction) {
        this.restriction = restriction;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
