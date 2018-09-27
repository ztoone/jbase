<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<head>
<title>BI 管理平台</title>
</head>
<script>
	setTimeout("logout()", 1)
	function logout(){
		window.location.href = "../login/login";
	}
</script>
<%
	session.invalidate();
	//response.sendRedirect("../login/login");
%>
</html>