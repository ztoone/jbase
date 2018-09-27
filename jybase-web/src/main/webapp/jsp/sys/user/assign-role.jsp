<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/user/user.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>分配角色</title>
</head>
<script>
    var oldSelRows;
    var delSelResIds;
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <!-- 可选角色 -->
    <div data-options="region:'center',border:false" style="background:#fff;border-bottom:1px solid #ccc;">
        <table id="role_table" class="easyui-treegrid" title="角色列表" style="width:100%;height:100%"
               data-options="
               singleSelect:false,
               rownumbers: true,
			   idField: 'id',
			   treeField: 'name',
               url: ctx + '/role/listall',
               onLoadSuccess:function(){loadSelectedUserRole();},
               method: 'get'
            ">
            <thead>
            <tr>
                <th data-options="field:'id',checkbox:true"></th>
                <th data-options="field:'name'" width="220">角色名称</th>
                <th data-options="field:'type'" width="220">角色类型</th>
            </tr>
            </thead>
        </table>
    </div>

    <div data-options="region:'south',border:false">
        <div class="dialog-button">
            <a id="ok" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="saveUserRole()">确定</a>
            <a id="cancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="window.parent.closeDialog();">取消</a>
        </div>
    </div>
</div>
</body>
</html>