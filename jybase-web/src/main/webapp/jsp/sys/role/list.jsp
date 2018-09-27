<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/role/role.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>
</head>
<script>
    var curRoleId;
    $(function(){
        loadList();
        pgEvent();
    });
</script>
<style>

</style>
<body class="easyui-layout">
<div id="centerDiv" data-options="region:'center'">
    <jybase:navigator model="role"/>
    <jybase:filter clzName="RoleBean"/>
    <table id="list_table" surl="${ctx}/role/list"></table>
</div>
</body>
</html>