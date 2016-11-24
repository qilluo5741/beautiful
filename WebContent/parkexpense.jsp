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
    <title>停车场报表</title>
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
		    $("#returnForm").attr("action", "Park/getparkorder.action?mobile="+(${mobile})+"&pageIndex="+(new_page_index+1));
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
		 <c:redirect url="Park/getparkorder.action?mobile=${mobile}"></c:redirect>
   </c:if>
  		<table cellpadding="0" cellspacing="0" align="center" width="99%" height="195" >
    		<tr>
    			<th>停车场名字</th>
    			<th>停车场地址</th>
    			<th>价格</th>
    			<th>停车车牌号</th>
    			<th>进场时间</th>
    			<th>出场时间</th>
    			<th>费用(单位:元)</th>
    		</tr>
    		<c:forEach items="${pager.list}" var="p">
	    		<tr>
	    		    <td align="center">${p.name}</td>
	    			<td align="center">${p.address}</td>
	    			<td align="center">${p.price}</td>
	    			<td align="center">${p.licenseplate}</td>
	    			<td align="center">${p.firsttime}</td>
	    			<td align="center">${p.factorytime}</td>
	    			<td align="center">${p.receivable}</td>
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