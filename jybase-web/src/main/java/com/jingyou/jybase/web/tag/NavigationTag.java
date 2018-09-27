package com.jingyou.jybase.web.tag;

import com.jingyou.jybase.framework.core.bean.sys.BtnBean;
import com.jingyou.jybase.framework.core.bean.sys.ResourceBean;
import com.jingyou.jybase.framework.core.bean.sys.UserBean;
import com.jingyou.jybase.framework.util.SpringBeanUtil;
import com.jingyou.jybase.service.sys.ResourceService;
import com.jingyou.jybase.service.sys.UserService;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.util.List;

/**
 * Created by Administrator on 2016/6/6 0006.
 */
public class NavigationTag  extends BodyTagSupport {
    private String model;
    private Logger logger = LoggerFactory.getLogger(NavigationTag.class);
    @Override
    public int doStartTag() throws JspException {
        try {
            ResourceService resourceService = SpringBeanUtil.getBean(ResourceService.class);
            UserService userService = SpringBeanUtil.getBean(UserService.class);
            StringBuffer url = new StringBuffer("/");
            url.append(model);
            url.append("/index");
            ResourceBean res = resourceService.findByUrl(url.toString());
            if(res != null){
                UserBean user = userService.findByAccount(SecurityUtils.getSubject().getPrincipal().toString());
                List<BtnBean> btns =  userService.getBtns(user.getId(),res.getId());
                if(btns != null && btns.size() > 0){
                    StringBuffer outStr = new StringBuffer(2000);
                    genHtml(outStr,btns);
                    this.pageContext.getOut().write(outStr.toString());
                }else{
                    return 0;
                }
            }else{
                return 0;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new JspException("页面标签输出内容出错", e);
        }
        return 0;
    }

    private void genHtml(StringBuffer html,List<BtnBean> btns){
        logger.info("生成功能按钮!");
        html.append("<div id=\"tgResList-toolbar\" style=\"padding:2px 0;\" class=\"datagrid-toolbar\">");
        html.append("<table cellpadding=\"0\" cellspacing=\"0\" style=\"width:100%\">");
        html.append("<tr>");
        html.append("<td style=\"padding-left:2px\">");
        for(BtnBean btn : btns){
            html.append(" <a href=\"javascript:");
            html.append(btn.getEvent());
            html.append("\" class=\"easyui-linkbutton\" data-options=\"iconCls:'");
            html.append(btn.getIcon());
            html.append("',plain:true\" style=\"float: left;\">");
            html.append(btn.getName());
            html.append("</a>");
        }
        html.append("</td>");
        html.append("</tr>");
        html.append(" </table>");
        html.append("</div>");
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
