<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/sysparam/sysparam.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加参数</title>
</head>
<script>
    $(function(){
    });
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <form id="addForm" style="padding-top:10px;padding-left:15px;">
            <table id="param_table" width="90%">
                <tr>
                    <td>参数名称：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="name" maxlength="50" data-options="required:true" style="width: 70%;" missingMessage="参数名称不能为空!"/>
                    </td>
                </tr>
                <tr>
                    <td>参数键：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="key" maxlength="100" data-options="required:true" missingMessage="参数键不能为空!" style="width:70%;" validType="remote['${ctx}/sysparam/check']" invalidMessage="参数键已存在!"/>
                    </td>
                </tr>
                <tr>
                    <td>参数值：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="value" maxlength="100" data-options="required:true" style="width: 70%;" missingMessage="参数键不能为空!"/>
                    </td>
                </tr>
                <tr>
                    <td>类型：</td>
                    <td>
                        <label>默认</label>
                        <input type="radio" name="isDefault" value="1" />
                        <label>自定义</label>
                        <input type="radio" name="isDefault" value="0" checked="checked"/>
                    </td>
                </tr>
                <tr>
                    <td>描述：</td>
                    <td>
                        <textarea class="easyui-textarea" rows="3" style="width: 70%;" name="desc"></textarea>
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