<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/resource/resource.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑菜单</title>
</head>
<script>
    $(function(){
       loadResourceTree();
    });
</script>
<body>
<div class="easyui-layout" data-options="fit:true">
    <div data-options="region:'center',border:false">
        <form id="editForm" style="padding-top:10px;padding-left:15px;">
            <input type="hidden" name="id" value="${res.id}"/>
            <table width="90%">
                <tr>
                    <td>上级资源：</td>
                    <td>
                        <select id="resourceTree" name="pid" class="easyui-combotree" style="width: 300px;">
                            <option selected="selected" value="${res.pid}">${pName}</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>资源名称：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="name" maxlength="50" data-options="required:true" style="width: 100%;" value="${res.name}"/>
                    </td>
                </tr>
                <tr>
                    <td>链接地址：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="url" maxlength="100" style="width: 100%;" value="${res.url}"/>
                    </td>
                </tr>
                <tr>
                    <td>图标/样式：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="icon" maxlength="20" data-options="required:true" style="width: 80%;vertical-align: middle;" value="${res.icon}"/>
                    </td>
                </tr>
                <tr>
                    <td>排序：</td>
                    <td>
                        <input type="text" class="easyui-validatebox" name="order" maxlength="100" data-options="required:true" style="width: 100%;" value="${res.order}"/>
                    </td>
                </tr>
                <tr>
                    <td>描述：</td>
                    <td>
                        <textarea class="easyui-textarea" class="easyui-textarea" rows="5" cols="21" name="desc" style="width: 100%;">${res.desc}</textarea>
                    </td>
                </tr>
                <tr>
                    <td>是否启用：</td>
                    <td>
                        <label>启用</label>
                        <input type="radio" name="enabled" value="1" checked="checked"/>
                        <label>不启用</label>
                        <input type="radio" name="enabled" value="0"/>
                        <script>setRadio("editForm","enabled",${res.enabled});</script>
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