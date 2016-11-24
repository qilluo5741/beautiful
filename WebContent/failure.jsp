<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>BATP停车分享领红包</title>
	<meta name="format-detection" content="telephone=no"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  <link rel="stylesheet" type="text/css" href="files/cmstop-error.css" media="all">
</head>
<body class="body-bg">
<div class="main">
    <p class="title">非常抱歉，该链接已经失效！<br/>小编告诉你:一笔订单就可以领取一次红包喔！</p>
    <a href="javascript:history.go(-1)" class="btn">返回上一页</a>
</div>
  </body>
</html>
