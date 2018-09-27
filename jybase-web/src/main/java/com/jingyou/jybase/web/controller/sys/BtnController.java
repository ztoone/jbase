package com.jingyou.jybase.web.controller.sys;

import com.jingyou.jybase.framework.annotation.AuditLog;
import com.jingyou.jybase.framework.annotation.LogType;
import com.jingyou.jybase.framework.core.bean.sys.BtnBean;
import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.service.sys.BtnService;
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
 * Created by Administrator on 2016/6/6 0006.
 */
@Controller
@RequestMapping(value = "/btn")
@AuditLog(log="按钮管理",type = LogType.操作)
public class BtnController extends BaseController {
    @Autowired
    private BtnService btnService;

    @RequestMapping("/index")
    public String index() {
        return "sys/btn/list";
    }

    @RequestMapping("/list/{resId}")
    @ResponseBody
    public Map<String, Object> getBtnList(@PathVariable String resId,Conditions cond,Page page){
        Map<String, Object> btnMap = new HashMap<String, Object>();
        page.addCriterion(Restrictions.eq("resId", resId));
        btnMap.put("rows", btnService.pageQuery(cond,page));
        btnMap.put("total", page.getTotalCount());
        return btnMap;
    }

    @RequestMapping("/save")
    @ResponseBody
    @AuditLog(log="新增按钮",type = LogType.操作)
    public JSONObject add(BtnBean btn) {
        JSONObject obj = new JSONObject();
        obj.element("status", btnService.save(btn));
        return obj;
    }

    @RequestMapping("/del")
    @ResponseBody
    @AuditLog(log="删除按钮",type = LogType.操作)
    public JSONObject del(@RequestParam String[] ids) {
        JSONObject obj = new JSONObject();
        obj.element("status", btnService.deleteByIds(ids));
        return obj;
    }

    @RequestMapping("/toEdit/{id}")
    public String toEdit(Model model, @PathVariable String id) {
        BtnBean btn = btnService.findByPK(id);
        model.addAttribute("btn", btn);
        return "sys//btn/edit";
    }

    @RequestMapping("/update")
    @ResponseBody
    @AuditLog(log="编辑按钮",type = LogType.操作)
    public JSONObject update(BtnBean btn) {
        JSONObject obj = new JSONObject();
        obj.element("status", btnService.update(btn));
        return obj;
    }
}
