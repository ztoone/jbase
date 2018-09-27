package com.jingyou.jybase.framework.hibernate.query;

import java.util.List;

/**
 * Created by Administrator on 2016/6/15 0015.
 */
public class Condition {
    private String property;//属性
    private List<String> values;//值
    private RestrictionType restriction;//比较方式
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public List<String> getValues() {
        return values;
    }
    public void setValues(List<String> values) {
        this.values = values;
    }
    public RestrictionType getRestriction() {
        return restriction;
    }

    public void setRestriction(RestrictionType restriction) {
        this.restriction = restriction;
    }
}
