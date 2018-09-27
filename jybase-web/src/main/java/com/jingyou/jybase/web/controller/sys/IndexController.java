package com.jingyou.jybase.web.controller.sys;

import com.jingyou.jybase.framework.hibernate.query.Page;
import com.jingyou.jybase.web.controller.base.BaseController;
import com.jingyou.jybase.web.filter.online.Client;
import com.jingyou.jybase.web.filter.online.ClientManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/21 0021.
 */
@Controller
@RequestMapping(value = "/index")
public class IndexController extends BaseController{

    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> getUserList(Page page) {
        Map<String, Object> userMap = new HashMap<String, Object>();
        List<Client> clients = new ArrayList<Client>(ClientManager.getInstance().getAllClient());
        if(clients != null && clients.size() > 0){
            int fromIdx = (page.getPage()-1) * page.getRows();
            int toIdx = page.getPage() * page.getRows();
            if(toIdx > clients.size())
                toIdx = clients.size();
            userMap.put("rows", clients.subList(fromIdx,toIdx));
            userMap.put("total", page.getTotalCount());
        }
        return userMap;
    }
}
