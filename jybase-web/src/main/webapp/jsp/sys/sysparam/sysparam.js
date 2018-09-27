/**
 * Created by Administrator on 2016/6/3 0020.
 */
function loadList(){
    $("#list_table").datagrid({
        url:ctx+"/sysparam/list",
        title: "参数管理",
        width: "100%",
        rownumbers: true,
        singleSelect:true,
        pagination:true,
        height:"auto",
        columns:[[
            {field:'ck',align:'center',checkbox:true},
            {field:'name',title:'参数名称',width:200,align:'center'},
            {field:'key',title:'参数Key',width:200,align:'center'},
            {field:'value',title:'参数值',width:200,align:'center',editor: 'text'},
            {field:'isDefault',title:'类型',width:200,align:'center',editor: 'text',formatter:function(value){
                if(value=='1'){
                    return '默认';
                }else{
                    return '自定义';
                }
            }},
            {field:'desc',title:'描述',width:300,align:'center',editor: 'text'}
        ]]
    });
}

/**
 * 显示添加页面
 */
function add(){
    var s = createFrame(ctx+"/jsp/sys/sysparam/add.jsp");
    getDialog().dialog({
        title: '添加参数',
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
    var url = ctx + "/sysparam/save";
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
 * 批量删除用户
 */
function del(){
    var url = ctx + "/sysparam/del";
    tableGridDelete(url,"list_table");
}

function beforeDel(){
    var rows = $("#list_table").datagrid('getChecked');
    if(rows[0].isDefault == 1){//系统默认参数
        messageShow('提示','系统默认参数不能删除!','show');
        return false;
    }
    return true;
}
/**
 * 修改参数
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
    var s = createFrame(ctx+"/sysparam/toEdit/"+rows[0].id);
    getDialog().dialog({
        title: '编辑参数',
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
    var url = ctx + "/sysparam/update";
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