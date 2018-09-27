<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/jsp/common/include.jsp" %>
<html>
<head>
    <title>My JSP 'showReport.jsp' starting page</title>
    <meta http-equiv="pragma"content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
</head>
<script>
    $(function(){
        var url = ctx+"/ReportServer?reportlet=${reportlet}";
        $("#reportFrame").attr("src",url);
        $("#content-container").css("overflow","hidden");
    });
</script>
<body style="overflow:hidden">
    <iframe id="reportFrame" name="aa" width="100%" height="100%"  src=""  style="overflow: hidden"/>
</body>
</html>