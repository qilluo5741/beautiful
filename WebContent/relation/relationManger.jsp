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
   	<title>拉单查询</title>
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
		    $("#returnForm").attr("action", "relation/selectAll.action?pageIndex="+(new_page_index+1));
		    $("#returnForm").submit();
		    return false;
		}
		$(function(){
			$("#News-Pagination").pagination(${relationPager.totalRecords}, {
		        items_per_page:${relationPager.pageSize}, // 每页显示多少条记录
		        current_page:${relationPager.pageIndex}- 1, // 当前显示第几页数据
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
   <c:if test="${empty relationPager}">
  		<c:redirect url="relation/selectAll.action"></c:redirect>
   </c:if>
  		<table cellpadding="0" cellspacing="0" align="center" width="99%" height="195" >
    		<tr>
    			<!-- <th>拉单编号</th> -->
    			<th>邀请码</th>
    			<th>拉单邀请码</th>
    			<th>拉单时间</th>
    		</tr>
    		<c:forEach items="${relationPager.list}" var="p">
    		<tr>
    			<%-- <td align="center">${fn:substring(p.relaid,0,17)}</td> --%>
    			<td align="center">${p.invideCode}</td>
    			<td align="center">${p.relationCode}</td>
    			<td align="center">${fn:substring(p.invideDate,0,19)}</td>
  			 </tr>
    		</c:forEach>
    	<tr>
	    	<td colspan="10" align="center" height="40px;">
		  		<form id="returnForm" method="post"></form>
		 		 <div align="center">
			  		<div style="" id="News-Pagination"  align="center"></div>
				</div>
			</td>
    </table>
</body>
</html>