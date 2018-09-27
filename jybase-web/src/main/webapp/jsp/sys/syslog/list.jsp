<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/syslog/log.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>系统日志</title>
</head>
<script>
    $(function () {
        loadList();
        pgEvent();
    });

</script>
<body class="easyui-layout">
<div id="centerDiv" data-options="region:'center'">
    <jybase:navigator model="syslog"/>
    <jybase:filter clzName="LogBean"/>
    <table id="list_table"  surl="${ctx}/syslog/list"></table>
</div>
</body>
</html>