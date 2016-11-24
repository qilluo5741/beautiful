<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/01.css" type="text/css"></link>
<title>邀请记录</title>
</head>
<body>
  		<table cellpadding="0" cellspacing="0" align="center" width="100%" height="195"  >
  		<%-- <caption style="">邀请记录表</caption> --%>
    		<tr>
    			<th>邀请的手机号码</th>
    			<th>邀请时间</th>
    			<th>是否注册</th>
    		</tr>
    		<c:forEach items="${sks}" var="p">
    		<tr>
    			<td align="center">${p.rtartmoble}</td>
    			<td align="center">${p.startDate}</td>
    			<td align="center">
    			<c:choose>
						<c:when test="${p.state == 0}">
							<span style="color:#FF6347">未注册</span>
						</c:when>
						<c:when test="${p.state == 1}">
						     已注册
						</c:when>
				</c:choose>
				</td>
  			 </tr>
  			 </c:forEach>
    </table>
</body>
</html>