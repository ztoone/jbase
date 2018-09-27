/**
 * Created by Administrator on 2016/5/17 0017.
 */
function createFrame(url){
    var iFrame = $("<iframe/>");
    iFrame.attr("src",url);
    iFrame.attr("id","frameContent");
    iFrame.attr("width","100%");
    iFrame.attr("height","100%");
    iFrame.attr("frameBorder","0");
    iFrame.attr("scrolling","auto");
    iFrame.attr("marginHeight","0");
    iFrame.attr("marginWidth","0");
    iFrame.attr("frameSpacing","0");
    return iFrame;
}

var divDialog = null;
function getDialog() {
    if (divDialog == null)
        divDialog = $('<div id="common_dialog"></div>');
    return divDialog;
};

/**
 * 弹出消息
 * @param title
 * @param msg
 * @param showType
 * @param callback
 */
function messageAlert(title, msg, icon, callback) {
    $.messager.alert(title, msg, icon, callback);
}

/**
 * 显示消息,会自动消失
 * @param title
 * @param msg
 * @param showType
 * @param callback
 */
function messageShow(title, msg, showType, callback) {
    $.messager.show({
        title : title,
        msg : msg,
        showType : showType,
        showSpeed : 200,
        width : 250,
        height : 50,
        timeout : 1000,
        style : {
            right : '',
            top : document.body.scrollTop + document.documentElement.scrollTop
            + 10,
            bottom : ''
        }
    });
}

parentReload = function(){
    window.parent.location.reload();
}

/**
 * 批量删除表格数据
 *
 * @param dgId
 */
function beforeDel(){return true}

tableGridDelete = function(url,tgId) {
    var rows = $("#" + tgId).datagrid('getChecked');
    if(rows==null || rows == ""){
        messageShow('提示','请至少选择一项进行删除!','show');
        return;
    }
    if(!beforeDel()){
        return;
    }
    $.messager.defaults = { ok: "确定", cancel: "取消",width:"288px",height:""};
    $.messager.confirm("确认删除","是否删除数据？",function(r){
        if(r){
            var idArray = new Array();
            var indexArray = new Array();
            $.each(rows,function(i,n){
                if(n.id!=undefined){
                    indexArray.push($('#' + tgId).datagrid('getRowIndex',n));
                    idArray.push(n.id);
                }
            });
            $.post(url,{'ids':idArray.toString()},function(data){
                if(data.status){
                    deleteTableRows(tgId,indexArray);
                }else{
                    if(data.msg){
                        messageAlert("提示",data.msg);
                    }else{
                        messageAlert("提示","删除失败!");
                    }

                }
            });
        }
    });
};

/**
 * 批量删除树表格数据
 * @param dgId
 */
treeGridDelete = function(url,tgId){
    var rows = $("#"+tgId).datagrid('getChecked');
    if(rows==null || rows == ""){
        messageShow('提示','请至少选择一项进行删除!','show');
        return;
    }
    $.messager.defaults = { ok: "确定", cancel: "取消",width:"288px",height:""};
    $.messager.confirm("确认删除","是否删除数据？",function(r){
        if(r){
            var idArray = new Array();
            var indexArray = new Array();
            $.each(rows,function(i,n){
                if(n.id!=undefined){
                    idArray.push(n.id);
                    indexArray.push(n.id);
                }
            });
            //判断选中项是否包含有父级节点
            var hasParentRes = false;
            for(var i=0;i<rows.length;i++){
                var childRows = $("#"+tgId).treegrid("getChildren",rows[i].id);
                if(childRows.length>0){
                    hasParentRes = true;
                    break;
                }
            }
            if(hasParentRes){
                messageAlert("提示","请先删除子节点!");
            }else{
                $.post(url,{'ids':idArray.toString()},function(data){
                    if(data.status){
                        for(var i=0;i<indexArray.length;i++){
                            deleteTreeRows(tgId,indexArray);
                        }
                    }else{
                        if(data.msg){
                            messageAlert("提示",data.msg);
                        }else{
                            messageAlert("提示","删除失败!");
                        }
                    }
                });
            }
        }
    });
}

/**
 * 删除table的行
 * @param table
 * @param ids
 */
deleteTableRows = function(table, ids) {// 表格需要倒序删除，否则顺序删除行号有问题
    for (var i = ids.length - 1; i >= 0; i--) {
        $("#"+table).datagrid('deleteRow', ids[i]);
    }
};

/**
 * 删除tree的行
 * @param table
 * @param ids
 */
deleteTreeRows = function(table, ids){
    for (var i = ids.length - 1; i >= 0; i--) {
        $('#' + table).treegrid('pop', ids[i]);
    }
}

/**
 * 设置默认选中的单选按钮
 * @param table
 * @param ids
 */
setRadio = function(tbName,name,value){
    var radio = $("#"+tbName).find("input[type='radio'][name='"+name+"'][value='"+value+"']");
    try{
        radio.attr("checked","checked");
    }catch(e){
        messageShow('错误',"脚本错误:"+e,"show");
    }
}

/**
 * 关闭弹出框
 */
function closeDialog() {
    divDialog.dialog('close', true);
}

/**
 * 数组根据下标删除元素
 * @param index
 */
Array.prototype.removeByIndex = function(index){
    this.splice(index,1);
}

/**
 * 隐藏查询table
 */
function hideSearchTable(){
    $("#search_table").hide();
}

/**
 * 序列化查询条件
 * @returns {*|jQuery}
 */
function serializeSearchForm(){
    var params = $("#search_form").serialize();
    params = params.replace(/%5B/g,"[");
    params = params.replace(/%5D/g,"]");
    return params;
}

var pageList = new Array(10,20,30,50,100);
/**
 * 分页事件
 */
function pgEvent(){
    var pg = $("#list_table").datagrid("getPager");
    if(pg){
        $(pg).pagination({
            pageSize: pageList[1],//每页显示的记录条数
            pageList:pageList,//可以设置每页记录条数的列表
            beforePageText: '第',//页数文本框前显示的汉字
            afterPageText: '页    共 {pages} 页',
            displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',
            onBeforeRefresh:function(){
            },
            onRefresh:function(pageNumber,pageSize){
                doSearch(undefined,pageNumber,pageSize);
            },
            onChangePageSize:function(pageNumber,pageSize){
                doSearch(undefined,pageNumber,pageSize);
            },
            onSelectPage:function(pageNumber,pageSize){
                doSearch(undefined,pageNumber,pageSize);
            }
        });
    }
}

/**
 * 查询按钮点击事件
 */
function search(){
    var isShow = $("#search_table").is(":hidden");
    if(isShow){
        $("#search_table").show();
    }else{
        $("#search_table").hide();
    }
}


function doSearch(url,pageNumber,pageSize){
    dealSearch(url,pageNumber,pageSize);
}

/**
 * 处理查询
 * @param pageNumber
 * @param pageSize
 */
function dealSearch(url,pageNumber,pageSize){
    if(url == undefined){
        url = $("#list_table").attr("surl");
    }
    if(pageNumber == undefined){
        pageNumber = 1;
    }
    if(pageSize == undefined){
        pageSize = pageList[1];
    }
    var param = serializeSearchForm();
    param = param + "&page="+ pageNumber + "&rows="+pageSize;
    $.post(url,param,function(data){
        $("#list_table").datagrid('loadData', data);
        $("#search_table").hide();
    },"json");
}
