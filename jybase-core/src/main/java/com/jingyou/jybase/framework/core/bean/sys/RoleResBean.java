package com.jingyou.jybase.framework.core.bean.sys;

/**
 * Created by Administrator on 2016/6/8 0008.
 */

import com.jingyou.jybase.framework.core.base.BaseBean;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 角色-资源
 */
@Entity
@Table(name = "SYS_ROLE_RES")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleResBean extends BaseBean {
    private static final long serialVersionUID = -5906288332282842374L;
    @Column(name = "ROLE_ID")
    private String roleId;

    @Column(name = "RES_ID")
    private String resId;

    @Column(name = "TYPE")
    private String type;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
