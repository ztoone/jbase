<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>在线用户列表</title>
</head>
<script>
    $(function(){
        loadList();
    });

    function loadList(){
        $("#list_table").datagrid({
            url: ctx+"/index/list",
            width: "100%",
            rownumbers: true,
            height:"auto",
            columns:[[
                {field:'user',title:'用户名',width:200,align:'center',formatter:function(value){
                    return value.account;
                }},
                {field:'ip',title:'登陆IP',width:200,align:'center'},
                {field:'loginDateTime',title:'登陆时间',width:200,align:'center'}
            ]]
        });
    }
</script>
<style>

</style>
<body class="easyui-layout">
<div id="centerDiv" data-options="region:'center'">
    <table id="list_table"/>
</div>
</body>
</html>