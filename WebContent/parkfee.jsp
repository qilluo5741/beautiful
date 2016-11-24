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
	<script src="assets/js/jquery-1.8.2.min.js"></script>
	<link rel="stylesheet" href="css/01.css" type="text/css"></link>
	<link rel="stylesheet" href="css/04.css" type="text/css"></link>
<title>停车收费</title>
</head>
<body>
<table cellpadding="0" cellspacing="0" align="center" width="100%" height="195"  >
    		<tr>
    			<th>车牌号</th>
    			<th>开始时间</th>
    			<th>结束时间</th>
    			<th>费用(单位:元)</th>
    			<th>应收费用(单位:元)</th>
    			<th>收费状态</th>
    		</tr>
    		<c:forEach items="${pager}" var="p">
    		<tr>
    			<td align="center">${p.licenseplate}</td>
    			<td align="center">${p.firsttime}</td>
    			<td align="center">${p.factorytime}</td>
    			<td align="center">${p.expense}</td>
    			<td align="center">${p.receivable}</td>
    			<td align="center">
    			<c:choose>
						<c:when test="${p.chargestatus == 0}">
							<span style="color:#EEAD0E">未收费</span>
							<label for="name">选择收费状态:</label>
		                    <select class="form-control provinces">
					         <option value="1">确认收费</option>
					         <option value="2">物业车辆</option>
					         <option value="3">军用车辆</option>
					         <option value="4">其他车辆</option>
					         </select>
							<button type="button" class="confirm" id="${p.parkdeid}" style="">确认</button>
						</c:when>
						<c:when test="${p.chargestatus == 1}">
						     <span>已收费</span>
						</c:when>
						<c:when test="${p.chargestatus == 2}">
							物业车辆
						</c:when>
						<c:when test="${p.chargestatus == 3}">
						     军用车辆
						</c:when>
						<c:when test="${p.chargestatus == 4}">
						     其他车辆
						</c:when>
				</c:choose>
				</td>
  			 </tr>
  			 </c:forEach>
    </table>
    <script type="text/javascript">
    $(function(){
    	$(".confirm").click(function(){
    		var chargestatus=$(this).parent().children(".provinces").val();
    			$.ajax({
    				url:'expense/updatepense.action',
    				data:{"chargestatus":chargestatus,"parkdeid":$(this).attr("id")},
    				type:"post",
    				success:function(res){
    					if(res=="1"){
    						alert("修改成功！");
    						location.reload();
    					}else{
    						alert("修改失败！");
    						location.reload();
    					}
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