/**
 * Created by Administrator on 2016/6/3 0020.
 */
function loadList(filterId){
    $("#list_table").datagrid({
        url:ctx+"/filter/item/list/"+filterId,
        title: "属性列表",
        width: "100%",
        rownumbers: true,
        singleSelect:true,
        height:"auto",
        pagination:true,
        columns:[[
            {field:'ck',align:'center',checkbox:true},
            {field:'field',title:'属性',width:100,align:'center'},
            {field:'fieldName',title:'属性名称',width:100,align:'center'},
            {field:'restriction',title:'比较方式',width:100,align:'center',formatter:function(value){
                switch (value){
                    case 'Like': return '包含';
                    case 'Eq': return '相等';
                    case 'Between': return '两者之间';
                    case 'Gt':return '大于';
                    case 'Lt':return '小于';
                    case 'Ge':return '大于等于';
                    case 'Le':return '小于等于';
                    case 'LLike':return '左包含';
                    case 'RLike':return '右包含';
                }
            }},
            {field:'fieldType',title:'类型',width:100,align:'center',formatter:function(value){
                switch (value){
                    case 'text': return '文本';
                    case 'select': return '下拉';
                    case 'date':return '日期';
                    case 'datetime':return '日期时间';
                }
            }},
            {field:'order',title:'排序',width:100,align:'center'},
            {field:'enabled',title:'状态',width:100,align:'center',formatter:function(value){
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
    var s = createFrame(ctx+"/jsp/sys/filter/item/add.jsp");
    getDialog().dialog({
        title: '添加属性',
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
    var url = ctx + "/filter/item/save";
    var formData = $("#addForm").serialize();
    formData = (formData + "&filterId=" + parent.parent.curFilterId);
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parent.closeDialog();
                parent.loadList(parent.parent.curFilterId);
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
    var url = ctx + "/filter/item/del";
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
    var s = createFrame(ctx+"/filter/item/toEdit/"+rows[0].id);
    getDialog().dialog({
        title: '编辑属性',
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
    var url = ctx + "/filter/item/update";
    var formData = $("#editForm").serialize();
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parent.closeDialog();
                parent.loadList(parent.parent.curFilterId);
            }else{
                messageAlert("提示","更新失败!");
            }
        }
    });
}