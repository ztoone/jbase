function navHover(obj) {
    if (obj.className.indexOf("nav-selected") < 0) {
        $.each($("#navMenu").find("li"), function (i, li) {
            if (li.className.indexOf("nav-selected") < 0) {
                li.className = "nav-default";
                $(li).find("a[src]").css("fontWeight", "normal");
            }
        });
        obj.className = "nav-hover";
        $(obj).find("a[src]").css("fontWeight", "normal");
    }
}
function navOut(obj) {
    if (obj.className.indexOf("nav-selected") < 0) {
        $.each($("#navMenu").find("li"), function (i, li) {
            if (li.className.indexOf("nav-selected") < 0) {
                li.className = "nav-default";
                $(li).find("a[src]").css("fontWeight", "normal");
            }
        });
    }
}

function onClick(obj) {
    $.each($("#navMenu").find("li"), function (i, li) {
        li.className = "nav-default";
        $(li).find("a[src]").css("fontWeight", "normal");
    });
    $(obj).removeClass("nav-default").addClass("nav-selected");

    var a = $(obj).find("a[src]");

    a.css("fontWeight", "bold");
    navigate(a.attr("src"), a.text(), a.attr("class"));

}

function navigate(url, title, icon) {
    var idx = -1;
    var tabs = $('#mainTabs').tabs("tabs");
    $.each(tabs, function (i, n) {
        if (n.find("iFrame").attr("src") == url)
            idx = i;
    });

    if (idx == -1) {
        $('#mainTabs').tabs('add', {
            content: createFrame(url),
            title: title,
            closable: true,
            icon: icon
        });
    } else {
        var old_idx = $('#mainTabs').tabs("getTabIndex", $('#mainTabs').tabs("getSelected"));
        $('#mainTabs').tabs("select", idx);
        if (old_idx == idx) {
            var c_tab = $("#mainTabs").tabs("getTab", idx);
            c_tab.find("iframe")[0].contentWindow.location.reload();
        }
    }
}


/**
 * 显示修改密码
 */
function changePwd() {
    var s = createFrame(ctx + "/jsp/sys/user/change-pwd.jsp");
    getDialog().dialog({
        title: '修改密码',
        width: 420,
        height: 180,
        closed: false,
        cache: false,
        content: s,
        modal: true
    });
}

function logout(){
    $.messager.confirm("提示","确认退出系统?",function(r) {
        if (r) {
            window.location.href = ctx + "/login/logout";
        }
    });
}

function showOnline(){
    var s = createFrame(ctx + "/jsp/index/online-list.jsp");
    getDialog().dialog({
        title: '在线用户列表',
        width: 1000,
        height: 600,
        closed: false,
        cache: false,
        content: s,
        modal: true
    });
}

function showChild(obj){
    $(obj).css("background","#ffe48d");
    $.each($("#navMenu").find("li"), function (i, li) {
        li.className = "nav-default";
        $(li).find("a[src]").css("fontWeight", "normal");
    });
    //$(obj).removeClass("nav-default").addClass("nav-selected");
    var div = $(obj).next("div");
    if($(div).is(":hidden")){
        $(obj).next("div").show();
        $("#orderSpan").removeClass("order-expend").addClass("order-hide");
    }else{
        $(obj).next("div").hide();
        $("#orderSpan").removeClass("order-hide").addClass("order-expend");
    }
}

function resizeTabs(t){
    if(t==1){
        $("#mainTabs>.tabs-header,#mainTabs>.tabs-panels,#mainTabs>.tabs-panels>.panel,#mainTabs>.tabs-panels>.panel>.panel-body").width($("#mainTabs").width()+"px");
        $("#mainTabs>.tabs-panels,#mainTabs>.tabs-panels>.panel,#mainTabs>.tabs-panels>.panel>.panel-body").height(($("#mainTabs").height()-30)+"px");
    }else{
        $("#mainTabs>.tabs-header,#mainTabs>.tabs-panels,#mainTabs>.tabs-panels>.panel,#mainTabs>.tabs-panels>.panel>.panel-body").width(screen.width*0.88+"px");
        $("#mainTabs>.tabs-panels,#mainTabs>.tabs-panels>.panel,#mainTabs>.tabs-panels>.panel>.panel-body").height(($("#mainTabs").height()-30)+"px");
    }
}