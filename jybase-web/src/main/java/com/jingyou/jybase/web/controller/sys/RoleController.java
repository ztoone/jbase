package com.jingyou.jybase.web.controller.sys;

import com.jingyou.jybase.framework.annotation.AuditLog;
import com.jingyou.jybase.framework.annotation.LogType;
import com.jingyou.jybase.framework.core.bean.sys.RoleBean;
import com.jingyou.jybase.framework.core.bean.sys.RoleResBean;
import com.jingyou.jybase.framework.core.bean.sys.UserBean;
import com.jingyou.jybase.framework.core.tree.PermissionTree;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.service.sys.RoleResService;
import com.jingyou.jybase.service.sys.RoleService;
import com.jingyou.jybase.web.controller.base.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/12.
 */
@Controller
@RequestMapping(value = "/role")
@AuditLog(log="角色管理",type = LogType.操作)
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleResService roleResService;

    @RequestMapping("/index")
    public String index() {
        return "sys/role/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> getRoleList(Conditions cond,Page page) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("rows", roleService.pageQuery(cond,page));
        paramMap.put("total", page.getTotalCount());
        return paramMap;
    }

    @RequestMapping("/listall")
    @ResponseBody
    public List<RoleBean> getParamAllList() {
      return roleService.findAll();
    }

    @RequestMapping("/save")
    @ResponseBody
    @AuditLog(log="新增角色",type = LogType.操作)
    public JSONObject add(RoleBean role) {
        JSONObject obj = new JSONObject();
        obj.element("status", roleService.save(role));
        return obj;
    }

    @RequestMapping("/del")
    @ResponseBody
    @AuditLog(log="删除角色",type = LogType.操作)
    public JSONObject del(@RequestParam String[] ids) {
        JSONObject obj = new JSONObject();
        for(String id : ids){
            RoleBean temp = roleService.findByPK(id);
            if(temp.getType().equals("系统角色")){
                obj.element("status",false);
                obj.element("msg","系统角色不能删除!");
                return obj;
            }
            List<UserBean> users = roleService.getUsers(id);
            if(users != null && users.size() > 0){
                obj.element("status",false);
                obj.element("msg","角色已赋予给用户,删除失败!");
                return obj;
            }
        }
        obj.element("status", roleService.deleteByIds(ids));
        return obj;
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model, @PathVariable String id) {
        RoleBean role = roleService.findByPK(id);
        model.addAttribute("role", role);
        return "sys//role/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    @AuditLog(log="编辑角色",type = LogType.操作)
    public JSONObject update(RoleBean role) {
        JSONObject obj = new JSONObject();
        obj.element("status", roleService.update(role));
        return obj;
    }

    @RequestMapping("/check")
    @ResponseBody
    public JSONObject check(@RequestParam String value){
        JSONObject obj = new JSONObject();
        obj.element("status",roleService.check(value));
        return obj;
    }

    @RequestMapping("/perssiontree")
    @ResponseBody
    public List<PermissionTree> resourceTree(){
        PermissionTree root = roleService.getRootNode();
        return root.getChildren();
    }

    @RequestMapping("/assignres")
    @ResponseBody
    @AuditLog(log="角色分配资源",type = LogType.操作)
    public JSONObject assignResource(@RequestParam String roleResJson,@RequestParam String[] delIds){
        JSONObject obj = new JSONObject();
        JSONArray array = JSONArray.fromObject(roleResJson);
        List<RoleResBean> roleResList = (List<RoleResBean> )JSONArray.toCollection(array,RoleResBean.class);
        obj.element("status",roleResService.assignResource(roleResList,delIds));
        return obj;
    }

    @RequestMapping("/getSelRes/{roleId}")
    @ResponseBody
    public List<RoleResBean> getSelRes(@PathVariable String roleId) {
        return roleResService.getListByRoleId(roleId);
    }
}
