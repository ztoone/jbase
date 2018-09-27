<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/dict/dict.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加字典</title>
</head>
<c:set var="curDictId" value=""/>
<script>
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <form id="addForm" style="padding-top:10px;padding-left:15px;">
            <table id="list_table" width="90%">
                <tr>
                    <td>名称：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="name" maxlength="100" data-options="required:true" missingMessage="字典名称不能为空!" style="width:60%;" validType="remote['${ctx}/dict/check']" invalidMessage="字典已存在!"/>
                    </td>
                </tr>
                <tr>
                    <td>描述：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="desc" maxlength="100" style="width:60%;"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'south',border:false">
        <div class="dialog-button">
            <a id="ok" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" href="javascript:void(0)" onclick="psave()">确定</a>
            <a id="cancel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" href="javascript:void(0)" onclick="window.parent.closeDialog();">取消</a>
        </div>
    </div>
</div>
</body>
</html>