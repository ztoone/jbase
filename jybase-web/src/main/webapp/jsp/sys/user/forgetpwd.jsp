<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/user/user.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>重置密码</title>
</head>
<script>
    $(function(){
        var paccount = $("#account",window.parent.document).val();
        $("#account").val(paccount);
    });
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <form id="resetPwdForm" style="padding-top:10px;padding-left:15px;">
       <table class="table-border" cellpadding="0" cellspacing="1" style="width:100%">
           <tr class="table-content">
               <td align="left"colSpan=2 width="20%" class="table-title"><span style="color:red;" id="resetMsg"></span></td>
           </tr>
           <tr class="table-content">
                <td align="right" width="20%" class="table-title">账号:</td>
                <td width="75%"><input type="text" name="account" id="account" style="width:300px" class="easyui-validatebox" data-options="required:true" missingMessage="账号不能为空!"/></td>
            </tr>
           <tr class="table-content">
               <td align="right" width="20%" class="table-title">邮箱:</td>
               <td width="75%"><input type="text" name="email" id="email" style="width:300px" class="easyui-validatebox" data-options="required:true,validType:['email','length[0,40]']" missingMessage="邮箱不能为空!"/></td>
           </tr>
            <tr class=table-content>
                <td width="100%" colSpan=2 align=center><input type="button" value="重置密码" onclick="findPwd()"/></td>
            </tr>
           <tr class="table-content">
               <td align="left" width="100%" colspan="2">
                   <div>
                       <span style="color:darkred;">
                           说明：<br>1.普通用户如果忘记密码可联系管理员,由管理员进行密码重置,管理员用户只能通过此方法进行密码重设.<br>2.用户只有配置了可用的邮箱才可以通过此方法重新设置密码.
                       </span>
                   </div>
               </td>
           </tr>
        </table>
        </form>
    </div>
</div>
</body>
</html>