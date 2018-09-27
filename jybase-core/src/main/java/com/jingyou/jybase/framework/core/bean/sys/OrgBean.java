package com.jingyou.jybase.framework.core.bean.sys;

import com.jingyou.jybase.framework.core.base.BaseBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/17 0017.
 */

@Entity
@Table(name = "SYS_ORG")
public class OrgBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = -4054608584099999489L;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CODE")
    private String code;

    @Column(name = "BSID")
    private String bsid;

    @Column(name = "PID")
    private String pid;

    @Column(name = "TYPE")
    private Integer type = 0;

    @Column(name = "ENABLED")
    private Integer enabled = 1;//是否启用 0:未启用  1:启用,默认启用

    @Column(name = "DELETED")
    private Integer deleted = 0;//是否删除 0:未删除  1:删除

    @Column(name = "DESCRIPTION")
    private String desc;

    @Column(name = "logo")
    private String logo;//公司Logo

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getBsid() {
        return bsid;
    }

    public void setBsid(String bsid) {
        this.bsid = bsid;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
