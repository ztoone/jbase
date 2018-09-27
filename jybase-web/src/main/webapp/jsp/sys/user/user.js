/**
 * Created by Administrator on 2016/5/20 0020.
 */
/**
 * 动态显示树
 */
function loadOrgTree(){
    $("#orgTree").tree ({
        url:ctx+"/org/jsonTree/0",
        onBeforeExpand:function(node) {
            var url = ctx+"/org/jsonTree/"+node.id;
            $('#orgTree').tree('options').url = url;
        },
        lines : true,
        onClick : function(node) {
            curOrgId = node.id;
            loadList(curOrgId);
            pgEvent();
        }
    });
}

function loadList(orgId){
    $("#list_table").datagrid({
        url:ctx+"/user/list/"+orgId,
        title: "用户管理",
        width: "100%",
        rownumbers: true,
        singleSelect:true,
        pagination:true,
        height:"auto",
        columns:[[
            //{field:'id',title:'id',width:200,align:'center',checkbox:true},
            {field:'ck',align:'center',checkbox:true},
            {field:'name',title:'用户名称',width:200,align:'center'},
            {field:'account',title:'用户账号',width:200,align:'center'},
            {field:'pwd',editor:'text',hidden:true},
            {field:'mobile',title:'联系电话',width:200,align:'center',editor: 'text'},
            {field:'email',title:'电子邮箱',width:200,align:'center',editor: 'text'},
            {field:'enabled',title:'状态',width:100,align:'center',editor: 'text',formatter:function(value){
                if(value=='1'){
                    return '启用';
                }else{
                    return '禁用';
                }
            }},
            {field:'type',title:'用户类型',width:100,align:'center',editor: 'text',formatter:function(value){
                if(value=='1'){
                    return '管理员';
                }else{
                    return '普通用户';
                }
            }}
        ]]
    });
    $("#list_table").attr("surl",ctx+"/user/list/"+curOrgId);
}

/**
 * 显示添加页面
 */
function add(){
    var s = createFrame(ctx+"/jsp/sys/user/add.jsp");
    getDialog().dialog({
        title: '添加用户',
        width: 600,
        height:500,
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
    var url = ctx + "/user/del";
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
    var s = createFrame(ctx+"/user/toEdit/"+rows[0].id);
    getDialog().dialog({
        title: '编辑用户',
        width: 600,
        height:500,
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
    var url = ctx + "/user/save";
    var formData = $("#addForm").serialize();
    formData = (formData + "&orgId=" + parent.curOrgId);
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parent.closeDialog();
                parent.loadList(parent.curOrgId);
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
    var url = ctx + "/user/update";
    var formData = $("#editForm").serialize();
    $.ajax({
        url: url,
        type: "POST",
        data:formData,
        dataType: "json",
        success: function (data) {
            if(data.status){
                parent.closeDialog();
                parent.loadList(parent.curOrgId);
            }else{
                messageAlert("提示","更新失败!");
            }
        }
    });
}

function assignRole(){
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
    curUserId = rows[0].id;
    var s = createFrame(ctx+"/jsp/sys/user/assign-role.jsp");
    getDialog().dialog({
        title: '分配角色',
        width: 500,
        height:600,
        closed: false,
        cache: false,
        content:s,
        modal: true
    });
}

function loadRoleList(){
    $("#role_table").datagrid({
        url:ctx+"/role/listall",
        title: "角色列表",
        width: "100%",
        rownumbers: true,
        checkOnSelect:true,
        selectOnCheck:false,
        singleSelect:true,
        height:"100%",
        columns:[[
            {field:'ck',align:'center',checkbox:true},
            {field:'name',title:'角色名称',width:150,align:'center'},
            {field:'type',title:'角色类型',width:150,align:'center'}
        ]]
    });
}

function saveUserRole(){
    var rows = $("#role_table").datagrid('getChecked');
    var uerRoleArray = new Array();
    if(rows.length > 0){
        for(var i=0;i<rows.length;i++){
            var userRole = {
                userId : parent.curUserId,
                roleId : rows[i].id,
                roleName : rows[i].name
            };
            uerRoleArray.push(userRole);
        }
    }

    var delIds = new Array();
    if(oldSelRows != undefined && oldSelRows.length > 0){
        for(var i=0;i<oldSelRows.length;i++){
            var checkbox = $("#datagrid-row-r1-2-" + oldSelRows[i].roleId).find("input[type=checkbox]");
            if(!checkbox[0].checked){
                delIds.push(oldSelRows[i].id);
            }
        }
    }
    var url = ctx + "/user/assignRole";
    $.post(url,{'userRoleJson':JSON.stringify(uerRoleArray),'delIds':delIds.toString()},function(data){
        if(data.status){
            parent.closeDialog();
        }else{
            messageAlert("提示","分配角色失败!");
        }
    });
}

function loadSelectedUserRole(){
    var url = ctx + "/user/getSelRole/" + parent.curUserId;
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        success: function (data) {
            oldSelRows = new Array();
            delSelResIds = new Array();
            if(data){
                for(var i=0;i<data.length;i++){
                    var temp = {
                        id:data[i].id,
                        roleId:data[i].roleId
                    }
                    oldSelRows.push(temp);
                    $("#role_table").treegrid("select",data[i].roleId);
                }
            }
        }
    });
}

function resetPwd(){
    var rows = $("#list_table").datagrid('getChecked');
    if(rows!=null){
        if(rows.length>0){
            if(rows.length>1){
                messageShow('提示','只能对单个用户进行重置!','show');
                return;
            }
        }else{
            messageShow('提示','请选择一个用户进行重置!','show');
            return;
        }
    }
    $.messager.confirm("重置密码","是否重置密码？",function(r){
        if(r){
            var url = ctx + "/user/resetPwd/"+rows[0].id;
            $.ajax({
                url: url,
                type: "POST",
                dataType: "json",
                success: function (data) {
                    if(data.status){
                        messageAlert("提示","密码重置成功!");
                    }else{
                        messageAlert("提示",data.msg);
                    }
                }
            });
        }
    });
}

function findPwd(){
    var result = $("#resetPwdForm").form('validate');
    if(!result)
        return;
    var account = $("#account").val();
    var email = $("#email").val();
    var url = ctx + "/user/reset/check";
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        data:{account : account,email:email},
        success: function (data) {
            $("#resetMsg").html(data.msg);
        }
    });
}