<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/sysparam/sysparam.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑参数</title>
</head>
<script>
    $(function(){
    });
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <form id="editForm" style="padding-top:10px;padding-left:15px;">
            <input type="hidden" name="id" value="${sysparam.id}"/>
            <table width="90%">
                <tr>
                    <td>参数名称：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="name" maxlength="50" data-options="required:true" style="width: 60%;" value="${sysparam.name}"/>
                    </td>
                </tr>
                <tr>
                    <td>参数键：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="key" maxlength="100" data-options="required:true" style="width: 60%;" value="${sysparam.key}"/>
                    </td>
                </tr>
                <tr>
                    <td>参数值：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="value" maxlength="100" data-options="required:true" style="width: 60%;" value="${sysparam.value}"/>
                    </td>
                </tr>
                <tr>
                    <td>类型：</td>
                    <td>
                        <label>默认</label>
                        <input type="radio" name="isDefault" value="1" checked="checked"/>
                        <label>自定义</label>
                        <input type="radio" name="isDefault" value="0"/>
                        <script>setRadio("editForm","isDefault",${sysparam.isDefault});</script>
                    </td>
                </tr>
                <tr>
                    <td>描述：</td>
                    <td>
                        <textarea class="easyui-textarea" class="easyui-textarea" rows="5" cols="21" name="desc" style="width: 60%;">${sysparam.desc}</textarea>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south',border:false">
        <div class="dialog-button">
            <a id="ok" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="update()">确定</a>
            <a id="cancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="window.parent.closeDialog();">取消</a>
        </div>
    </div>
</div>
</body>
</html>