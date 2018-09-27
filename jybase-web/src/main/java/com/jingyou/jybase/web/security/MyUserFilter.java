package com.jingyou.jybase.web.security;

import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2016/6/13 0013.
 */
public class MyUserFilter extends UserFilter {
    private String loginUrl;
    //未登录重定向到登陆页
    protected void redirectToLogin(ServletRequest req, ServletResponse resp)
            throws IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String loginUrl = getLoginUrl();
        WebUtils.issueRedirect(request, response, loginUrl);
    }

    @Override
    public String getLoginUrl() {
        return loginUrl;
    }

    @Override
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }
}
