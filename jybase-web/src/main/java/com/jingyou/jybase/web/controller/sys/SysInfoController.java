package com.jingyou.jybase.web.controller.sys;

import com.jingyou.jybase.framework.annotation.AuditLog;
import com.jingyou.jybase.framework.annotation.LogType;
import com.jingyou.jybase.framework.util.server.SigarUtil;
import com.jingyou.jybase.framework.util.server.vo.OsVo;
import com.jingyou.jybase.framework.util.server.vo.ServerVo;
import com.jingyou.jybase.web.controller.base.BaseController;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2016/6/12 0012.
 */
@Controller
@RequestMapping(value = "/sysinfo")
@AuditLog(log="服务器信息",type = LogType.操作)
public class SysInfoController extends BaseController {
    /**
     * 显示服务器性能监控页面
     * @return
     */
    @RequestMapping("/index")
    @AuditLog(log="查看服务器性能信息",type = LogType.操作)
    public String showServerPage(){
        return "sys/sysinfo/index";
    }

    @RequestMapping("/sysBaseInfo")
    @ResponseBody
    public JSONObject sysBaseInfo(){
        JSONObject obj = new JSONObject();
        try {
            SigarUtil sigarUtil = new SigarUtil();
            OsVo os = sigarUtil.getOs();
            ServerVo server = sigarUtil.getServer();
            obj.element("status",true);
            obj.element("os",os);
            obj.element("server",server);
        } catch (Exception e) {
            e.printStackTrace();
            obj.element("status", false);
        }catch (Throwable t){
            t.printStackTrace();
            obj.element("status",false);
        }
        return obj;
    }

}
