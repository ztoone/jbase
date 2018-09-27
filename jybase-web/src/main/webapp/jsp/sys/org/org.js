/**
 * Created by Administrator on 2016/5/19 0019.
 */
/**
 * 显示添加页面
 */
function add(){
    var s = createFrame(ctx+"/jsp/sys/org/add.jsp");
    getDialog().dialog({
        title: '添加机构',
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
    var url = ctx + "/org/del";
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
    if(rows[0].id == "root"){
        messageAlert("提示","根节点不能修改!");
        return;
    }
    var s = createFrame(ctx+"/org/toEdit/"+rows[0].id);
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
function loadOrgTree(){
    $("#orgTree").combotree ({
        url:ctx+"/org/jsonTree/0",
        required:true,
        onBeforeExpand:function(node) {
            var url = ctx+"/org/jsonTree/"+node.id;
            $('#orgTree').combotree("tree").tree("options").url = url;
        },onSelect:function(node){
            curOrgId = node.id;
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
    var url = ctx + "/org/save";
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
 * 更新
 */
function update(){
    var result = $("#editForm").form('validate');
    if(!result)
        return;
    var url = ctx + "/org/update";
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