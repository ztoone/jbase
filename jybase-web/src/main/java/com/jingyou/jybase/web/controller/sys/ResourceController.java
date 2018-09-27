package com.jingyou.jybase.web.controller.sys;

import com.jingyou.jybase.framework.annotation.AuditLog;
import com.jingyou.jybase.framework.annotation.LogType;
import com.jingyou.jybase.framework.core.bean.sys.BtnBean;
import com.jingyou.jybase.framework.core.bean.sys.ResourceBean;
import com.jingyou.jybase.framework.core.tree.ResourceTree;
import com.jingyou.jybase.service.sys.ResourceService;
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

import java.util.List;

/**
 * Created by Administrator on 2016/5/12.
 */
@Controller
@RequestMapping(value = "/resource")
@AuditLog(log="菜单管理",type = LogType.操作)
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;

   @RequestMapping("/index")
   public String index(){
       return "sys/resource/list";
   }

    @RequestMapping("/tree")
    @ResponseBody
    public List<ResourceTree> resourceTree(){
        ResourceTree root = resourceService.getRootNode(true);
        return root.getChildren();
    }

    @RequestMapping("/jsonTree/{id}")
    @ResponseBody
    public JSONArray jsonTree(@PathVariable String id){
        JSONArray jsonTree = new JSONArray();
        JSONObject jNode = null;
        List<ResourceBean> lst = resourceService.findChildren(id);
        for(ResourceBean res : lst){
            jNode = new JSONObject();
            jNode.element("id", res.getId());
            jNode.element("text", res.getName());
            jNode.element("iconCls", res.getIcon());
            jNode.element("url", res.getUrl());
            if(resourceService.hasChildren(res.getId())){
                jNode.element("state", "closed");
            }
            jsonTree.add(jNode);
        }
        return jsonTree;
    }

    @RequestMapping("/save")
    @ResponseBody
    @AuditLog(log="新增菜单",type = LogType.操作)
    public JSONObject add(ResourceBean res){
        JSONObject obj = new JSONObject();
        obj.element("status", resourceService.save(res));
        return obj;
    }

    @RequestMapping("/del")
    @ResponseBody
    @AuditLog(log="删除菜单",type = LogType.操作)
    public JSONObject del(@RequestParam String[] ids){
        JSONObject obj = new JSONObject();
        obj.element("status", resourceService.deleteByIds(ids));
        return obj;
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model,@PathVariable String id){
        ResourceBean res = resourceService.findByPK(id);
        String pName = resourceService.findByPK(res.getPid()).getName();
        model.addAttribute("res",res);
        model.addAttribute("pName",pName);
        return "sys//resource/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    @AuditLog(log="编辑菜单",type = LogType.操作)
    public JSONObject update(ResourceBean res){
        JSONObject obj = new JSONObject();
        obj.element("status", resourceService.update(res));
        return obj;
    }

    @RequestMapping("/toBtn/{id}")
    public String toBtn(Model model,@PathVariable String id){
        List<BtnBean> btns = null;
        model.addAttribute("btns",btns);
        return null;
    }
}
