package com.jingyou.jybase.web.controller.sys;

import com.jingyou.jybase.common.Const;
import com.jingyou.jybase.common.util.DateUtil;
import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.common.util.VerifyCodeUtils;
import com.jingyou.jybase.framework.annotation.AuditLog;
import com.jingyou.jybase.framework.annotation.LogType;
import com.jingyou.jybase.framework.core.bean.sys.ResourceBean;
import com.jingyou.jybase.framework.core.bean.sys.UserBean;
import com.jingyou.jybase.framework.core.tree.ResourceTree;
import com.jingyou.jybase.service.sys.ResourceService;
import com.jingyou.jybase.service.sys.SysParamService;
import com.jingyou.jybase.service.sys.UserService;
import com.jingyou.jybase.web.controller.base.BaseController;
import com.jingyou.jybase.web.filter.online.Client;
import com.jingyou.jybase.web.filter.online.ClientManager;
import com.jingyou.jybase.web.util.RequestUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by Administrator on 2016/5/12.
 */
@Controller
@RequestMapping(value = "/login")
@AuditLog(log="系统认证",type = LogType.登陆)
public class LoginController  extends BaseController {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;
    @Autowired
    private SysParamService paramService;

    @RequestMapping(value = "/login",method={RequestMethod.GET})
    public String login(){
        return "sys/login/login";
    }

    @RequestMapping(value = "/doLogin",method={RequestMethod.POST})
    @AuditLog(log="用户登陆",type = LogType.登陆)
    public String doLogin(HttpServletRequest request,Model model,UserBean user){
        try {
            /*String  verifyCode1= (String)request.getSession().getAttribute("verifyCode");//取出session中的验证码
            String  verifyCode2 = request.getParameter("verifyCode");//页面输入的验证码
            if(!verifyCode1.equalsIgnoreCase(verifyCode2)){
                model.addAttribute("msg3","验证码错误!");
                return "sys/login/login";
            }*/
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(),user.getPwd());
            subject.login(token);

            request.getSession().setAttribute(Const.SESSION_ACCOUNT,user.getAccount());
            Map<String,Client> clientMap = ClientManager.getInstance().getClientMap();
            if(clientMap.containsKey(user.getAccount())){
                String singleLogin = paramService.getValueByKey(Const.SYS_PARAM_SINGLE_LOGIN_KEY);
                Client c = clientMap.get(user.getAccount());
                HttpSession session = ClientManager.getInstance().removeSession(c.getSessionId());
                if (singleLogin.equals("Y") && !RequestUtil.getIpAddr(request).equals(c.getIp())) {//只能单用户登陆,两个IP是不一致的才认为是多地登陆
                    session.invalidate();
                }
                c.setIp(RequestUtil.getIpAddr(request));
                c.setSessionId(request.getSession().getId());
                c.setLoginDateTime(DateUtil.getCurrDateTime());
                ClientManager.getInstance().addSession(c.getSessionId(), request.getSession());
            }else{
                Client client = new Client();
                client.setIp(RequestUtil.getIpAddr(request));
                client.setUser(user);
                client.setSessionId(request.getSession().getId());
                client.setLoginDateTime(DateUtil.getCurrDateTime());
                ClientManager.getInstance().addClient(user.getAccount(), client);
                ClientManager.getInstance().addSession(request.getSession().getId(), request.getSession());
            }
            return "sys/login/success";
        } catch (AuthenticationException e) {
            model.addAttribute("username",user.getAccount());
            e.printStackTrace();
            if(e.getMessage().equals("用户密码错误!")){
                model.addAttribute("msg2",e.getMessage());
            }else{
                model.addAttribute("msg1",e.getMessage());
            }
            return "sys/login/login";
        }
    }

    @RequestMapping(value = "/toIndex")
    public String toIndex(Model model){
        Subject subject = SecurityUtils.getSubject();
        if(subject.getPrincipal() == null){
            return "sys/login/login";
        }
        UserBean user = userService.findByAccount(subject.getPrincipal().toString());
        List<ResourceBean> ress = userService.getRessByUserId(user.getId());
        List<ResourceTree> tree =resourceService.getRootNode(ress).getChildren();
        model.addAttribute("tree",tree);
        return "index/index";
    }

    @RequestMapping(value = "/logout")
    @AuditLog(log="退出系统",type = LogType.登陆)
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject != null && subject.getPrincipal() != null && !StringUtil.isBlank(subject.getPrincipal().toString())){
            ClientManager.getInstance().removeClinet(subject.getPrincipal().toString());
            subject.logout();
        }
        return "sys/login/logout";
    }

    @RequestMapping(value = "/code/{digit}/{width}/{height}/{time}",method = RequestMethod.GET)
    public void viewYzm(@PathVariable("digit") int digit,@PathVariable("width") int width,@PathVariable("height") int height,@PathVariable("time") String time,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String verifyCode = VerifyCodeUtils.generateVerifyCode(digit);
        request.getSession().setAttribute("verifyCode",verifyCode);
        ServletOutputStream sos = response.getOutputStream();
        VerifyCodeUtils.outputImage(width,height,sos,verifyCode);
    }
}
