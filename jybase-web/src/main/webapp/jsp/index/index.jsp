<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="com.jingyou.jybase.web.filter.online.ClientManager" %>
<%@ page import="com.jingyou.jybase.web.util.AppUtil" %>
<%@ page import="com.jingyou.jybase.common.util.DateUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>系统首页</title>
    <script type="text/javascript" src="${ctx}/jsp/index/index.js"></script>
    <link href="${ctx}/jsp/index/index.css" rel="stylesheet" type="text/css"/>
</head>
<script>
    var  oldHeight;
    $(function(){
        oldHeight = $(".tabs-panels").height();
    });

    function toggleFullScreen() {
        if (!document.fullscreenElement &&    // alternative standard method
                !document.mozFullScreenElement && !document.webkitFullscreenElement) {  // current working methods
            if (document.documentElement.requestFullscreen) {
                document.documentElement.requestFullscreen();
            } else if (document.documentElement.mozRequestFullScreen) {
                document.documentElement.mozRequestFullScreen();
            } else if (document.documentElement.webkitRequestFullscreen) {
                document.documentElement.webkitRequestFullscreen(Element.ALLOW_KEYBOARD_INPUT);
            }else if(document.documentElement.msRequestFullscreen) {
                document.documentElement.msRequestFullscreen();
            }

            var screenHeight = screen.height;//屏幕高度
            var northHeight = 60;//north高度
            var southHeight = 20;
            var headerHeight = $(".tabs-header").height();//table 标题高度
            var newHeight = screenHeight-northHeight-southHeight-headerHeight;
            autoHeight(newHeight);
        } else {
            if (document.cancelFullScreen) {
                document.cancelFullScreen();
            } else if (document.mozCancelFullScreen) {
                document.mozCancelFullScreen();
            } else if (document.webkitCancelFullScreen) {
                document.webkitCancelFullScreen();
            }
            autoHeight(oldHeight);
        }
    }

    function autoHeight(height){
        var tablePanels = $(".tabs-panels");
        $(tablePanels).height(height);
        $("#divFrame").height(height);
        var tabs = $(tablePanels).children("div");
        for(var i=0;i<tabs.length;i++){
            var ctabs = $(tabs[i]).children("div");
            $(ctabs[0]).height(height);
        }
    }

    function up(){
    }
</script>
<body class="easyui-layout">

<div data-options="region:'north',border:false" style="height:70px;background:#c5d1df;padding:10px" id="upper">
    <div style="width:100%;height:60%;float:left;text-align:right">
        <a class="easyui-linkbutton" data-options="plain:true,disabled:true,iconCls:'icon-user'"><span style="font-weight: bold;"><%=SecurityUtils.getSubject().getPrincipal()%></span></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-user-key',plain:true" onclick="changePwd();">修改密码</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-full-screen',plain:true" onclick="toggleFullScreen();">全屏</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-logout',plain:true" onclick="logout();">退出</a>
    </div>
    <div style="width:100%;height:30%;float:left;text-align:right">
        <span style="font-weight:bold;color:#14178c"><%=DateUtil.getDateWeek()%></span>
    </div>
    <%--<div style="width:50%;height:10%;float:right;text-align:right">
        <div style="width:5%;height:100%;float:left" class="index-up" onclick="up();"></div>
    </div>--%>
</div>

<div data-options="region:'south',border:false" style="height:20px;background:#A9FACD;overflow:hidden">
        <div style="float:left;width:40%">&nbsp;&nbsp;
            <a class="easyui-linkbutton" data-options="plain:true,disabled:true,iconCls:'icon-user'">欢迎您,<span style="font-weight:bold;"><%=SecurityUtils.getSubject().getPrincipal()%></span></a> |
            <a class="easyui-linkbutton" data-options="plain:true,disabled:true,iconCls:'icon-org'">部门：<span style="font-weight:bold;"><%=new AppUtil().getCurrentOrgName()%></span></a> |
            <a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="showOnline();">当前在线<span id="userNumSpan" style="color:red"><%=ClientManager.getInstance().count()%></span>人</a>
        </div>
        <div style="float:left;width:20%;text-align:center">浪潮软件集团有限公司</div>
        <div style="float:left;width:40%;text-align:right"></div>
</div>
<div data-options="region:'west',split:false,onCollapse:function(){resizeTabs(1);},onExpand:function(){resizeTabs(2);}" title="导航菜单" style="width:12%;height:80%">
        <div id="navMenu" class="easyui-accordion" data-options="fit:true,border:false">
            <c:forEach items="${tree}" var="var">
                <div title="${var.name}" data-options="selected:true,iconCls:'${var.iconCls}'" style="padding:5px;overflow:auto;">
                    <ul style="margin: 0;padding: 0;">
                        <c:forEach items="${var.children}" var="child">
                            <c:choose>
                                <c:when test="${empty child.children}">
                                     <li class="nav-default" onmouseover="navHover(this)" onmouseout="navOut(this)" onClick="onClick(this)"><a href="javascript:void(0)" src="${ctx}${child.url}" class="${child.iconCls}">${child.name}</a></li>
                                </c:when>
                                <c:otherwise>
                                            <li class="nav-default" onmouseover="navHover(this)" onmouseout="navOut(this)" onClick="showChild(this)">
                                                <a href="javascript:void(0)" class="${child.iconCls}">${child.name} &nbsp;&nbsp;&nbsp;&nbsp;
                                                    <span id="orderSpan" class="order-parent order-expend"></span></a>
                                            </li>
                                    <div style="display:none">
                                     <c:forEach items="${child.children}" var="child2">
                                        <ul style="margin: 0;padding: 0">
                                            <li class="nav-default" onmouseover="navHover(this)" onmouseout="navOut(this)" onClick="onClick(this)">
                                                &nbsp;&nbsp;<a href="javascript:void(0)" src="${ctx}${child2.url}" class="${child2.iconCls}">${child2.name}</a>
                                            </li>
                                         </ul>
                                     </c:forEach>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>

                </div>
            </c:forEach>
        </div>
    </div>

<div id="mainTabs" data-options="region:'center'" class="easyui-tabs">
        <div id="home" title="首页" data-options="icon:'icon-root'">
            <div id="divFrame" class="easyui-panel" data-options="border:false,fit:true">
                <iframe id="frameContent" src="" frameborder="0" width="100%" height="100%" scrolling="auto" marginheight="0" marginwidth="0" frameSpacing="0" />
            </div>
        </div>
    <div style="width:50%;height:10%;float:right;text-align:right" class="index-up"></div>
    </div>
</body>
</html>