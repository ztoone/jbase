package com.jingyou.jybase.framework.core.bean.sys;

import com.jingyou.jybase.framework.core.base.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
@Entity
@Table(name = "SYS_PARAM")
public class SysParamBean extends BaseBean {
    private static final long serialVersionUID = -6712868045214568979L;
    @Column(name = "KEY_S")
    private String key;

    @Column(name = "VALUE_S")
    private String value;

    @Column(name = "NAME_S")
    private String name;

    @Column(name = "IS_DEFAULT")
    private Integer isDefault = 1;//类型 0:自定义  1:默认

    @Column(name = "DESCRIPTION")
    private String desc;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }
}
