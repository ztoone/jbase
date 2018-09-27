/**
 * Created by Administrator on 2016/6/3 0020.
 */
function loadFilterList(){
    $("#list_table").datagrid({
        url:ctx+"/filter/list",
        title: "过滤器管理",
        width: "100%",
        rownumbers: true,
        singleSelect:true,
        pagination:true,
        height:"auto",
        columns:[[
            {field:'ck',align:'center',checkbox:true},
            {field:'clzName',title:'业务对象',width:200,align:'center'},
            {field:'desc',title:'描述',width:200,align:'center',editor: 'text'}
        ]]
    });
}

/**
 * 显示添加页面
 */
function add(){
    var s = createFrame(ctx+"/jsp/sys/filter/add.jsp");
    getDialog().dialog({
        title: '添加过滤器',
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
    var url = ctx + "/filter/save";
    var formData = $("#addForm").serialize();
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parent.closeDialog();
                parent.loadFilterList();
            }else{
                messageAlert("提示","新增失败!");
            }
        }
    });
}

/**
 * 批量删除
 */
function del(){
    var url = ctx + "/filter/del";
    tableGridDelete(url,"list_table");
}

/**
 * 修改
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
    var s = createFrame(ctx+"/filter/toEdit/"+rows[0].id);
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
    var url = ctx + "/filter/update";
    var formData = $("#editForm").serialize();
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parent.closeDialog();
                parent.loadFilterList();
            }else{
                messageAlert("提示","更新失败!");
            }
        }
    });
}

/**
 * 配置过滤器属性
 */
function cfgField(){
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

    curFilterId = rows[0].id;
    var s = createFrame(ctx+"/jsp/sys/filter/item/list.jsp");
    getDialog().dialog({
        title: '过滤器配置',
        width: 600,
        height:400,
        closed: false,
        cache: false,
        content:s,
        modal: true
    });
}