package com.jingyou.jybase.web.tag;

import com.jingyou.jybase.common.Const;
import com.jingyou.jybase.common.util.StringUtil;
import com.jingyou.jybase.framework.core.bean.sys.FilterBean;
import com.jingyou.jybase.framework.core.bean.sys.FilterItemBean;
import com.jingyou.jybase.framework.hibernate.query.RestrictionType;
import com.jingyou.jybase.framework.util.SpringBeanUtil;
import com.jingyou.jybase.service.sys.FilterItemService;
import com.jingyou.jybase.service.sys.FilterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public class FilterTag extends BodyTagSupport {
    private Logger logger = LoggerFactory.getLogger(FilterTag.class);
    private String clzName;

    @Override
    public int doStartTag() throws JspException {
        try {
            if(StringUtil.isBlank(clzName))
                return 0;
            FilterService filterService = SpringBeanUtil.getBean(FilterService.class);
            FilterItemService itemService = SpringBeanUtil.getBean(FilterItemService.class);
            FilterBean filter = filterService.findByClzName(clzName);
            if(filter == null)
                return 0;
            List<FilterItemBean> items = itemService.findByFilerId(filter.getId(),false);
            if(items != null && items.size() > 0){
                StringBuffer outStr = new StringBuffer(2000);
                genHtml(outStr,items);
                this.pageContext.getOut().write(outStr.toString());
            }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            throw new JspException("页面标签输出内容出错", e);
        }
        return 0;
    }

    private void genHtml(StringBuffer html,List<FilterItemBean> items){
        logger.info("生成查询条件!");
        html.append("<div>");
        html.append("<form id=\"search_form\">");
        html.append("<table id=\"search_table\" class=\"table-border\" cellpadding=\"0\" cellspacing=\"1\" style=\"width:100%;display:none\">");
        int idx = 0;
        for(FilterItemBean item : items){
            html.append("<tr class=\"table-content\">");
            html.append("<td align=\"right\" width=\"20%\" class=\"table-title\">");
            html.append(item.getFieldName());
            html.append("</td>");
            html.append("<td width=\"30%\">");

            html.append("<input type=\"hidden\" name=\"conds[");
            html.append(idx);
            html.append("].property\" value=\"");
            html.append(item.getField());
            html.append("\"/>");

            html.append("<input type=\"hidden\" name=\"conds[");
            html.append(idx);
            html.append("].restriction\" value=\"");
            html.append(item.getRestriction());
            html.append("\"/>");

            genField(html,item,idx);

            html.append("</td>");
            html.append("</tr>");

            idx++;
        }
        genBtn(html);
        html.append("</table>");
        html.append("</form>");
        html.append("</div>");
    }

    //根据属性类型生成控件
    private void genField(StringBuffer html,FilterItemBean item,int idx){
        String restriction = item.getRestriction();
        String filedType = item.getFieldType();
        if(restriction.equals(RestrictionType.Between.toString())){
            if(Const.FIELD_TYPE_TEXT.equals(filedType)){
                html.append("<input type=\"text\" name=\"conds[");
                html.append(idx);
                html.append("].values[0]\"/>");

                html.append("&nbsp;&nbsp;至&nbsp;&nbsp;");

                html.append("<input type=\"text\" name=\"conds[");
                html.append(idx);
                html.append("].values[1]\"/>");
            }else if(Const.FIELD_TYPE_DATE.equals(filedType)){
                html.append("<input class=\"easyui-datebox\" type=\"text\" name=\"conds[");
                html.append(idx);
                html.append("].values[0]\"/>");

                html.append("&nbsp;&nbsp;至&nbsp;&nbsp;");

                html.append("<input class=\"easyui-datebox\" type=\"text\" name=\"conds[");
                html.append(idx);
                html.append("].values[1]\"/>");
            }else if(Const.FIELD_TYPE_DATETIME.equals(filedType)){
                html.append("<input class=\"easyui-datetimebox\" type=\"text\" name=\"conds[");
                html.append(idx);
                html.append("].values[0]\"/>");

                html.append("&nbsp;&nbsp;至&nbsp;&nbsp;");

                html.append("<input class=\"easyui-datetimebox\" type=\"text\" name=\"conds[");
                html.append(idx);
                html.append("].values[1]\"/>");
            }else if(Const.FIELD_TYPE_SELECT.equals(filedType)){
                genSelect(html,item.getValue(),idx,0);

                html.append("&nbsp;&nbsp;至&nbsp;&nbsp;");

                genSelect(html,item.getValue(),idx,1);
            }
        }else{
            if(Const.FIELD_TYPE_TEXT.equals(filedType)){
                html.append("<input type=\"text\" name=\"conds[");
                html.append(idx);
                html.append("].values[0]\"/>");
            }else if(Const.FIELD_TYPE_DATE.equals(filedType)){
                html.append("<input class=\"easyui-datebox\" type=\"text\" name=\"conds[");
                html.append(idx);
                html.append("].values[0]\"/>");
            }else if(Const.FIELD_TYPE_DATETIME.equals(filedType)){
                html.append("<input class=\"easyui-datetimebox\" type=\"text\" name=\"conds[");
                html.append(idx);
                html.append("].values[0]\"/>");
            }else if(Const.FIELD_TYPE_SELECT.equals(filedType)){
                genSelect(html,item.getValue(),idx,0);
            }
        }
    }

    private void genSelect(StringBuffer html,String options,int idx,int vidx){
        html.append("<select class=\"easyui-combobox\" id=\"restriction\" style=\"width:100px\" name=\"conds[");
        html.append(idx);
        html.append("].values[");
        html.append(vidx);
        html.append("]\"/>");
        if(!StringUtil.isBlank(options)){
            String[] optionArray = options.split(";");
            for(int i=0;i<optionArray.length;i++){
                String[] kv = optionArray[i].split(":");
                html.append("<option value=\"");
                html.append(kv[0]);
                html.append("\">");
                html.append(kv[1]);
                html.append("</option>");
            }
        }
        html.append("</select>");
    }
    private void genBtn(StringBuffer html){
        html.append("<tr class=table-content>");
        html.append("<td width=\"100%\" colSpan=2 align=center>");
        html.append("<input type=\"button\" value=\"确定\" onclick=\"doSearch();\"/>&nbsp;&nbsp;");
        html.append("<input type=\"button\" value=\"取消\" onclick=\"hideSearchTable();\"/>&nbsp;&nbsp;");
        html.append("</td>");
        html.append("</tr>");
    }

    public String getClzName() {
        return clzName;
    }

    public void setClzName(String clzName) {
        this.clzName = clzName;
    }
}
