package com.jingyou.jybase.web.controller.sys;

import com.jingyou.jybase.common.Const;
import com.jingyou.jybase.common.util.DateUtil;
import com.jingyou.jybase.common.util.EncryptUtil;
import com.jingyou.jybase.common.util.PwdUtil;
import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.annotation.AuditLog;
import com.jingyou.jybase.framework.annotation.LogType;
import com.jingyou.jybase.framework.core.bean.sys.UserBean;
import com.jingyou.jybase.framework.core.bean.sys.UserRoleBean;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.service.sys.SysParamService;
import com.jingyou.jybase.service.sys.UserRoleService;
import com.jingyou.jybase.service.sys.UserService;
import com.jingyou.jybase.web.controller.base.BaseController;
import com.jingyou.jybase.web.util.EmailUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/12.
 */
@Controller
@RequestMapping(value = "/user")
@AuditLog(log="用户管理",type = LogType.操作)
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private SysParamService sysParamService;
    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/index")
    public String index() {
        return "sys/user/list";
    }

    @RequestMapping("/list/{orgId}")
    @ResponseBody
    public Map<String, Object> getUserList(@PathVariable String orgId,Conditions cond, Page page) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        page.addCriterion(Restrictions.eq("orgId", orgId));
        userMap.put("rows", userService.pageQuery(cond,page));
        userMap.put("total", page.getTotalCount());
        return userMap;
    }

    @RequestMapping("/save")
    @ResponseBody
    @AuditLog(log="新增用户",type = LogType.操作)
    public JSONObject add(UserBean user) {
        JSONObject obj = new JSONObject();
        user.setPwd(PwdUtil.encryptPassword(sysParamService.getValueByKey(Const.SYS_PARAM_USER_PWD_KEY)));//新建用户设置初始化密码
        user.setCreateTime(new Date());
        obj.element("status", userService.save(user));
        return obj;
    }

    @RequestMapping("/del")
    @ResponseBody
    @AuditLog(log="删除用户",type = LogType.操作)
    public JSONObject del(@RequestParam String[] ids) {
        JSONObject obj = new JSONObject();
        for(String id : ids) {
            UserBean temp = userService.findByPK(id);
            if (temp.getType() == 1) {//用户类型为管理员
                obj.element("status",false);
                obj.element("msg","管理员类型用户不能删除!");
                return obj;
            }
        }
        obj.element("status", userService.deleteByIds(ids));
        return obj;
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model, @PathVariable String id) {
        UserBean user = userService.findByPK(id);
        model.addAttribute("user", user);
        return "sys/user/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    @AuditLog(log="编辑用户",type = LogType.操作)
    public JSONObject update(UserBean user) {
        JSONObject obj = new JSONObject();
        UserBean old = userService.findByPK(user.getId());
        user.setOrgId(old.getOrgId());
        user.setPwd(old.getPwd());
        userService.evict(old);//改为游离状态
        obj.element("status", userService.update(user));
        return obj;
    }

    @RequestMapping("/assignRole")
    @ResponseBody
    @AuditLog(log="用户赋角色",type = LogType.操作)
    public JSONObject assignRole(@RequestParam String userRoleJson,@RequestParam String[] delIds){
        JSONObject obj = new JSONObject();
        JSONArray array = JSONArray.fromObject(userRoleJson);
        List<UserRoleBean> userRoles = (List<UserRoleBean> )JSONArray.toCollection(array,UserRoleBean.class);
        obj.element("status",userRoleService.assginRole(userRoles,delIds));
        return obj;
    }

    @RequestMapping("/getSelRole/{userId}")
    @ResponseBody
    public List<UserRoleBean> getSelRole(@PathVariable String userId) {
        return userRoleService.getListByUserId(userId);
    }

    @RequestMapping("/changePwd")
    @ResponseBody
    @AuditLog(log="修改密码",type = LogType.操作)
    public JSONObject changePwd(@RequestParam String newPwd){
        JSONObject obj = new JSONObject();
        String account = SecurityUtils.getSubject().getPrincipal().toString();
        UserBean user = userService.findByAccount(account);
        user.setPwd(PwdUtil.encryptPassword(newPwd));
        obj.element("status",userService.update(user));
        return obj;
    }

    @RequestMapping("/checkPwd")
    @ResponseBody
    public JSONObject checkPwd(@RequestParam String value){
        JSONObject obj = new JSONObject();
        String account = SecurityUtils.getSubject().getPrincipal().toString();
        UserBean user = userService.findByAccount(account);
        obj.element("status",!PwdUtil.validatePassword(value,user.getPwd()));
        return obj;
    }

    @RequestMapping("/check")
    @ResponseBody
    public JSONObject check(@RequestParam String value){
        JSONObject obj = new JSONObject();
        obj.element("status",userService.check(value));
        return obj;
    }

    @RequestMapping("/resetPwd/{userId}")
    @ResponseBody
    public JSONObject resetPwd(@PathVariable String userId){
        JSONObject obj = new JSONObject();
        UserBean user = userService.findByPK(userId);
        if(user.getType() == 1){//管理员
            obj.element("status", false);
            obj.element("msg","管理员类型用户密码不能重置!");
            return obj;
        }
        user.setPwd(PwdUtil.encryptPassword(sysParamService.getValueByKey(Const.SYS_PARAM_USER_PWD_KEY)));
        obj.element("status", userService.update(user));
        return obj;
    }

    @RequestMapping("/reset/check")
    @ResponseBody
    public JSONObject resetCheck(@RequestParam String account,@RequestParam String email,HttpServletRequest request){
        JSONObject obj = new JSONObject();
        UserBean user = userService.findByAccount(account);
        if(user != null){
            if(StringUtil.isEmpty(user.getEmail())){
                obj.element("msg","&nbsp;*用户未配置邮箱,重置失败.");
            }else{
                if(!email.equals(user.getEmail())){
                    obj.element("msg","&nbsp;*输入邮箱与用户配置邮箱不一致,重置失败.");
                    return obj;
                }
                StringBuffer resetUrl = new StringBuffer("http://");
                String ip = request.getLocalAddr();
                resetUrl.append(ip);
                resetUrl.append(":8001/jybase/user/reset/toResetPage/");
                resetUrl.append(EncryptUtil.encrypt(user.getId()));
                resetUrl.append("/");
                resetUrl.append(EncryptUtil.encrypt((new Date().getTime()+"")));
                String msg = "请在5分钟内点击<a href=\""+resetUrl.toString()+"\">重置密码</a>来完成密码重置,如果无法跳转，可以复制<br>["+resetUrl.toString()+"]<br>到浏览器进行重置.";
                EmailUtil.sendHtml(email,"密码重置",msg);
                obj.element("msg","&nbsp;*密码重置链接已发送至[" + formatEmail(email) + "],请登陆邮箱点击进行重置.");
            }
        }else{
            obj.element("msg","&nbsp;*用户不存在.");
        }
        return obj;
    }


    @RequestMapping("/reset/toResetPage/{id}/{timestamp}")
    public String toResetPage(Model model,@PathVariable String id,@PathVariable String timestamp) {
        try {
            long curr = new Date().getTime();
            long time = Long.parseLong(EncryptUtil.decrypt(timestamp));
            long fiveMinutes = 5 * 60 * 1000;
            if((curr - time) > fiveMinutes){
                model.addAttribute("status",false);
            }else{
                model.addAttribute("status",true);
                model.addAttribute("id",id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "sys/user/resetpwd";
    }

    @RequestMapping("/reset/pwd")
    @ResponseBody
    public JSONObject resetPwd(@RequestParam String id,@RequestParam String newPwd){
        JSONObject obj = new JSONObject();
        try {
            String userId = EncryptUtil.decrypt(id);
            UserBean user = userService.findByPK(userId);
            user.setPwd(PwdUtil.encryptPassword(newPwd));
            obj.element("status", userService.update(user));
        } catch (Exception e) {
            e.printStackTrace();
            obj.element("status",false);
        }
        return obj;
    }

    private String formatEmail(String email){
        String[] strs = email.split("@");
        if(strs[0].length()>4){
            StringBuffer temp = new StringBuffer(strs[0].substring(0,2));
            for(int i=0;i<strs[0].length()-4;i++){
                temp.append("*");
            }
            temp.append(strs[0].substring(strs[0].length()-2,strs[0].length()));
            temp.append("@jingyougroup.com");
            return temp.toString();
        }else{
            StringBuffer temp = new StringBuffer(strs[0].charAt(0)+"");
            for(int i=0;i<strs[0].length()-2;i++){
                temp.append("*");
            }
            temp.append(strs[0].charAt(strs[0].length()-1));
            temp.append("@jingyougroup.com");
            return temp.toString();
        }
    }

}
