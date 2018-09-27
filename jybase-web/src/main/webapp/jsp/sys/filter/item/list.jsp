<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/filter/item/filterItem.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>过滤器属性列表</title>
</head>
<script>
    $(function () {
        loadList(parent.curFilterId);
        pgEvent();
    });
</script>
<body class="easyui-layout">
<div id="tgResList-toolbar" style="padding:2px 0;" class="datagrid-toolbar">
    <table cellpadding="0" cellspacing="0">
        <tr>
            <td style="padding-left:2px">
                <a href="javascript:add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"
                   style="float: left;">新增</a>
                <a href="javascript:edit()" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"
                   style="float: left;">修改</a>
                <a href="javascript:del()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"
                   style="float: left;">删除</a>
            </td>
        </tr>
    </table>
</div>
<jybase:filter clzName="FilterItemBean"/>
<table id="list_table"></table>
</body>
</html>