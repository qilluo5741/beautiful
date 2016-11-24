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
	<link rel="stylesheet" href="css/04.css" type="text/css"></link>
	<script src="assets/js/jquery-1.8.2.min.js"></script>
	<title>预约订单</title>

</head>
<body>
<table cellpadding="0" cellspacing="0" align="center" width="100%" height="195"  >
    		<tr>
    			<th>姓名</th>
    			<th>手机号码</th>
    			<th>车牌号</th>
    			<th>开始停车时间</th>
    			<th>结束停车时间</th>
    			<th>接受状态</th>
    		</tr>
    	 <c:forEach items="${pager}" var="p">
    		<tr>
    			<td align="center">${p.name}</td>
    			<td align="center">${p.mobile}</td>
    			<td align="center">${p.plate_no}</td>
    			<td align="center">${p.park_start_time}</td>
    			<td align="center">${p.park_end_time}</td>
    			<td align="center">
    			<c:choose>
						<c:when test="${p.oderstate == 0}">
							<span style="color:#FF0000">已拒绝</span>
						</c:when>
						<c:when test="${p.oderstate == 1}">
						     已接受<br>
						    <table cellpadding="0" cellspacing="0" align="center" width="100%" height="30">
						     <tr>
						     <c:if test="${empty p.starttiming}">
						     	<td align="center"><button type="button" id="${p.order_num}" class="intime" name="intim">开始计时</button></td>
						     </c:if>
						      <c:if test="${not empty p.starttiming}">
						     	<td align="center">${p.starttiming }</td>
						     </c:if>
						     
						     <c:if test="${empty p.endtiming}">
						     	<td align="center"><button type="button" id="${p.order_num}" class="outtime" name="intim">结束计时</button></td>
						     </c:if>
						      <c:if test="${not empty p.endtiming}">
						     	<td align="center">${p.endtiming }</td>
						     </c:if>
						     </tr>
						     </table>
						</c:when>
				</c:choose>
				</td>
  			 </tr>
  			 </c:forEach>
    	<tr>
    </table>
    <script type="text/javascript">
    $(function(){
    	$(".intime").click(function(){
    		var order_num=$(this).attr("id");
    		var $thisParent=$(this).parent();
    			$.ajax({
    				url:'expense/getIntime.action',
    				data:{"order_num":order_num},
    				datatype:"json",
    				type:"post",
    				success:function(data){
    					var time=data.result;
    					$thisParent.html(time);
    				},error:function(){
    					location.reload();
    				}
    			});
    		   }
    		); 
    });
    </script>
    
    <script type="text/javascript">
    $(function(){
    	$(".outtime").click(function(){
    		var order_num=$(this).attr("id");
    		var $thisParent=$(this).parent();
    			$.ajax({
    				url:'expense/getOuttime.action',
    				data:{"order_num":order_num},
    				datatype:"json",
    				type:"post",
    				success:function(data){
    					var time=data.result;
    					$thisParent.html(time);
    				},error:function(){
    					location.reload();
    				}
    			});
    		   }
    		); 
    });
    </script>
</body>
</html>