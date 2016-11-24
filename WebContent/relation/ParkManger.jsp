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
   	<title>停车场查询</title>
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
		    $("#returnForm").attr("action", "Park/selectAlls.action?pageIndex="+(new_page_index+1));
		    $("#returnForm").submit();
		    return false;
		}
		$(function(){
			$("#News-Pagination").pagination(${ParkPager.totalRecords}, {
		        items_per_page:${ParkPager.pageSize}, // 每页显示多少条记录
		        current_page:${ParkPager.pageIndex}- 1, // 当前显示第几页数据
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
   <c:if test="${empty ParkPager}">
  		<c:redirect url="Park/selectAlls.action"></c:redirect>
   </c:if>
  		<table cellpadding="0" cellspacing="0" align="left" width="99%" height="195" >
    		<tr>
    			<th width="6%">停车场名字</th>
    			<!-- <th>停车场地址</th> -->
    			<!-- <th>停车场类别</th> -->
    			<!-- <th>收费模式</th> -->
    			<th width="5%">停车位类型</th>
    			<th width="5%">可预约状态</th>
    			<th width="6%">入口地址</th>
    			<th width="5%">是否合作</th>
    			<!-- <th>停车位价格</th> -->
    		</tr>
    		<c:forEach items="${ParkPager.list}" var="p">
    		<tr>
    			<td align="center" width="6%">${p.name}</td>
    			<%-- <td align="center">${p.address}</td> --%>
    			<%-- <td align="center">${p.city}</td> --%>
    			<%-- <td align="center">${p.cost}</td> --%>
    			<td align="center"  width="5%">${p.type}</td>
    			<td align="center"  width="5%">
    			<c:choose>
						<c:when test="${p.status == 0}">
							<input style="border-radius:50%;width:30px;height: 30px;background-color:#1d953f;border-style: none;" disabled="disabled">
						</c:when>
						<c:when test="${p.status == 1}">
							<input style="border-radius:50%;width:30px;height: 30px;background-color:#dea32c;border-style: none;" disabled="disabled">
						</c:when>
						<c:when test="${p.status == 2}">
							<input style="border-radius:50%;width:30px;height: 30px;background-color:#ef4136;border-style: none;" disabled="disabled">
						</c:when>
				</c:choose>
    			</td>
    			<td align="center"  width="6%">${fn:substring(p.entry_address,0,16)}</td>
    			<td align="center"  width="5%">${p.is_cooperate}</td>
    			<%-- <td align="center">${p.price}</td> --%>
  			 </tr>
    		</c:forEach>
    	<tr>
	    	<td colspan="5" align="center" height="40px;">
		  		<form id="returnForm" method="post"></form>
		 		 <div>
			  		<div style="" id="News-Pagination"  align="center"></div>
				</div>
			</td>
    </table>
</body>
</html>