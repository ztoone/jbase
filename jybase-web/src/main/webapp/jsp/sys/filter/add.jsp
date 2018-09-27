<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/filter/filter.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>添加过滤器</title>
</head>
<script>
    $(function(){
    });
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <form id="addForm" style="padding-top:10px;padding-left:15px;">
            <table id="filter_table" width="90%">
                <tr>
                    <td>业务对象：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="clzName" maxlength="50" data-options="required:true" style="width: 70%;" missingMessage="业务对象不能为空!"  validType="remote['${ctx}/filter/check']" invalidMessage="业务对象已存在!"/>
                    </td>
                </tr>
                <tr>
                    <td>描述：</td>
                    <td>
                        <textarea class="easyui-textarea" class="easyui-textarea" rows="5" cols="21" name="desc" style="width: 70%;"></textarea>
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