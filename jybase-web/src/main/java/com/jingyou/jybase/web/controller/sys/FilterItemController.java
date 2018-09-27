package com.jingyou.jybase.web.controller.sys;

import com.jingyou.jybase.framework.annotation.AuditLog;
import com.jingyou.jybase.framework.annotation.LogType;
import com.jingyou.jybase.framework.core.bean.sys.FilterItemBean;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.service.sys.FilterItemService;
import com.jingyou.jybase.web.controller.base.BaseController;
import net.sf.json.JSONObject;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/3 0003.
 */
@Controller
@RequestMapping(value = "/filter/item")
@AuditLog(log="过滤器配置",type = LogType.操作)
public class FilterItemController extends BaseController {
    @Autowired
    private FilterItemService itemService;

    @RequestMapping("/index")
    public String index(){
        return "sys/filter/item/list";
    }

    @RequestMapping("/list/{filterId}")
    @ResponseBody
    public Map<String, Object> getItemList(@PathVariable String filterId,Conditions cond,Page page){
        Map<String, Object> userMap = new HashMap<String, Object>();
        page.addCriterion(Restrictions.eq("filterId", filterId));
        userMap.put("rows", itemService.pageQuery(cond,page));
        userMap.put("total", page.getTotalCount());
        return userMap;
    }

    @RequestMapping("/save")
    @ResponseBody
    @AuditLog(log="过滤器新增属性",type = LogType.操作)
    public JSONObject add(FilterItemBean item){
        JSONObject obj = new JSONObject();
        obj.element("status", itemService.save(item));
        return obj;
    }

    @RequestMapping("/del")
    @ResponseBody
    @AuditLog(log="删除过滤器属性",type = LogType.操作)
    public JSONObject del(@RequestParam String[] ids){
        JSONObject obj = new JSONObject();
        obj.element("status", itemService.deleteByIds(ids));
        return obj;
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model,@PathVariable String id){
        FilterItemBean item = itemService.findByPK(id);
        model.addAttribute("item",item);
        return "sys/filter/item/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    @AuditLog(log="编辑过滤器属性",type = LogType.操作)
    public JSONObject update(FilterItemBean item){
        JSONObject obj = new JSONObject();
        obj.element("status", itemService.update(item));
        return obj;
    }

    @RequestMapping("/check/{filterId}")
    @ResponseBody
    public JSONObject check(@PathVariable String filterId,@RequestParam String value){
        JSONObject obj = new JSONObject();
        obj.element("status",itemService.check(filterId,value));
        return obj;
    }
}
