package com.jingyou.jybase.framework.core.base;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
@MappedSuperclass
public abstract class BaseBean implements Serializable{
    @Id
    @GenericGenerator(name = "uuid",strategy = "com.jingyou.jybase.framework.hibernate.UuidGenerator")
    @GeneratedValue(generator = "uuid")
    @Column(name="ID",length = 32)
    public String id;

    public  String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
