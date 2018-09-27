<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<script type="text/javascript" src="${ctx}/js/echart/echarts.js"></script>
<html>
<head>
    <meta charset="UTF-8">
    <title>报表</title>
</head>
<script>
    $(function() {
        initECharts();
    });

    var chart;
    function initECharts() {
        require.config({
            paths: {
                echarts:  ctx+"/js/echart/"
            }
        });
        require(['echarts', 'echarts/chart/gauge', "echarts/chart/pie",'echarts/chart/bar', 'echarts/chart/line'],
                function (ec) {
                    var chartDiv = $("#chartdiv");
                    chart = ec.init(chartDiv[0]);
                }
        );
        loadData();
    }

    function loadData() {
        var option = getOption();
        var url = ctx + "/chart/load/logAnalyse";
        var param = {period:'201606'};
        $.get(url,{paramJson:JSON.stringify(param)},function (data) {
            if(data){
                var labels = new Array();
                var cdata = new Array();
                for(var i=0;i<data.length;i++){
                    labels.push(data[i].MODEL);
                    cdata.push(data[i].TOTAL);
                }
                option.xAxis[0].data = labels;
                option.series[0].data = cdata;
                chart.setOption(option);
            }
        });
    }

    function getOption(){
        var option = {
            title : {
                text: '模块访问统计'
            },
            tooltip : {
                trigger: 'axis'
            },
            xAxis : [
                {
                    type : 'category',
                    axisLine:{show:false},
                    splitLine:{show:true,lineStyle:{type:'dashed'}},
                    data : []
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    splitLine:{show:true},
                    axisLine:{show:false},
                    axisLabel:{margin:20}
                }
            ],
            series : [
                {
                    name:'访问次数',
                    barMaxWidth:40,
                    barCategoryGap: 5,
                    barGap:2,
                    type:'bar',
                    data:[]
                }
            ]
        };
        return option;
    }
</script>
<body class="easyui-layout">
<div id="tgResList-toolbar" style="padding:2px 0;" class="datagrid-toolbar">
    <table cellpadding="0" cellspacing="0">
        <tr>
            <td style="padding-left:2px">
                <a href="javascript:void ()" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true"
                   style="float: left;">查询</a>
            </td>
        </tr>
    </table>
</div>
<div id="chartdiv" style="width:100%;height:90%"></div>
</body>
</html>