/**
 * Created by Administrator on 2016/5/17 0017.
 */
/**
 * 显示添加页面
 */
function add(){
    var s = createFrame(ctx+"/jsp/sys/resource/add.jsp");
    getDialog().dialog({
        title: '添加菜单',
        width: 600,
        height:400,
        closed: false,
        cache: false,
        content:s,
        modal: true
    });
}

/**
 * 批量删除菜单
 */
function del(){
    var url = ctx + "/resource/del";
    treeGridDelete(url,"list_table");
}

/**
 * 修改菜单
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
    var s = createFrame(ctx+"/resource/toEdit/"+rows[0].id);
    getDialog().dialog({
        title: '编辑菜单',
        width: 600,
        height:400,
        closed: false,
        cache: false,
        content:s,
        modal: true
    });
}

/**
 * 动态显示树
 */
function loadResourceTree(){
    $("#resourceTree").combotree ({
        url:ctx+"/resource/jsonTree/0",
        required:true,
        onBeforeExpand:function(node) {
            var url = ctx+"/resource/jsonTree/"+node.id;
            $('#resourceTree').combotree("tree").tree("options").url = url;
        }
    });
}

/**
 * 保存
 */
function save(){
    var result = $("#addForm").form('validate');
    if(!result)
        return;
    var url = ctx + "/resource/save";
    var formData = $("#addForm").serialize();
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parentReload()
            }else{
                messageAlert("提示","新增失败!");
            }
        }
    });
}

/**
 * 保存
 */
function update(){
    var result = $("#editForm").form('validate');
    if(!result)
        return;
    var url = ctx + "/resource/update";
    var formData = $("#editForm").serialize();
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parentReload()
            }else{
                messageAlert("提示","更新失败!");
            }
        }
    });
}

function btn(){
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

    curResId = rows[0].id;
    var s = createFrame(ctx+"/jsp/sys/btn/list.jsp");
    getDialog().dialog({
        title: '按钮管理',
        width: 1000,
        height:600,
        closed: false,
        cache: false,
        content:s,
        modal: true
    });
}