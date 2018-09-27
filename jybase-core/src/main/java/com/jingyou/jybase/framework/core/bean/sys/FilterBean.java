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
@Table(name = "SYS_FILTER")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class FilterBean extends BaseBean {
    private static final long serialVersionUID = 3510941125265411419L;
    @Column(name = "CLZ_NAME")
    private String clzName;//业务对象
    @Column(name = "DESCRIPTION")
    private String desc;

    public String getClzName() {
        return clzName;
    }

    public void setClzName(String clzName) {
        this.clzName = clzName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
