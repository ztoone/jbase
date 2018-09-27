/**
 * Created by Administrator on 2016/5/20 0020.
 */

function loadList(resId){
    $("#list_table").datagrid({
        url:doSearcha(resId),
        title: "按钮列表",
        width: "100%",
        rownumbers: true,
        singleSelect:true,
        height:"auto",
        pagination:true,
        columns:[[
            {field:'ck',align:'center',checkbox:true},
            {field:'name',title:'名称',width:150,align:'center'},
            {field:'event',title:'事件',width:150,align:'center'},
            {field:'icon',title:'图标/样式',width:100,align:'center',editor: 'text'},
            {field:'desc',title:'描述',width:200,align:'center',editor: 'text'},
            {field:'order',title:'排序',width:70,align:'center',editor: 'text'},
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
    var s = createFrame(ctx+"/jsp/sys/btn/add.jsp");
    getDialog().dialog({
        title: '添加按钮',
        width: 600,
        height:400,
        closed: false,
        cache: false,
        content:s,
        modal: true
    });
}

/**
 * 批量删除用户
 */
function del(){
    var url = ctx + "/btn/del";
    tableGridDelete(url,"list_table");
}

/**
 * 修改用户
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
    var s = createFrame(ctx+"/btn/toEdit/"+rows[0].id);
    getDialog().dialog({
        title: '编辑按钮',
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
    var url = ctx + "/btn/save";
    var formData = $("#addForm").serialize();
    formData = (formData + "&resId=" + parent.parent.curResId);
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parent.closeDialog();
                parent.loadList(parent.parent.curResId);
            }else{
                messageAlert("提示","新增失败!");
            }
        }
    });
}

/**
 * 更新
 */
function update(){
    var result = $("#editForm").form('validate');
    if(!result)
        return;
    var url = ctx + "/btn/update";
    var formData = $("#editForm").serialize();
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parent.closeDialog();
                parent.loadList(parent.parent.curResId);
            }else{
                messageAlert("提示","更新失败!");
            }
        }
    });
}

//重写doSearch()
function doSearcha(resId,url,pageNumber,pageSize){
    url = ctx+"/btn/list/"+resId;
    dealSearch(url,pageNumber,pageSize);
}