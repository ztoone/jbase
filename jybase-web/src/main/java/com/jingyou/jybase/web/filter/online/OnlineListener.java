package com.jingyou.jybase.web.filter.online;

import com.jingyou.jybase.common.Const;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Administrator on 2016/6/20 0020.
 */
public class OnlineListener implements ServletContextListener,HttpSessionListener {
    private static ApplicationContext ctx = null;
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        String sessionId = httpSessionEvent.getSession().getId();
        try {
            String account = httpSessionEvent.getSession().getAttribute(Const.SESSION_ACCOUNT).toString();
            ClientManager.getInstance().removeClinet(account);
            ClientManager.getInstance().removeSession(sessionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ctx = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
    }

    public static ApplicationContext getCtx() {
        return ctx;
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}
