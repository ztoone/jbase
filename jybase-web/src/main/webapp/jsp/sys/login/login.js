/**
 * Created by Administrator on 2016/5/12.
 */
function login(){
    $("#loginForm").submit();
}

function forgetPwd(){
    var s = createFrame(ctx+"/jsp/sys/user/forgetpwd.jsp");
    getDialog().dialog({
        title: '找回密码',
        width: 600,
        height:300,
        closed: false,
        cache: false,
        content:s,
        modal: true
    });
}