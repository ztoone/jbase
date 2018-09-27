<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/user/user.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>修改密码</title>
</head>
<script>
    function changePassword(){
        var result = $("#changePwdForm").form('validate');
        if(result){
            var url = ctx + "/user/changePwd";
            $.ajax({
                url: url,
                type: "POST",
                dataType: "json",
                data:{newPwd : $("#pwd").val()},
                success: function (data) {
                    if(data.status){
                        window.parent.messageShow("提示","密码修改成功!");
                        window.parent.closeDialog();
                    }else{
                        window.parent.messageShow("提示","密码修改失败!");
                        window.parent.closeDialog();
                    }
                }
            });
        }
    }
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <form id="changePwdForm" method="post" style="padding-top:10px;padding-left:20px;">
            <table width="90%">
                <tr>
                    <td>原密码：</td>
                    <td>
                        <input id="opwd" class="easyui-validatebox" type="password" name="oPassWord" maxlength="20" data-options="required:true" missingMessage="不能为空!" validType="remote['${ctx}/user/checkPwd']" invalidMessage="原密码错误!"/>
                    </td>
                </tr>
                <tr>
                    <td>新密码：</td>
                    <td>
                        <input id="pwd" class="easyui-validatebox" type="password" name="passWord" maxlength="20" required="true" data-options="validType:'minLength[6]'" missingMessage="不能为空!" invalidMessage="密码至少为6位!"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        确认新密码：
                    </td>
                    <td>
                        <input id="rpwd" class="easyui-validatebox" type="password" name="rPassWord" maxlength="20" required="true" validType="equals['#pwd']" missingMessage="不能为空!" invalidMessage="两次输入不一致!"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south',border:false">
        <div class="dialog-button">
            <a id="ok" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="changePassword()">确定</a>
            <a id="cancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="window.parent.closeDialog();">取消</a>
        </div>
    </div>
</div>
</body>
</html>