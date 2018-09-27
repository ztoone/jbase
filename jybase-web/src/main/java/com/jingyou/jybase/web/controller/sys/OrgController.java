package com.jingyou.jybase.web.controller.sys;

import com.jingyou.jybase.framework.annotation.AuditLog;
import com.jingyou.jybase.framework.annotation.LogType;
import com.jingyou.jybase.framework.core.bean.sys.OrgBean;
import com.jingyou.jybase.framework.core.tree.OrgTree;
import com.jingyou.jybase.service.sys.OrgService;
import com.jingyou.jybase.web.controller.base.BaseController;
import com.jingyou.jybase.web.util.AppUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 */
@Controller
@RequestMapping(value = "/org")
@AuditLog(log="机构管理",type = LogType.操作)
public class OrgController extends BaseController {

    @Autowired
    private OrgService orgService;

    @RequestMapping("/index")
   public String index(){
       return "sys/org/list";
   }

    @RequestMapping("/tree")
    @ResponseBody
    public List<OrgTree>orgTree(){
        OrgBean currOrg = AppUtil.getCurrentOrg();
        OrgTree root = orgService.getRootNode(currOrg,true);
        List<OrgTree> result = new ArrayList<OrgTree>();
        result.add(root);
        return result;
    }

    @RequestMapping("/jsonTree/{id}")
    @ResponseBody
    public JSONArray jsonTree(@PathVariable String id){
        JSONArray jsonTree = new JSONArray();
        JSONObject jNode = null;
        if(id.equals("0")){
            jNode = new JSONObject();
            OrgBean root = AppUtil.getRootOrg();
            jNode.element("id", root.getId());
            jNode.element("text", root.getName());
            jNode.element("state", "closed");
            jsonTree.add(jNode);
            return jsonTree;
        }
        List<OrgBean> lst = orgService.findChildren(id,false);
        for(OrgBean org : lst){
            jNode = new JSONObject();
            jNode.element("id", org.getId());
            jNode.element("text", org.getName());
            if(orgService.hasChildren(org.getId())){
                jNode.element("state", "closed");
            }
            jsonTree.add(jNode);
        }
        return jsonTree;
    }

    @RequestMapping("/save")
    @ResponseBody
    @AuditLog(log="新增机构",type = LogType.操作)
    public JSONObject add(OrgBean org){
        JSONObject obj = new JSONObject();
        obj.element("status", orgService.save(org));
        return obj;
    }

    @RequestMapping("/del")
    @ResponseBody
    @AuditLog(log="删除机构",type = LogType.操作)
    public JSONObject del(@RequestParam String[] ids){
        JSONObject obj = new JSONObject();
        if(orgService.hasUser(ids[0])){
            obj.element("status",false);
            obj.element("msg","请先删除机构下用户!");
        }else{
            obj.element("status", orgService.deleteByIds(ids));
        }
        return obj;
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model,@PathVariable String id){
        OrgBean org = orgService.findByPK(id);
        String pName = orgService.findByPK(org.getPid()).getName();
        model.addAttribute("org",org);
        model.addAttribute("pName",pName);
        return "sys/org/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    @AuditLog(log="编辑机构",type = LogType.操作)
    public JSONObject update(OrgBean org){
        JSONObject obj = new JSONObject();
        obj.element("status", orgService.update(org));
        return obj;
    }

    @RequestMapping("/check")
    @ResponseBody
    public JSONObject check(@RequestParam String value){
        JSONObject obj = new JSONObject();
        obj.element("status",orgService.check(value));
        return obj;
    }

    @RequestMapping("/check/{pid}")
    @ResponseBody
    public JSONObject check(@PathVariable String pid,@RequestParam String value){
        JSONObject obj = new JSONObject();
        obj.element("status",orgService.check(pid,value));
        return obj;
    }
}
