package com.jingyou.jybase.web.controller.sys;

import com.jingyou.jybase.framework.annotation.AuditLog;
import com.jingyou.jybase.framework.annotation.LogType;
import com.jingyou.jybase.framework.core.bean.sys.SysParamBean;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.service.sys.SysParamService;
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
@RequestMapping(value = "/sysparam")
@AuditLog(log="系统变量管理",type = LogType.操作)
public class SysParamController extends BaseController {
    @Autowired
    private SysParamService sysParamService;

    @RequestMapping("/index")
    public String index(){
        return "sys/sysparam/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String,Object> getParamList(Conditions cond,Page page){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("rows", sysParamService.pageQuery(cond,page));
        paramMap.put("total", page.getTotalCount());
        return paramMap;
    }

    @RequestMapping("/save")
    @ResponseBody
    @AuditLog(log="新增系统变量",type = LogType.操作)
    public JSONObject add(SysParamBean param){
        JSONObject obj = new JSONObject();
        obj.element("status", sysParamService.save(param));
        return obj;
    }

    @RequestMapping("/del")
    @ResponseBody
    @AuditLog(log="删除系统变量",type = LogType.操作)
    public JSONObject del(@RequestParam String[] ids){
        JSONObject obj = new JSONObject();
        obj.element("status", sysParamService.deleteByIds(ids));
        return obj;
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model,@PathVariable String id){
        SysParamBean param = sysParamService.findByPK(id);
        model.addAttribute("sysparam",param);
        return "sys//sysparam/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    @AuditLog(log="编辑系统变量",type = LogType.操作)
    public JSONObject update(SysParamBean param){
        JSONObject obj = new JSONObject();
        obj.element("status", sysParamService.update(param));
        return obj;
    }

    @RequestMapping("/check")
    @ResponseBody
    public JSONObject check(@RequestParam String value){
        JSONObject obj = new JSONObject();
        obj.element("status",sysParamService.check(value));
        return obj;
    }
}
