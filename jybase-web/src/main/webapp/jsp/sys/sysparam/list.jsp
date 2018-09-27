<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/sysparam/sysparam.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>系统参数</title>
</head>
<script>
    $(function(){
        loadList();
        pgEvent();
    });
</script>
<style>

</style>
<body class="easyui-layout">
<div id="centerDiv" data-options="region:'center'">
    <jybase:navigator model="sysparam"/>
    <jybase:filter clzName="SysParamBean"/>
    <table id="list_table"  surl="${ctx}/sysparam/list" />
</div>
</body>
</html>