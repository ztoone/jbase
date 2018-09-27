package com.jingyou.jybase.framework.core.bean.sys;

import com.jingyou.jybase.framework.core.base.BaseBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
@Entity
@Table(name = "SYS_LOG")
public class LogBean extends BaseBean {
    private static final long serialVersionUID = -7133884278079507345L;
    @Column(name = "MODEL")
    private String model;//模块

    @Column(name = "CONTEXT")
    private String context;//内容

    @Column(name = "URI")
    private String uri;//请求资源

    @Column(name = "IP")
    private String ip;//地址

    @Column(name = "TIME")
    private String time; //时间

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "TIME_SPEND")
    private long timeSpend;//耗时

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTimeSpend() {
        return timeSpend;
    }

    public void setTimeSpend(long timeSpend) {
        this.timeSpend = timeSpend;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
