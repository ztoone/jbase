function initBaseInfo(){
    var url = ctx + "/sysinfo/sysBaseInfo"
    $.ajax({
        url: url,
        type: "POST",
        dataType: "json",
        success: function (data) {
            if(data.status){
                loadBaseInfo(data.os,data.server);
            }else{
                messageAlert("提示","新增失败!");
            }
        }
    });
}

function loadBaseInfo(os,server){
    $("#sysBaseInfo").datagrid({
        title: "服务器基础信息",
        width:"100%",
        height:"100%",
        fitColumns:true,
        columns:[[
            {field:'key',title:'属性',width:60},
            {field:'value',title:'值',width:200}
        ]],
        data:[
            {"key":"操作系统","value":os.osType},
            {"key":"内核类型","value":os.arch},
            {"key":"系统类型","value":os.bit},
            {"key":"系统时间","value":os.systemTime},
            {"key":"处理器","value":server.cpuVo.cpuInfo},
            {"key":"安装内存","value":server.memoryVo.total},
            {"key":"硬盘容量","value":server.diskVo.total},
            {"key":"JVM","value":server.jvmVo.name},
            {"key":"JVM版本","value":server.jvmVo.version}
        ]
    });
}

var cpuChart;
var memoryChart;
var diskChart;
var diskAreaChart;
var cpuHistoryChar;
var memoryHistoryChar;
var netFlowChar;
function initECharts() {
    require.config({
        paths: {
            echarts:  ctx+"/js/echart/"
        }
    });
    require(['echarts', 'echarts/chart/gauge', 'echarts/chart/bar', 'echarts/chart/line'],
        function (ec) {
            var cpuDiv = $("#cpudiv");
            var memoryDiv = $("#memorydiv");
            var diskDiv = $("#diskdiv");
            var diskAreaDiv = $("#diskareadiv");
            var cpuHistoryDiv = $("#cpuhistorydiv");
            var memoryHistoryDiv = $("#memoryhistorydiv");
            var netFlowDiv = $("#netflowdiv");
            cpuChart = ec.init(cpuDiv[0]);
            memoryChart = ec.init(memoryDiv[0]);
            diskChart = ec.init(diskDiv[0]);
            diskAreaChart = ec.init(diskAreaDiv[0]);
            cpuHistoryChar = ec.init(cpuHistoryDiv[0]);
            memoryHistoryChar = ec.init(memoryHistoryDiv[0]);
            netFlowChar = ec.init(netFlowDiv[0]);
            timer();
        }
    );
}

var timer1;
var time = 1 * 1000;//1秒
function timer() {
    loadData();
    timer1 = setTimeout("timer()", time);
}

var cpuHistorySeriesArray = new Array();
var memoryHistorySeriesArray = new Array();
var netFlowSeriesInArray = new Array();
var netFlowSeriesOutArray = new Array();
var netFlowSeriesInSpeedArray = new Array();
var netFlowSeriesOuSpeedtArray = new Array();
function loadData() {
    var url = ctx + "/sysinfo/sysBaseInfo"
    $.ajax({
        url: url,
        type: "post",
        dataType: 'json',
        success: function (data) {
            if (!data.status) {
                if (data.data == "t") {
                    $("#warn").show();
                }
            }
            //cpu最新情况
            var cpuUsedPercent = data.server.cpuVo.usedPercent;
            var cpuOption = getCpuOption();
            cpuOption.series[0].data = [{name: "CPU", value: cpuUsedPercent}];
            cpuChart.setOption(cpuOption);

            //内存最新情况
            var memoryUsedPercent = data.server.memoryVo.usedPercent;
            var memoryOption = getMemoryOption();
            memoryOption.series[0].data = [{name: "内存", value: memoryUsedPercent}];
            memoryChart.setOption(memoryOption);

            //硬盘最新情况
            var diskUsedPercent = data.server.diskVo.usedPercent;
            var diskOption = getDiskOption();
            diskOption.series[0].data = [{name: "硬盘", value: diskUsedPercent}];
            diskChart.setOption(diskOption);

            //硬盘分区使用情况
            var diskAreaOption = getDiskAreaOption();
            var seriesArray = [];
            var yAxisArray = [];
            var diskAreas = data.server.diskVo.disks;
            for (var i = 0; i < diskAreas.length; i++) {
                var temp = {
                    used: diskAreas[i]['used'],
                    free: diskAreas[i]['free']
                };
                seriesArray[i] = temp;
                yAxisArray[i] = diskAreas[i]['diskName'] + "," + diskAreas[i]['total'] + "," + diskAreas[i]['diskFileType'];
            }
            seriesArray.reverse();
            for (var i = 0; i < seriesArray.length; i++) {
                diskAreaOption.series[0].data.push(seriesArray[i].used);
                diskAreaOption.series[1].data.push(seriesArray[i].free);
            }
            diskAreaOption.yAxis[0].data = yAxisArray.reverse();
            diskAreaChart.setOption(diskAreaOption);

            //cpu历史使用曲线图
            var cpuHistoryOption = getCpuHistoryOption();
            var newObj = {name: 'Cpu使用率', value: cpuUsedPercent};
            if (cpuHistorySeriesArray.length == 60) {
                cpuHistorySeriesArray.removeByIndex(0);
                cpuHistorySeriesArray.push(newObj);
            } else {
                cpuHistorySeriesArray.push(newObj);
            }
            cpuHistoryOption.series[0].data = cpuHistorySeriesArray;
            cpuHistoryChar.setOption(cpuHistoryOption);

            //内存历史使用曲线图
            var memoryHistoryOption = getMemoryHistoryOption();
            var newObj = {name: '内存使用率', value: memoryUsedPercent};
            if (memoryHistorySeriesArray.length == 60) {
                memoryHistorySeriesArray.removeByIndex(0);
                memoryHistorySeriesArray.push(newObj);
            } else {
                memoryHistorySeriesArray.push(newObj);
            }
            memoryHistoryOption.series[0].data = memoryHistorySeriesArray;
            memoryHistoryChar.setOption(memoryHistoryOption);

            //网络流量曲线图
            var netFlowIn = data.server.netVo.in;
            var netFlowOut = data.server.netVo.out;
            if (netFlowSeriesInArray.length == 60) {
                netFlowSeriesInArray.removeByIndex(0);
                netFlowSeriesOutArray.removeByIndex(0);
                netFlowSeriesInSpeedArray.removeByIndex(0);
                netFlowSeriesOuSpeedtArray.removeByIndex(0);
                netFlowSeriesInArray.push(netFlowIn);
                netFlowSeriesOutArray.push(netFlowOut);
                var inSpeed = netFlowIn - netFlowSeriesInArray[58];
                var outSpeed = netFlowOut - netFlowSeriesOutArray[58];
                var newObjInSpeed = {name: '接收', value: inSpeed};
                var newObjOutSpeed = {name: '发送', value: outSpeed};
                netFlowSeriesInSpeedArray.push(newObjInSpeed);
                netFlowSeriesOuSpeedtArray.push(newObjOutSpeed);
            } else if(netFlowSeriesInArray.length == 0){
                netFlowSeriesInArray.push(netFlowIn);
                netFlowSeriesOutArray.push(netFlowOut);
                var newObjInSpeed = {name: '接收', value: 0};
                var newObjOutSpeed = {name: '发送', value: 0};
                netFlowSeriesInSpeedArray.push(newObjInSpeed);
                netFlowSeriesOuSpeedtArray.push(newObjOutSpeed);
            }else{
                netFlowSeriesInArray.push(netFlowIn);
                netFlowSeriesOutArray.push(netFlowOut);
                var inSpeed = netFlowIn - netFlowSeriesInArray[netFlowSeriesInArray.length-2];
                var outSpeed = netFlowOut - netFlowSeriesOutArray[netFlowSeriesOutArray.length-2];
                var newObjInSpeed = {name: '接收', value: inSpeed};
                var newObjOutSpeed = {name: '发送', value: outSpeed};
                netFlowSeriesInSpeedArray.push(newObjInSpeed);
                netFlowSeriesOuSpeedtArray.push(newObjOutSpeed);
            }
            var netFlowOption = getNetFlowOption();
            netFlowOption.series[0].data = netFlowSeriesInSpeedArray;
            netFlowOption.series[1].data = netFlowSeriesOuSpeedtArray;
            netFlowChar.setOption(netFlowOption);

        }
    });
}

function getCpuOption() {
    var cpuOption = {
        title: {
            text: 'Cpu使用情况',
            x: 'center',
            y: 'bottom'
        },
        tooltip: {
            formatter: "{a} <br/>{b} : {c}%"
        },
        series: [
            {
                name: 'Cpu使用率',
                type: 'gauge',
                splitNumber: 10,       // 分割段数，默认为5
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: [[0.2, '#228b22'], [0.8, '#48b'], [1, '#ff4500']],
                        width: 8
                    }
                },
                axisTick: {            // 坐标轴小标记
                    splitNumber: 10,   // 每份split细分多少段
                    length: 12,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    show: true,        // 默认显示，属性show控制显示与否
                    length: 30,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                pointer: {
                    width: 5
                },
                title: {
                    show: true,
                    offsetCenter: [0, '-40%'],       // x, y，单位px
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        fontWeight: 'bolder'
                    }
                },
                detail: {
                    formatter: '{value}%',
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        color: 'auto',
                        fontWeight: 'bolder'
                    }
                },
                data: []
            }
        ]
    };
    return cpuOption;
}

function getMemoryOption() {
    var memoryOption = {
        title: {
            text: '内存使用情况',
            x: 'center',
            y: 'bottom'
        },
        tooltip: {
            formatter: "{a} <br/>{b} : {c}%"
        },
        series: [
            {
                name: '内存使用率',
                type: 'gauge',
                splitNumber: 10,       // 分割段数，默认为5
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: [[0.2, '#228b22'], [0.8, '#48b'], [1, '#ff4500']],
                        width: 8
                    }
                },
                axisTick: {            // 坐标轴小标记
                    splitNumber: 10,   // 每份split细分多少段
                    length: 12,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    show: true,        // 默认显示，属性show控制显示与否
                    length: 30,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                pointer: {
                    width: 5
                },
                title: {
                    show: true,
                    offsetCenter: [0, '-40%'],       // x, y，单位px
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        fontWeight: 'bolder'
                    }
                },
                detail: {
                    formatter: '{value}%',
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        color: 'auto',
                        fontWeight: 'bolder'
                    }
                },
                data: []
            }
        ]
    };
    return memoryOption;
}

function getDiskOption() {
    var diskOption = {
        title: {
            text: '硬盘使用情况',
            x: 'center',
            y: 'bottom'
        },
        tooltip: {
            formatter: "{a} <br/>{b} : {c}%"
        },
        series: [
            {
                name: '硬盘使用率',
                type: 'gauge',
                splitNumber: 10,       // 分割段数，默认为5
                axisLine: {            // 坐标轴线
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: [[0.2, '#228b22'], [0.8, '#48b'], [1, '#ff4500']],
                        width: 8
                    }
                },
                axisTick: {            // 坐标轴小标记
                    splitNumber: 10,   // 每份split细分多少段
                    length: 12,        // 属性length控制线长
                    lineStyle: {       // 属性lineStyle控制线条样式
                        color: 'auto'
                    }
                },
                axisLabel: {           // 坐标轴文本标签，详见axis.axisLabel
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        color: 'auto'
                    }
                },
                splitLine: {           // 分隔线
                    show: true,        // 默认显示，属性show控制显示与否
                    length: 30,         // 属性length控制线长
                    lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                        color: 'auto'
                    }
                },
                pointer: {
                    width: 5
                },
                title: {
                    show: true,
                    offsetCenter: [0, '-40%'],       // x, y，单位px
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        fontWeight: 'bolder'
                    }
                },
                detail: {
                    formatter: '{value}%',
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        color: 'auto',
                        fontWeight: 'bolder'
                    }
                },
                data: []
            }
        ]
    };
    return diskOption;
}

function getDiskAreaOption() {
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            },
            formatter: function (params) {
                var nameArray = params[0].name.split(",");
                var res = nameArray[0] + "<br/>格式:" + nameArray[2] + "<br/>容量:" + nameArray[1] + " GB";
                for (var i = 0, l = params.length; i < l; i++) {
                    res += "<br/>" + params[i].seriesName + ":" + params[i].value + " GB";
                }
                return res;
            }
        },
        grid: {
            x: 60, y: 50, x2: 30, y2: 50, borderWidth: 0
        },
        color: ['#2894FF', '#c4e1FF'],
        xAxis: [
            {
                type: 'value'
            }
        ],
        yAxis: [
            {
                axisLabel: {
                    formatter: function (label) {
                        return label.split(",")[0];
                    }
                },
                type: 'category',
                data: []
            }
        ],
        series: [
            {
                name: '已用',
                type: 'bar',
                stack: '总量',
                barGap: 3,
                barMaxWidth: 20,
                itemStyle: {normal: {label: {show: false, position: 'insideRight'}}},
                data: []
            },
            {
                name: '可用',
                type: 'bar',
                stack: '总量',
                barGap: 3,
                barMaxWidth: 20,
                itemStyle: {normal: {label: {show: false, position: 'insideRight'}}},
                data: []
            }
        ]
    };
    return option;
}

function getCpuHistoryOption() {
    var option = {
        tooltip: {
            trigger: 'axis'
        },
        calculable: true,
        animation: false,
        grid: {
            x: 40, y: 50, x2: 20, y2: 50, borderWidth: 0
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',
                    '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',
                    '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',
                    '', '', '', '', '', '', ''
                ]
            }
        ],
        yAxis: [
            {
                type: 'value',
                min: 0,
                max: 100
            }
        ],
        series: [
            {
                name: 'cpu使用率',
                type: 'line',
                stack: '总量',
                data: [],
                smooth: true,
                symbol: "none"
            }
        ]
    };
    return option;
}

function getMemoryHistoryOption() {
    var option = {
        /* title: {
         text: '内存使用记录',
         x: 'center',
         y: 'bottom'
         },*/
        tooltip: {
            trigger: 'axis'
        },
        calculable: true,
        animation: false,
        grid: {
            x: 40, y: 50, x2: 20, y2: 50, borderWidth: 0
        },
        xAxis: [
            {
                type: 'category',
                boundaryGap: false,
                data: ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',
                    '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',
                    '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',
                    '', '', '', '', '', '', ''
                ]
            }
        ],
        yAxis: [
            {
                type: 'value',
                min: 0,
                max: 100
            }
        ],
        series: [
            {
                name: '内存使用率',
                type: 'line',
                stack: '总量',
                data: [],
                smooth: true,
                symbol: "none"
            }
        ]
    };
    return option;
}

function getNetFlowOption(){
    var option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'
            },
            formatter: function (params) {
                var res = "";
                for(var i=0;i<params.length;i++){
                    res += params[i].seriesName +":" + params[i].value + "KB/S<br>";
                }
                return res;
            }
        },
        legend: {
            data:['发送','接收']
        },
        calculable: true,
        animation: false,
        grid: {
            x: 40, y: 50, x2: 20, y2: 50, borderWidth: 0
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data: ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',
                    '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',
                    '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '',
                    '', '', '', '', '', '', ''
                ]
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'发送',
                type:'line',
                data:[],
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                smooth: true,
                symbol: "none"
            },
            {
                name:'接收',
                type:'line',
                stack: '总量',
                data:[],
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                smooth: true,
                symbol: "none"
            }
        ]
    };
    return option;
}