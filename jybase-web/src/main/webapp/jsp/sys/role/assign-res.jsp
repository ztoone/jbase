<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/role/role.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>分配资源</title>
</head>
<script>
    var oldSelRows;
    var delSelResIds;
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <table id="res_table" title="权限列表" class="easyui-treegrid" style="width:auto;height:auto"
               data-options="
				url: ctx + '/role/perssiontree',
				method: 'get',
				rownumbers: true,
				idField: 'id',
				treeField: 'name',
				singleSelect:false,
				onClickRow:function(row){treeGridOnclick(row);},
				onLoadSuccess:function(){loadSelectedRoleRes();}
			">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'name'" width="220">名称</th>
                <th data-options="field:'type',formatter:function(value){
                if(value=='resource')
                     return '菜单';
                else if(value=='button')
                     return '按钮';
                }" width="70">类型</th>
            </tr>
            </thead>
        </table>
    </div>
    <div data-options="region:'south',border:false">
        <div class="dialog-button">
            <a id="ok" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)"
               onclick="savePerssion()">确定</a>
            <a id="cancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)"
               onclick="window.parent.closeDialog();">取消</a>
        </div>
    </div>
</div>
</body>
</html>