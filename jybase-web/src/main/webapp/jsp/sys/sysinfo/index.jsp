<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/sysinfo/sysinfo.js"></script>
<script type="text/javascript" src="${ctx}/js/echart/echarts.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>服务器信息</title>
</head>
<script>
    $(function () {
        initBaseInfo();
        initECharts();
    });
</script>
<body class="easyui-layout">
<div region="center" border="false" style="overflow:hidden;width:100%;height:100%">
    <div style="width: 26%;height:99%;float: left">
        <div class="datagrid-wrap panel-body" title="" style="width:99%; height:100%">
            <div style="height:55%;width:100%">
                <table id="sysBaseInfo"></table>
            </div>
            <div style="height:45%;width:100%">
                <div id="netflowdiv" style="height:100%;width:100%;overflow:hidden"></div>
            </div>
        </div>
    </div>

    <div style="width: 24%;height:95%;float:left;padding-left:2px">
            <div class="panel-header" style="width:96%;">
                <div class="panel-title">处理器信息</div>
            </div>
            <div class="datagrid-wrap panel-body" title="处理器信息" style="width:99%; height:100%">
                <div id="cpudiv" style="height:55%;width:100%;overflow:hidden"></div>
                <div id="cpuhistorydiv" style="height:45%;width:100%;overflow:hidden"></div>
            </div>
    </div>

    <div style="width: 24%;height:95%;float:left;padding-left:2px">
            <div class="panel-header" style="width:96%;">
                <div class="panel-title">内存信息</div>
            </div>
            <div class="datagrid-wrap panel-body" title="内存信息" style="width:99%; height:100%">
                <div id="memorydiv" style="height:55%;width:100%;overflow:hidden"></div>
                <div id="memoryhistorydiv" style="height:45%;width:100%;overflow:hidden"></div>
            </div>
    </div>

    <div style="width: 24%;height:95%;float:left;padding-left:2px">
            <div class="panel-header" style="width:96%;">
                <div class="panel-title">硬盘信息</div>
            </div>
            <div class="datagrid-wrap panel-body" title="硬盘信息" style="width:99%; height:100%">
                <div id="diskdiv" style="height:55%;width:100%;overflow:hidden"></div>
                <div id="diskareadiv" style="height:45%;width:100%;overflow:hidden"></div>
            </div>
    </div>
</div>
</body>
</html>