<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/filter/filter.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑过滤器</title>
</head>
<script>
    $(function(){
    });
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <form id="editForm" style="padding-top:10px;padding-left:15px;">
            <input type="hidden" name="id" value="${filter.id}"/>
            <table width="90%">
            <tr>
                <td>业务对象：</td>
                <td>
                    <input type="text" class="easyui-validatebox input-readonly" name="clzName" maxlength="50" readonly value="${filter.clzName}"/>
                </td>
            </tr>
            <tr>
                <td>描述：</td>
                <td>
                    <textarea class="easyui-textarea" class="easyui-textarea" rows="5" cols="21" name="desc" style="width: 70%;">${filter.desc}</textarea>
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