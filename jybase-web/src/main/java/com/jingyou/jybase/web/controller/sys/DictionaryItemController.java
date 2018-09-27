package com.jingyou.jybase.web.controller.sys;

import com.jingyou.jybase.framework.annotation.AuditLog;
import com.jingyou.jybase.framework.annotation.LogType;
import com.jingyou.jybase.framework.core.bean.sys.DictionaryBean;
import com.jingyou.jybase.framework.core.bean.sys.DictionaryItemBean;
import com.jingyou.jybase.framework.core.bean.sys.FilterItemBean;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.service.sys.DictionaryItemService;
import com.jingyou.jybase.service.sys.DictionaryService;
import com.jingyou.jybase.web.controller.base.BaseController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
 * Created by Administrator on 2016/7/8 0008.
 */
@Controller
@RequestMapping(value = "/dict/item")
@AuditLog(log="字典管理",type = LogType.操作)
public class DictionaryItemController extends BaseController {

    @Autowired
    private DictionaryItemService itemService;

    @RequestMapping("/index")
    public String index(){
        return "sys/dict/item/list";
    }

    @RequestMapping("/list/{dictId}")
    @ResponseBody
    public Map<String, Object> getItemList(@PathVariable String dictId,Conditions cond,Page page){
        Map<String, Object> map = new HashMap<String, Object>();
        page.addCriterion(Restrictions.eq("dictId", dictId));
        page.addOrder(Order.asc("order"));
        map.put("rows", itemService.pageQuery(cond,page));
        map.put("total", page.getTotalCount());
        return map;
    }

    @RequestMapping("/save")
    @ResponseBody
    @AuditLog(log="字典新增项",type = LogType.操作)
    public JSONObject add(DictionaryItemBean item){
        JSONObject obj = new JSONObject();
        obj.element("status", itemService.save(item));
        return obj;
    }

    @RequestMapping("/del")
    @ResponseBody
    @AuditLog(log="删除字典项",type = LogType.操作)
    public JSONObject del(@RequestParam String[] ids){
        JSONObject obj = new JSONObject();
        obj.element("status", itemService.deleteByIds(ids));
        return obj;
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model,@PathVariable String id){
        DictionaryItemBean item = itemService.findByPK(id);
        model.addAttribute("item",item);
        return "sys/dict/item/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    @AuditLog(log="编辑字典项",type = LogType.操作)
    public JSONObject update(DictionaryItemBean item){
        JSONObject obj = new JSONObject();
        obj.element("status", itemService.update(item));
        return obj;
    }

    @RequestMapping("/check/{dictId}")
    @ResponseBody
    public JSONObject check(@PathVariable String dictId,@RequestParam String value){
        JSONObject obj = new JSONObject();
        obj.element("status",itemService.check(dictId,value));
        return obj;
    }

    @RequestMapping("/checkCode/{dictId}")
    @ResponseBody
    public JSONObject checkCode(@PathVariable String dictId,@RequestParam String value){
        JSONObject obj = new JSONObject();
        obj.element("status",itemService.checkCode(dictId, value));
        return obj;
    }
}
