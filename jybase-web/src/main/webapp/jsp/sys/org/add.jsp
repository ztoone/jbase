<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/org/org.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加部门</title>
</head>
<script>
    var curOrgId;
    $(function(){
       loadOrgTree();
    });
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <form id="addForm" style="padding-top:10px;padding-left:15px;">
            <table id="org_table" width="90%">
                <tr>
                    <td>上级部门：</td>
                    <td>
                    <input id="orgTree" name="pid" class="easyui-combotree" style="width: 60%;" missingMessage="请选择上级部门!">
                    </td>
                </tr>
                <tr>
                    <td>部门名称：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="name" maxlength="50" data-options="required:true" style="width: 60%;" missingMessage="部门名称不能为空!" validType="remote['${ctx}/org/check',curOrgId]" invalidMessage="同一部门下名称已存在!"/>
                    </td>
                </tr>
                <tr>
                    <td>部门编码：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="code" maxlength="100" data-options="required:true" style="width: 60%;" missingMessage="部门编码不能为空!" validType="remote['${ctx}/org/check']" invalidMessage="部门编码已存在!"/>
                    </td>
                </tr>
                <tr>
                    <td>描述：</td>
                    <td>
                        <textarea class="easyui-textarea" class="easyui-textarea" rows="5" cols="21" name="desc" style="width: 100%;"></textarea>
                    </td>
                </tr>
                <tr>
                    <td>是否启用：</td>
                    <td>
                        <label>启用</label>
                        <input type="radio" name="enabled" value="1" checked="checked"/>
                        <label>不启用</label>
                        <input type="radio" name="enabled" value="0"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south',border:false">
        <div class="dialog-button">
            <a id="ok" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="save()">确定</a>
            <a id="cancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="window.parent.closeDialog();">取消</a>
        </div>
    </div>
</div>
</body>
</html>