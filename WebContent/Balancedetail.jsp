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
<title>交易记录</title>
</head>
<body>
<table cellpadding="0" cellspacing="0" align="center" width="100%" height="195"  >
    		<tr>
    			<th>开始时间</th>
    			<th>收益金额</th>
    			<th>余额明细状态</th>
    		</tr>
    		<c:forEach items="${pager}" var="p">
    		<tr>
    			<td align="center">${p.starttime}</td>
    			<td align="center" style="color: #FFD700">+${p.money}</td>
    			<td align="center">
    			<c:choose>
						<c:when test="${p.balancetype == 0}">
							支付宝支付
						</c:when>
						<c:when test="${p.balancetype == 1}">
						     微信支付
						</c:when>
						<c:when test="${p.balancetype == 2}">
						  余额支付
						</c:when>
						<c:when test="${p.balancetype == 3}">
						     保安收益
						</c:when>
						<c:when test="${p.balancetype == 4}">
						     退款金额
						</c:when>
						<c:when test="${p.balancetype == 5}">
						     推荐保安
						</c:when>
				</c:choose>
				</td>
  			 </tr>
  			 </c:forEach>
    </table>

</body>
</html>