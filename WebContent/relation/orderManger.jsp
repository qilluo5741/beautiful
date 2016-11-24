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
   	<title>订单信息查询</title>
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
		    $("#returnForm").attr("action","orderinfo/selectAll.action?pageIndex="+(new_page_index+1));
		    $("#returnForm").submit();
		    return false;
		}
		$(function(){
			$("#News-Pagination").pagination(${orderpager.totalRecords}, {
		        items_per_page:${orderpager.pageSize}, // 每页显示多少条记录
		        current_page:${orderpager.pageIndex}- 1, // 当前显示第几页数据
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
   <c:if test="${empty orderpager}">
  		<c:redirect url="orderinfo/selectAll.action"></c:redirect>
   </c:if>
  		<table cellpadding="0" cellspacing="0" align="center" width="99%" height="195" >
    		<tr>
    			<!-- <th>开始时间</th> -->
    			<!-- <th>结束时间</th> -->
    			<th>交易时间</th>
    			<!-- <th>下订单时间</th>-->
    			<th>支付时间</th>
    			<th>支付类型</th>
    			<th>订单号</th>
    			<th>支付金额</th>
    			<th>车牌号</th>
    			<th>预约状态</th>
    			<th>感谢费</th>
    		</tr>
    		<c:forEach items="${orderpager.list}" var="p">
    		<tr>
    			<%-- <td align="center">${fn:substring(p.park_start_time,0,19)}</td> --%>
    			<%-- <td align="center">${fn:substring(p.park_end_time,0,19)}</td> --%>
    			<td align="center">${fn:substring(p.exchange_time,0,16)}</td>
    			<%-- <td align="center">${fn:substring(p.order_request_time,0,16)}</td> --%>
    			<td align="center">${fn:substring(p.pay_time,0,16)}</td>
    			<td align="center">
    			<c:choose>
						<c:when test="${p.pay_type == 0}">
						支付宝
						</c:when>
						<c:when test="${p.pay_type == 1}">
						微信
						</c:when>
						<c:when test="${p.pay_type == 2}">
						银行卡
						</c:when>
						<c:when test="${p.pay_type == 3}">
						余额
						</c:when>
				</c:choose>
    			</td>
    			<td align="center">${fn:substring(p.order_num,0,14)}</td>
    			<td align="center">${p.money}</td>
    			<td align="center">${p.plate_no}</td>
    			<td align="center">
    			<c:choose>
						<c:when test="${p.oderstate == 0}">
						保安接受
						</c:when>
						<c:when test="${p.oderstate == 1}">
						拒绝状态
						</c:when>
						<c:when test="${p.oderstate == 2}">
						自动退款
						</c:when>
				</c:choose>
    			</td>
    			<td align="center">${fn:substring(p.thankcharge,0,4)}</td>
  			 </tr>
    		</c:forEach>
    	<tr>
	    	<td colspan="13" align="center" height="40px;">
		  		<form id="returnForm" method="post"></form>
		 		 <div>
			  		<div style="" id="News-Pagination"  align="center"></div>
				</div>
			</td>
    </table>
</body>
</html>