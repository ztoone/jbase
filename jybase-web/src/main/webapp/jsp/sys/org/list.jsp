<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/org/org.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>菜单管理</title>
</head>
<body>
<jybase:navigator model="org"/>
<table id="list_table" title="机构列表" class="easyui-treegrid" style="width:auto;height:auto"
       data-options="
				url: ctx + '/org/tree',
				method: 'get',
				rownumbers: true,
				idField: 'id',
				treeField: 'name',
				onLoadSuccess: function (row, data){
                    $.each(data, function (i, val) {
                       $('#list_table').treegrid('collapseAll', data[i].id);
                    });
                    $('#list_table').treegrid('expand','root');
                }
			">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'name'" width="220">机构名称</th>
        <th data-options="field:'code'" width="300">机构编码</th>
        <th data-options="field:'desc'" width="300">机构描述</th>
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