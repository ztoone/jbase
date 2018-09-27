package com.jingyou.jybase.web.controller.sys;

import com.jingyou.jybase.common.Const;
import com.jingyou.jybase.framework.annotation.AuditLog;
import com.jingyou.jybase.framework.annotation.LogType;
import com.jingyou.jybase.framework.core.bean.sys.DictionaryBean;
import com.jingyou.jybase.service.sys.DictionaryService;
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
 * Created by Administrator on 2016/7/8 0008.
 */
@Controller
@RequestMapping(value = "/dict")
@AuditLog(log="字典管理",type = LogType.操作)
public class DictionaryController extends BaseController {

    @Autowired
    private DictionaryService dictionaryService;

    @RequestMapping("/index")
    public String index(){
        return "sys/dict/list";
    }

    @RequestMapping("/jsonTree/{id}")
    @ResponseBody
    public JSONArray jsonTree(@PathVariable String id){
        JSONArray jsonTree = new JSONArray();
        JSONObject jNode = null;
        List<DictionaryBean> lst = dictionaryService.findChildren(id);
        for(DictionaryBean org : lst){
            jNode = new JSONObject();
            jNode.element("id", org.getId());
            jNode.element("text", org.getName());
            if(dictionaryService.hasChildren(org.getId())){
                jNode.element("state", "closed");
            }
            jsonTree.add(jNode);
        }
        return jsonTree;
    }

    @RequestMapping("/check")
    @ResponseBody
    public JSONObject check(@RequestParam String value){
        JSONObject obj = new JSONObject();
        obj.element("status",dictionaryService.check(value));
        return obj;
    }

    @RequestMapping("/save")
    @ResponseBody
    @AuditLog(log="字典新增项",type = LogType.操作)
    public JSONObject add(DictionaryBean dict){
        JSONObject obj = new JSONObject();
        dict.setPid(Const.TREE_ROOT_ID);
        obj.element("status", dictionaryService.save(dict));
        return obj;
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model,@PathVariable String id){
        DictionaryBean dict = dictionaryService.findByPK(id);
        model.addAttribute("dict",dict);
        return "sys/dict/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    @AuditLog(log="编辑字典",type = LogType.操作)
    public JSONObject update(DictionaryBean dict){
        JSONObject obj = new JSONObject();
        obj.element("status", dictionaryService.update(dict));
        return obj;
    }

    @RequestMapping("/del/{id}")
    @ResponseBody
    @AuditLog(log="删除字典",type = LogType.操作)
    public JSONObject del(@PathVariable String id){
        JSONObject obj = new JSONObject();
        obj.element("status", dictionaryService.delete(id));
        return obj;
    }
}