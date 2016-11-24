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
   	<title>提现查询</title>
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
		    $("#returnForm").attr("action", "drawcash/selectAll.action?pageIndex="+(new_page_index+1));
		    $("#returnForm").submit();
		    return false;
		}
		$(function(){
			$("#News-Pagination").pagination(${drawcashPager.totalRecords},{
		        items_per_page:${drawcashPager.pageSize}, // 每页显示多少条记录
		        current_page:${drawcashPager.pageIndex}- 1,// 当前显示第几页数据
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
   <c:if test="${empty drawcashPager}">
  		<c:redirect url="drawcash/selectAll.action"></c:redirect>
   </c:if>
  		<table cellpadding="0" cellspacing="0" align="center" style="width:99%;height:auto;">
    		<tr>
    			<!-- <th>提现编号</th> -->
    			<th>账户名称</th>
    			<th>账户号码</th>
    			<th>提现金额</th>
    			<th>申请时间</th>
    			<th>手机号码</th>
    			<th>提现时间</th>
    			<th>提现状态</th>
    		</tr>
    		<c:forEach items="${drawcashPager.list}" var="p">
    		<tr>
    			<%-- <td align="center">${fn:substring(p.drawid,0,36)}</td> --%>
    			<td align="center">${p.bank.accountname}</td>
    			<td align="center">${p.bank.accountnumber}</td>
    			<td align="center">${p.money}</td>
    			<td align="center">${fn:substring(p.endtime,0,19)}</td>
    			<td align="center">${p.user.mobile}</td>
    			<td align="center">${fn:substring(p.finaltime,0,19)}</td>
    			<td align="center">
    			<c:choose>
						<c:when test="${p.state == 0}">
							已处理
						</c:when>
						<c:when test="${p.state == 1}">
							<a href="drawcash/updateState.action?drawid=${p.drawid}">未处理</a>
						</c:when>
				</c:choose>
    			</td>
  			 </tr>
    		</c:forEach>
    	<tr>
	    	<td colspan="9" align="left" height="40px;">
		  		<form id="returnForm" method="post"></form>
		 		 <div>
			  		<div style="" id="News-Pagination"  align="center"></div>
				</div>
			</td>
    </table>
</body>
</html>