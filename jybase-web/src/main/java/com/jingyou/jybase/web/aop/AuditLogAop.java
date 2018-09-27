package com.jingyou.jybase.web.aop;

import com.jingyou.jybase.common.util.DateUtil;
import com.jingyou.jybase.framework.annotation.AuditLog;
import com.jingyou.jybase.framework.core.bean.sys.LogBean;
import com.jingyou.jybase.framework.core.bean.sys.UserBean;
import com.jingyou.jybase.service.sys.LogService;
import com.jingyou.jybase.service.sys.UserService;
import com.jingyou.jybase.web.util.RequestUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
@Aspect
@Component
public class AuditLogAop {
    @Autowired
    private UserService userService;
    @Autowired
    private LogService logService;
    ThreadLocal<Long> time=new ThreadLocal<Long>();
    ThreadLocal<LogBean> threadLog = new ThreadLocal<LogBean>();
    @Pointcut("@annotation(com.jingyou.jybase.framework.annotation.AuditLog)")
    public void log(){
    }

    @Before("log()")
    public void beforeLog(JoinPoint joinPoint){
        LogBean log = new LogBean();
        loadUserName(log);
        threadLog.set(log);
        time.set(System.currentTimeMillis());
    }

    @After("log()")
    public void afterLog(JoinPoint joinPoint){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Object clz = joinPoint.getTarget();//类
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod(); //方法
        LogBean log = threadLog.get();
        loadModel(log, clz);
        loadContext(log, method);
        log.setUri(request.getRequestURI());
        log.setIp(RequestUtil.getIpAddr(request));
        log.setTimeSpend(System.currentTimeMillis() - time.get());
        log.setTime(DateUtil.getCurrDateTime());
        logService.save(log);
    }

    @AfterReturning("log()")
    public void afterReturning(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        request.getAttribute("msg");
    }

    //取类上日志信息
    private void loadModel(LogBean log,Object clz){
        AuditLog auditLog = clz.getClass().getAnnotation(AuditLog.class);
        log.setModel(auditLog.log());
    }

    //取方法上日志信息
    private void loadContext(LogBean log,Method method){
        AuditLog auditLog = method.getAnnotation(AuditLog.class);
        log.setContext(auditLog.log());
        log.setType(auditLog.type().toString());
    }

    //取用户信息
    private void loadUserName(LogBean log){
        Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipal() != null){
            UserBean user = userService.findByAccount(subject.getPrincipal().toString());
            log.setUserName(user.getName());
            userService.evict(user);//使user变为游离状态
        }else{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String account = request.getParameter("account");
            log.setUserName(account);
        }
    }
}
