/**
 * Created by Administrator on 2016/5/20 0020.
 */
function loadList(){
    $("#list_table").datagrid({
        url:doSearch(),
        title: "系统日志",
        width: "100%",
        rownumbers: true,
        pagination:true,
        height:"auto",
        columns:[[
            {field:'ck',align:'center',checkbox:true},
            {field:'userName',title:'操作人',width:100,align:'center'},
            {field:'model',title:'模块',width:150,align:'center'},
            {field:'context',title:'内容',width:200,align:'center',editor: 'text'},
            {field:'uri',title:'资源',width:200,align:'center',editor: 'text'},
            {field:'type',title:'日志类型',width:100,align:'center',editor: 'text'},
            {field:'ip',title:'IP地址',width:150,align:'center',editor: 'text'},
            {field:'timeSpend',title:'耗时(毫秒)',width:100,align:'center',editor: 'text'},
            {field:'time',title:'操作时间',width:200,align:'center',editor: 'text'}
        ]]
    });
}