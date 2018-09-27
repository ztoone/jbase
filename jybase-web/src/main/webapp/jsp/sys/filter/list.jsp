<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/filter/filter.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>过滤器管理</title>
</head>
<script>
    var curFilterId;
    $(function(){
        loadFilterList();
    });
</script>
<style>

</style>
<body class="easyui-layout">
<div id="centerDiv" data-options="region:'center'">
    <jybase:navigator model="filter"/>
    <jybase:filter clzName="FilterBean"/>
    <table id="list_table" surl="${ctx}/filter/list"></table>
</div>
</body>
</html>