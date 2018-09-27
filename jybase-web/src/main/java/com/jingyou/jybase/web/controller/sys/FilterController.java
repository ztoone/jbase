package com.jingyou.jybase.web.controller.sys;

import com.jingyou.jybase.framework.annotation.AuditLog;
import com.jingyou.jybase.framework.annotation.LogType;
import com.jingyou.jybase.framework.core.bean.sys.FilterBean;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.service.sys.FilterService;
import com.jingyou.jybase.web.controller.base.BaseController;
import net.sf.json.JSONObject;
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
@RequestMapping(value = "/filter")
@AuditLog(log="过滤器管理",type = LogType.操作)
public class FilterController extends BaseController {
    @Autowired
    private FilterService filterService;

    @RequestMapping("/index")
    public String index(){
        return "sys/filter/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> getFilterList(Conditions cond,Page page){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("rows", filterService.pageQuery(cond,page));
        paramMap.put("total", page.getTotalCount());
        return paramMap;
    }

    @RequestMapping("/save")
    @ResponseBody
    @AuditLog(log="新增过滤器",type = LogType.操作)
    public JSONObject add(FilterBean filter){
        JSONObject obj = new JSONObject();
        obj.element("status", filterService.save(filter));
        return obj;
    }

    @RequestMapping("/del")
    @ResponseBody
    @AuditLog(log="删除过滤器",type = LogType.操作)
    public JSONObject del(@RequestParam String[] ids){
        JSONObject obj = new JSONObject();
        obj.element("status", filterService.deleteByIds(ids));
        return obj;
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model,@PathVariable String id){
        FilterBean filter = filterService.findByPK(id);
        model.addAttribute("filter",filter);
        return "sys//filter/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    @AuditLog(log="编辑过滤器",type = LogType.操作)
    public JSONObject update(FilterBean filter){
        JSONObject obj = new JSONObject();
        obj.element("status", filterService.update(filter));
        return obj;
    }

    @RequestMapping("/check")
    @ResponseBody
    public JSONObject check(@RequestParam String value){
        JSONObject obj = new JSONObject();
        obj.element("status",filterService.check(value));
        return obj;
    }
}
