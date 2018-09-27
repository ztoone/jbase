/**
 * Created by Administrator on 2016/6/3 0020.
 */
function loadList(dictId){
    $("#list_table").datagrid({
        url:doSearch(ctx+"/dict/item/list/"+dictId),
        title: "字典项列表",
        width: "100%",
        rownumbers: true,
        singleSelect:true,
        height:"auto",
        pagination:true,
        columns:[[
            {field:'ck',align:'center',checkbox:true},
            {field:'name',title:'名称',width:100,align:'center'},
            {field:'code',title:'编码',width:100,align:'center'},
            {field:'desc',title:'描述',width:350,align:'center'},
            {field:'order',title:'排序',width:100,align:'center'}
        ]]
    });
    $("#list_table").attr("surl",ctx+"/dict/item/list/"+curDictId);
}

/**
 * 显示添加页面
 */
function add(){
    if(curDictId == "root"){
        messageAlert("提示","根节点不能添加项!");
        return;
    }
    var s = createFrame(ctx+"/jsp/sys/dict/item/add.jsp");
    getDialog().dialog({
        title: '添加字典项',
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
    var url = ctx + "/dict/item/save";
    var formData = $("#addForm").serialize();
    formData = (formData + "&dictId=" + parent.curDictId);
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parent.closeDialog();
                parent.loadList(parent.curDictId);
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
    var url = ctx + "/dict/item/del";
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
    var s = createFrame(ctx+"/dict/item/toEdit/"+rows[0].id);
    getDialog().dialog({
        title: '编辑字典项',
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
    var url = ctx + "/dict/item/update";
    var formData = $("#editForm").serialize();
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parent.loadList(parent.curDictId);
                parent.closeDialog();
            }else{
                messageAlert("提示","更新失败!");
            }
        }
    });
}