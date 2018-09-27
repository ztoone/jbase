/**
 * Created by Administrator on 2016/5/20 0020.
 */
/**
 * 动态显示树
 */
function loadDictTree(){
    $("#dictTree").tree ({
        url:ctx+"/dict/jsonTree/0",
        onBeforeExpand:function(node) {
            var url = ctx+"/dict/jsonTree/"+node.id;
            $('#dictTree').tree('options').url = url;
        },
        lines : true,
        onClick : function(node) {
            curDictId = node.id;
            loadList(curDictId);
            pgEvent();
        },
        onLoadSuccess:function(){
            $('#dictTree').tree('expandAll');
        }
    });
}

/**
 * 显示添加页面
 */
function padd(){
    var s = createFrame(ctx+"/jsp/sys/dict/add.jsp");
    getDialog().dialog({
        title: '添加字典',
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
function psave(){
    var result = $("#addForm").form('validate');
    if(!result)
        return;
    var url = ctx + "/dict/save";
    var formData = $("#addForm").serialize();
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parent.closeDialog();
                parent.loadDictTree();
            }else{
                messageAlert("提示","新增失败!");
            }
        }
    });
}

/**
 * 修改
 */
function pedit(){
    if(curDictId == "root"){
        messageAlert("提示","根节点不能修改!");
        return;
    }
    var s = createFrame(ctx+"/dict/toEdit/"+curDictId);
    getDialog().dialog({
        title: '编辑字典',
        width: 400,
        height:200,
        closed: false,
        cache: false,
        content:s,
        modal: true
    });
}

/**
 * 更新
 */
function pupdate(){
    var result = $("#editForm").form('validate');
    if(!result)
        return;
    var url = ctx + "/dict/update";
    var formData = $("#editForm").serialize();
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parent.modify($("#dictName").val());
                parent.closeDialog();
            }else{
                messageAlert("提示","更新失败!");
            }
        }
    });
}

function modify(name){
    var curNode = $('#dictTree').tree('find',curDictId);
    curNode.text = name;
    $('#dictTree').tree("update",curNode);
}

/**
 * 批量删除
 */
function pdel(){
    if(curDictId == "root"){
        messageAlert("提示","根节点不能删除!");
        return;
    }
    var url = ctx + "/dict/del/"+curDictId;
    $.messager.confirm("确认删除","是否删除数据？",function(r){
        if(r){
            $.ajax({
                url: url,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if(data.status){
                        var delNode = $('#dictTree').tree('find',curDictId);
                        $('#dictTree').tree("remove",delNode.target);
                    }else{
                        messageAlert("提示","删除失败!");
                    }
                }
            });
        }
    });
}