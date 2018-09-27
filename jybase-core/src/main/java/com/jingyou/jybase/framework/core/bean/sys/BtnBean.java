package com.jingyou.jybase.framework.core.bean.sys;

import com.jingyou.jybase.framework.core.base.BaseBean;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/6 0006.
 */
@Entity
@Table(name = "SYS_RES_BTN")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BtnBean extends BaseBean {
    private static final long serialVersionUID = -809672582209036131L;
    @Column(name = "RES_ID")
    private String resId;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EVENT")
    private String event;

    @Column(name = "ICON")
    private String icon;

    @Column(name = "ENABLED")
    private Integer enabled = 1;//是否启用 0:未启用  1:启用,默认启用

    @Column(name = "DESCRIPTION")
    private String desc;//菜单描述

    @Column(name = "ORDERS")
    private Integer order;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
