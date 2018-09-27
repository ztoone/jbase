/**
 * Created by Administrator on 2016/6/3 0020.
 */
function loadList(){
    $("#list_table").datagrid({
        url:ctx+"/role/list",
        title: "角色管理",
        width: "100%",
        rownumbers: true,
        singleSelect:true,
        pagination:true,
        height:"auto",
        columns:[[
            {field:'ck',align:'center',checkbox:true},
            {field:'name',title:'角色名称',width:200,align:'center'},
            {field:'type',title:'角色类型',width:200,align:'center'},
            {field:'desc',title:'描述',width:200,align:'center',editor: 'text'},
            {field:'enabled',title:'状态',width:200,align:'center',editor: 'text',formatter:function(value){
                if(value=='1'){
                    return '启用';
                }else{
                    return '禁用';
                }
            }}
        ]]
    });
}

/**
 * 显示添加页面
 */
function add(){
    var s = createFrame(ctx+"/jsp/sys/role/add.jsp");
    getDialog().dialog({
        title: '添加角色',
        width: 600,
        height:400,
        closed: false,
        cache: false,
        content:s,
        modal: true
    });
}

/**
 * 保存
 */
function save(){
    var result = $("#addForm").form('validate');
    if(!result)
        return;
    var url = ctx + "/role/save";
    var formData = $("#addForm").serialize();
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parent.closeDialog();
                parent.loadList();
            }else{
                messageAlert("提示","新增失败!");
            }
        }
    });
}

/**
 * 批量删除角色
 */
function del(){
    var url = ctx + "/role/del";
    tableGridDelete(url,"list_table");
}

/**
 * 修改角色
 */
function edit(){
    var rows = $("#list_table").datagrid('getChecked');
    if(rows!=null){
        if(rows.length>0){
            if(rows.length>1){
                messageShow('提示','只能对单条数据进行修改!','show');
                return;
            }
        }else{
            messageShow('提示','请选择一行进行修改!','show');
            return;
        }
    }
    var s = createFrame(ctx+"/role/toEdit/"+rows[0].id);
    getDialog().dialog({
        title: '编辑角色',
        width: 600,
        height:400,
        closed: false,
        cache: false,
        content:s,
        modal: true
    });
}

/**
 * 更新
 */
function update(){
    var result = $("#editForm").form('validate');
    if(!result)
        return;
    var url = ctx + "/role/update";
    var formData = $("#editForm").serialize();
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parent.closeDialog();
                parent.loadList();
            }else{
                messageAlert("提示","更新失败!");
            }
        }
    });
}

function assignRes(){
    var rows = $("#list_table").datagrid('getChecked');
    if(rows!=null){
        if(rows.length>0){
            if(rows.length>1){
                messageShow('提示','只能对单条数据进行操作!','show');
                return;
            }
        }else{
            messageShow('提示','请选择一行进行操作!','show');
            return;
        }
    }
    curRoleId = rows[0].id;
    var s = createFrame(ctx+"/jsp/sys/role/assign-res.jsp");
    getDialog().dialog({
        title: '分配资源',
        width: 500,
        height:600,
        closed: false,
        cache: false,
        content:s,
        modal: true
    });
}

//分配资源时点击资源树节点事件
function treeGridOnclick(row){
    var pnode = row;
    while(pnode.pid != "root"){//选中子节点后土迭代将父节点选中
        pnode = $("#res_table").treegrid("getParent",pnode.id);
        $("#res_table").treegrid("select",pnode.id);
    }

    var children = $("#res_table").treegrid("getChildren",row.id);
    for(var i=0;i<children.length;i++){
        $("#res_table").treegrid("unselect",children[i].id);
    }
}
function savePerssion(){
    var rows = $("#res_table").datagrid('getChecked');
    var roleResArray = new Array();
    if(rows.length > 0){
        for(var i=0;i<rows.length;i++){
            if(rows[i] == "id"){
               continue;
            }
            var roleRes = {
                roleId : parent.curRoleId,
                resId : rows[i].resId,
                type : rows[i].type
            };
            roleResArray.push(roleRes);
        }
    }
    var delIds = new Array();
    for(var i=0;i<oldSelRows.length;i++){
        var checkbox = $("#datagrid-row-r1-2-" + oldSelRows[i].resId).find("input[type=checkbox]");
        if(checkbox.length > 0 && !checkbox[0].checked){
            delIds.push(oldSelRows[i].id);
        }
    }
    var url = ctx + "/role/assignres";
    $.post(url,{'roleResJson':JSON.stringify(roleResArray),'delIds':delIds.toString()},function(data){
        if(data.status){
            parent.closeDialog();
        }else{
            messageAlert("提示","分配资源失败!");
        }
    });
}

function loadSelectedRoleRes(){
    var url = ctx + "/role/getSelRes/" + parent.curRoleId;
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        success: function (data) {
            if(data){
                oldSelRows = new Array();
                delSelResIds = new Array();
                for(var i=0;i<data.length;i++){
                    var temp = {
                        id:data[i].id,
                        resId:data[i].resId
                    }
                    oldSelRows.push(temp);
                    $("#res_table").treegrid("select",data[i].resId);
                }
            }
        }
    });
}