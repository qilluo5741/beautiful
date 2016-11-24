<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
    <title>交易记录管理</title>
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
		    $("#returnForm").attr("action", "detail/selectAll.action?pageIndex="+(new_page_index+1));
		    $("#returnForm").submit();
		    return false;
		}
		$(function(){
			$("#News-Pagination").pagination(${pager.totalRecords}, {
		        items_per_page:${pager.pageSize}, // 每页显示多少条记录
		        current_page:${pager.pageIndex}- 1, // 当前显示第几页数据
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
 <c:if test="${empty pager}">
		 <c:redirect url="detail/selectAll.action"></c:redirect>
   </c:if>
  		<table cellpadding="0" cellspacing="0" align="center" width="99%" height="195" >
    		<tr>
    			<!-- <th>交易编号</th> -->
    			<!-- <th>用户编号</th> -->
    			<th>交易时间</th>
    			<th>交易类型</th>
    			<th>交易金额</th>
    		</tr>
    		<c:forEach items="${pager.list}" var="p">
    		<tr>
    			<%-- <td align="center">${fn:substring(p.badeid,0,17)}</td> --%>
    			<%-- <td align="center">${p.userid}</td> --%>
    			<td align="center">${fn:substring(p.starttime,0,19)}</td>
    			<td align="center">
    			<c:choose>
						<c:when test="${p.balancetype == 0}">
							支付宝交易
						</c:when>
						<c:when test="${p.balancetype == 1}">
						     微信支付交易
						</c:when>
						<c:when test="${p.balancetype == 2}">
						     余额支付交易
						</c:when>
						<c:when test="${p.balancetype == 3}">
						     保安收益
						</c:when>
						<c:when test="${p.balancetype == 4}">
						     退款金额
						</c:when>
						<c:when test="${p.balancetype == 5}">
						     推荐好友
						</c:when>
						<c:when test="${p.balancetype == 6}">
						     推荐保安
						</c:when>
				</c:choose>
				</td>
    			<td align="center">${p.money}</td>
  			 </tr>
    		</c:forEach>
    	<tr>
	    	<td colspan="10" align="center" height="40px;">
		  		<form id="returnForm" method="post"></form>
		 		 <div>
			  		<div style="" id="News-Pagination"  align="center"></div>
				</div>
			</td>
		</tr>
    </table>
</body>
</html>