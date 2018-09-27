<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/btn/btn.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑按钮</title>
</head>
<script>
    $(function(){
    });
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <form id="editForm" style="padding-top:10px;padding-left:15px;">
            <input type="hidden" name="id" value="${btn.id}"/>
            <input type="hidden" name="resId" value="${btn.resId}"/>
            <table width="90%">
                <tr>
                    <td>名称：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="name" maxlength="50" data-options="required:true" style="width: 60%;" value="${btn.name}"/>
                    </td>
                </tr>
                <tr>
                    <td>事件：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="event" maxlength="100" data-options="required:true" style="width: 60%;" value="${btn.event}"/>
                    </td>
                </tr>
                <tr>
                    <td>图标/样式：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="icon" maxlength="100" data-options="required:true" style="width: 60%;" value="${btn.icon}"/>
                    </td>
                </tr>
                <tr>
                    <td>排序：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="order" maxlength="100" data-options="required:true" style="width: 60%;" value="${btn.order}"/>
                    </td>
                </tr>
                <tr>
                    <td>描述：</td>
                    <td>
                        <textarea class="easyui-textarea" class="easyui-textarea" rows="5" cols="21" name="desc" style="width: 100%;">${btn.desc}</textarea>
                    </td>
                </tr>
                <tr>
                    <td>是否启用：</td>
                    <td>
                        <label>启用</label>
                        <input type="radio" name="enabled" value="1" checked="checked"/>
                        <label>不启用</label>
                        <input type="radio" name="enabled" value="0"/>
                        <script>setRadio("editForm","enabled",${btn.enabled});</script>
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