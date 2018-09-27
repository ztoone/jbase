<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/user/user.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加用户</title>
</head>
<script>
    $(function(){
    });
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <form id="addForm" style="padding-top:10px;padding-left:15px;">
            <table id="user_table" width="90%">
                <tr>
                    <td>用户名：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="name" maxlength="50" data-options="required:true" style="width: 70%;" missingMessage="用户名不能为空!"/>
                    </td>
                </tr>
                <tr>
                    <td>账号：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="account" maxlength="100" data-options="required:true" style="width: 70%;" missingMessage="账号不能为空!" validType="remote['${ctx}/user/check']" invalidMessage="账号已存在!"/>
                    </td>
                </tr>
                <tr>
                    <td>联系电话：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="mobile" maxlength="100" data-options="" style="width: 70%;"/>
                    </td>
                </tr>
                <tr>
                    <td>邮箱：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="email" maxlength="100" data-options="validType:['email','length[0,40]']" style="width: 70%;"/>
                    </td>
                </tr>
                <tr>
                    <td>用户类型：</td>
                    <td>
                        <select class="easyui-combobox" name="type" data-options="editable:false,width:202">
                            <option value="0">普通用户</option>
                            <option value="1">管理员</option>
                        </select>
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
                <tr>
                    <td>有效期：</td>
                    <td>
                        <input class="easyui-datetimebox" name="expireTime" type="text"  style="width:50%">
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