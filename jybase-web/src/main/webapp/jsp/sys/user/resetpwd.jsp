<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>重置密码</title>
</head>
<script>
    $(function(){
        var status = "${status}";
        if(status == "true"){
            $("#resetDiv").show();
        }else{
            messageAlert("提示","密码重置链接已失效,请重新获取重置链接,并在5分钟内完成重置!","",function(){window.parent.close()});
        }
    });

    function resetPwd(){
        var result = $("#resetPwdForm").form('validate');
        var id = $("#id").val();
        if(result){
            var url = ctx + "/user/reset/pwd";
            $.ajax({
                url: url,
                type: "POST",
                dataType: "json",
                data:{id:id,newPwd : $("#pwd").val()},
                success: function (data) {
                    if(data.status){
                        messageAlert("提示","密码重置成功!","",function(){window.parent.close()});
                    }else{
                        messageAlert("提示","密码重置发生异常!");
                    }
                }
            });
        }
    }
</script>
<body>
<div class="easyui-layout" data-options="fit:true" style="display:none" id="resetDiv">
    <div data-options="region:'center',border:false">
        <form id="resetPwdForm" method="post" style="padding-top:10px;padding-left:20px;">
            <table class="table-border" cellpadding="0" cellspacing="1" style="width:100%">
                <input type="hidden" id="id" value="${id}"/>
                <tr class="table-content">
                    <td align="right" width="20%" class="table-title">新密码</td>
                    <td width="75%">
                        <input id="pwd" class="easyui-validatebox" type="password" name="passWord" maxlength="20" required="true" data-options="validType:'minLength[6]'" missingMessage="不能为空!" invalidMessage="密码至少为6位!"/>
                    </td>
                </tr>
                <tr class="table-content">
                    <td align="right" width="20%" class="table-title">确认新密码</td>
                    <td width="75%">
                        <input id="rpwd" class="easyui-validatebox" type="password" name="rPassWord" maxlength="20" required="true" validType="equals['#pwd']" missingMessage="不能为空!" invalidMessage="两次输入不一致!"/>
                    </td>
                </tr>

                <tr class=table-content>
                    <td width="100%" colSpan=2 align=center><input type="button" value="重置密码" onclick="resetPwd()"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>