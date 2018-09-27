package com.jingyou.jybase.web.filter.online;

import com.jingyou.jybase.framework.core.bean.sys.UserBean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public class Client implements Serializable{
    private UserBean user;
    private String ip;
    private String loginDateTime;
    private String sessionId;

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLoginDateTime() {
        return loginDateTime;
    }

    public void setLoginDateTime(String loginDateTime) {
        this.loginDateTime = loginDateTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
