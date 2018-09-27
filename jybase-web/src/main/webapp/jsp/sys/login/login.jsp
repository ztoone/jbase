<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/include.jsp" %>
<head>
<title>BI 管理平台</title>
<link href="${ctx}/css/default/login/login.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="${ctx}/jsp/sys/login/login.js"></script>
</head>
<body >
<div class="login_main" style="margin-top: 89.5px; margin-left:300px;">
	<div class="login_top">
		<div class="login_title1"></div>
	</div>
	<div class="login_middle">
		<div class="login_middleleft"></div>
		<div class="login_middlecenter">
			<form id="loginForm" action="${ctx}/login/doLogin" class="login_form" method="POST">
				<input type="hidden" id="basePath" value="$!basePath"/>
				<input type="hidden" id="isForcedExit" value="true"/>
				<div class="login_user">
					<div style="float: left;width:60px"><span class="login_label">用户名：</span></div>
					<div>
						<input type="text" id="account" name="account" title="请输入用户名" value="${username}" style="width:200px"/>
						<span class="login_info">${msg1}</span>
					</div>
				</div>
				<div class="login_pass">
					<div style="float: left;width:60px"><span class="login_label">密&nbsp;码：</span></div>
					<div>
						<input type="password" id="pwd" name="pwd" title="请输入密码" value="" style="width:200px"/>
						<span class="login_info">${msg2}</span>
					</div>
				</div>
				<div class="login_pass">
					<div style="float: left;width:60px"><span class="login_label">验证码：</span></div>
					<div>
						<input id="verifyCode" name="verifyCode" style="width:100px"/>
						<img id="imgObj" alt="验证码" src="${ctx}/login/code/4/80/28/time='+new Date().getTime()" title="点击更换"
							 style="cursor:pointer;vertical-align:middle;margin:0px 0px 0px 10px;"
							 onclick="$(this).attr('src','${ctx}/login/code/4/80/28/time='+new Date().getTime())"/>
						<span class="login_info">${msg3}</span>
					</div>
				</div>
				<div>
					<div class="login_button_left">
						<input type="submit" id="loginBtn" class="login_button blue" value="登陆" onclick="login()"/>
					</div>
					<div class="login_button_center">
						<input type="reset" id="resetBtn" class="login_button blue" value="重置" onclick=""/>
					</div>
					<div class="login_button_right">
						<a href="#" onclick="forgetPwd()">忘记密码?</a>
					</div>

				</div>
				<%--<div class="login_desc">
					<span style="float:left">版权所有@北京精友时代信息技术发展有限公司</span>
				</div>--%>
			</form>
		</div>
	</div>
	<div class="login_bottom">
	</div>
</div>
</body>
</html>