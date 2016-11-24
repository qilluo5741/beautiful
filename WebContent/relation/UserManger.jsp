<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   	<title>用户权限查询</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/01.css" type="text/css"></link>
	<link rel="stylesheet" href="css/index.css" type="text/css" media="screen" />
	<script type="text/javascript" src="js/Myjquery.js"></script>
	<link rel="stylesheet" href="css/pagination.css" type="text/css"></link>
	<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="js/jquery.pagination.js"></script>
	<script type="text/javascript">
		//点击分页按钮以后触发的动作
		function handlePaginationClick(new_page_index, pagination_container) {
		    $("#returnForm").attr("action","relation/selectAlls.action?pageIndex="+(new_page_index+1));
		    $("#returnForm").submit();
		    return false;
		}
		$(function(){
			$("#News-Pagination").pagination(${UserPager.totalRecords}, {
		        items_per_page:${UserPager.pageSize}, // 每页显示多少条记录
		        current_page:${UserPager.pageIndex}- 1, // 当前显示第几页数据
		        num_display_entries:2, // 分页显示的条目数
		        next_text:"下一页",
		        prev_text:"上一页",
		        num_edge_entries:3, // 连接分页主体，显示的条目数
		        callback:handlePaginationClick
			});
		});
	</script>
</head>
<body>
   <c:if test="${empty UserPager}">
  		<c:redirect url="relation/selectAlls.action"></c:redirect>
   </c:if>
  		<table cellpadding="0" cellspacing="0" align="center" width="99%" height="195" >
    		<tr>
    			<th>用户手机</th>
    			<th>用户姓名</th>
    			<th>用户性别</th>
    			<th>注册时间</th>
    			<th>用户状态</th>
    		</tr>
    		<c:forEach items="${UserPager.list}" var="p">
    		<tr>
    			<td align="center">${p.mobile}</td>
    			<td align="center">${p.name}</td>
    			<td align="center">
    			<a href="relation/updateSex.action?id=${p.id}">${p.sex}</a>
    			</td>
    			<td align="center">${fn:substring(p.date_created,0,19)}</td>
    			<td align="center">
    			<c:choose>
						<c:when test="${p.is_admin == 0}">
							<a href="relation/updateState.action?id=${p.id}">普通用户</a>
						</c:when>
						<c:when test="${p.is_admin == 1}">
							<a href="relation/updateStates.action?id=${p.id}">管理员</a>
						</c:when>
						<c:when test="${p.is_admin == 2}">
							保安
						</c:when>
						<c:when test="${p.is_admin == 3}">
							超级管理员
						</c:when>
				</c:choose></td>
  			 </tr>
    		</c:forEach>
    	<tr>
	    	<td colspan="6" align="center" height="40px;">
		  		<form id="returnForm" method="post"></form>
		 		 <div>
			  		<div style="" id="News-Pagination"  align="center"></div>
				</div>
			</td>
    </table>
</body>
</html>