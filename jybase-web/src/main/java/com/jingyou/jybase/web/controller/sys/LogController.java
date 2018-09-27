package com.jingyou.jybase.web.controller.sys;

import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.framework.hibernate.query.Conditions;
import com.jingyou.jybase.service.sys.LogService;
import com.jingyou.jybase.web.controller.base.BaseController;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
@Controller
@RequestMapping(value = "/syslog")
public class LogController extends BaseController {
    @Autowired
    private LogService logService;

    @RequestMapping("/index")
    public String index() {
        return "sys/syslog/list";
    }

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> getLogList(Conditions cond,Page page) {
        page.addOrder(Order.desc("time"));
        Map<String, Object> logMap = new HashMap<String, Object>();
        logMap.put("rows", logService.pageQuery(cond,page));
        logMap.put("total", page.getTotalCount());
        return logMap;
    }
}
