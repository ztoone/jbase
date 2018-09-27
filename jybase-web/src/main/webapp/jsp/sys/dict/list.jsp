<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/jsp/sys/dict/dict.js"></script>
<script type="text/javascript" src="${ctx}/jsp/sys/dict/item/dictItem.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>字典管理</title>
</head>
<script>
    var curDictItemId;
    var curDictId = "root";
    $(function(){
        loadDictTree();
        loadList("root");
        pgEvent();
    });
</script>
<style>

</style>
<body class="easyui-layout">
<div data-options="region:'west',split:false,border:false" style="width:200px;padding-right:1px;">
    <div class="easyui-panel" data-options="title:'字典列表',fit:true,tools:[{iconCls:'icon-add',handler:function(){padd();}},{iconCls:'icon-edit',handler:function(){pedit()}},{iconCls:'icon-remove',handler:function(){pdel();}}]" style="padding-left:5px;">
        <ul id="dictTree" class="easyui-tree"></ul>
    </div>
    <%--<div id="tab-tools" style="height:30px">
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-add'" onclick="padd()"></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-edit'" onclick="pedit()"></a>
        <a href="javascript:void(0)" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" onclick="pdel()"></a>
    </div>--%>
</div>
<div id="centerDiv" data-options="region:'center'">
    <jybase:navigator model="dict"/>
    <jybase:filter clzName="DictionaryItemBean"/>
    <table id="list_table"></table>
</div>
</body>
</html>