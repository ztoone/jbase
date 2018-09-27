package com.jingyou.jybase.web.filter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public class MyAuthcFilter extends FormAuthenticationFilter {
    @Override
    protected void redirectToLogin(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        String base = req.getRequestURI().substring(0, req.getRequestURI().indexOf(req.getContextPath()) + req.getContextPath().length());
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("window.top.location.href='"+ base + getLoginUrl() +"';");
        out.println("</script>");
        out.close();
    }
}
