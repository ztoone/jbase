package com.jingyou.jybase.framework.core.bean.sys;

import com.jingyou.jybase.framework.core.base.BaseBean;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/5/20 0020.
 */
@Entity
@Table(name = "SYS_USER")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserBean extends BaseBean {

    private static final long serialVersionUID = -2488766476866753995L;
    /*@Id
    @GenericGenerator(name = "uuid",strategy = "assigned")
    @GeneratedValue(generator = "uuid")
    @Column(name="ID",length = 32)
    private String id;//用户主键生成策略为自定义，由程序指定id，在新建用户时会同时赋以默认角色*/

    @Column(name = "ACCOUNT")
    private String account;//账号

    @Column(name = "NAME")
    private String name;//用户名

    @Column(name = "PWD")
    private String pwd;//用户密码

    @Column(name = "ENABLED")
    private Integer enabled = 1;//是否启用 0:未启用  1:启用,默认启用

    @Column(name = "THEME")
    private String theme = "default";//主题

    @Column(name = "EMAIL")
    private String email;//邮箱

    @Column(name = "MOBILE")
    private String mobile;//手机

    @Column(name = "ORG_ID")
    private String orgId;//所属机构ID

    @Column(name = "TYPE")
    private Integer type = 0;//类型 1:管理员  2:普通用户

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "CREATE_TIME")
    private Date createTime;//创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "EXPIRE_TIME")
    private Date expireTime;//失效时间

    @Transient
    private String loginIp;

   /* public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
