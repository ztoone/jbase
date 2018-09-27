<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/resource/resource.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>菜单管理</title>
</head>
<script>
    var curResId;
</script>
<body>
<jybase:navigator model="resource"/>
<table id="list_table" title="菜单列表" class="easyui-treegrid" style="width:auto;height:auto"
       data-options="
				url: ctx + '/resource/tree',
				method: 'get',
				rownumbers: true,
				idField: 'id',
				treeField: 'name',
				singleSelect:true
			">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'name'" width="220">菜单名称</th>
        <th data-options="field:'url'" width="300">链接地址</th>
        <th data-options="field:'desc'" width="300">菜单描述</th>
        <th data-options="field:'order'" width="70">排序</th>
        <th data-options="field:'enabled',formatter:function(value){
            if(value=='1')
                return '启用';
            else if(value=='0')
                return '禁用';
        }" width="70">状态</th>
    </tr>
    </thead>
</table>
</body>
</html>