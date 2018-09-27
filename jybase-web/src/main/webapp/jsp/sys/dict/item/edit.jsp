<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/dict/item/dictItem.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑字典项</title>
</head>
<script>
    $(function(){
    });
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <form id="editForm" style="padding-top:10px;padding-left:15px;">
            <input type="hidden" name="id" value="${item.id}"/>
            <input type="hidden" name="dictId" value="${item.dictId}"/>
            <table id="filter_table" width="90%">
                <tr>
                    <td>名称：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="name" maxlength="100"  style="width:60%;" value="${item.name}"/>
                    </td>
                </tr>
                <tr>
                    <td>编码：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="code" maxlength="100"  style="width:60%;" value="${item.code}"/>
                    </td>
                </tr>
                <tr>
                    <td>描述：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="desc" maxlength="100" style="width:60%;" value="${item.desc}"/>
                    </td>
                </tr>
                <tr>
                    <td>排序：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="order" maxlength="100" style="width:60%;" value="${item.order}"/>
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