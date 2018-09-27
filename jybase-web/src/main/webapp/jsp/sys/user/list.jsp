<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/user/user.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户管理</title>
</head>
<script>
    var curUserId;
    var curOrgId = "root";
    $(function(){
        loadOrgTree();
        loadList("root");
        pgEvent();
    });
</script>
<style>

</style>
<body class="easyui-layout">
<div data-options="region:'west',split:false,border:false" style="width:200px;padding-right:1px;">
    <div class="easyui-panel" data-options="title:'部门列表',fit:true" style="padding-left:5px;">
        <ul id="orgTree" class="easyui-tree"></ul>
    </div>
</div>
<div id="centerDiv" data-options="region:'center'">
    <jybase:navigator model="user"/>
    <jybase:filter clzName="UserBean"/>
    <table id="list_table"></table>
</div>
</body>
</html>