package com.jingyou.jybase.framework.core.bean.sys;

import com.jingyou.jybase.framework.core.base.BaseBean;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
@Entity
@Table(name = "SYS_ROLE")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RoleBean extends BaseBean {
    private static final long serialVersionUID = 7061943852800473370L;
   /* @Id
    @GenericGenerator(name = "uuid",strategy = "assigned")
    @GeneratedValue(generator = "uuid")
    @Column(name="ID",length = 32)
    private String id;//用户主键生成策略为自定义，由程序指定id，在新建用户时会同时赋以默认角色*/
    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "DESCRIPTION")
    private String desc;

    @Column(name = "ENABLED")
    private Integer enabled = 1;//是否启用 0:未启用  1:启用,默认启用

    /*public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}
