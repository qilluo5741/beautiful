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
	 <script src="assets/js/jquery-1.8.2.min.js"></script>
<title>关联记录</title>
</head>
<body>
  		<table cellpadding="0" cellspacing="0" align="center" width="100%" height="95%">
    		<tr>
    			<th>别名</th>
    			<th>收费员手机号码</th>
    			<th>解除关联</th>
    		</tr>
    		<c:forEach items="${pager}" var="p">
    		<tr>
    		   <%--  <td align="center" >${p.tollRid}</td> --%>
    			<td align="center" >${p.tollRremname}</td>
    			<td align="center" >${p.tollRrecphone}</td>
    			<td align="center"><button type="button" class="delete" id="${p.tollRrecphone}">解除关联</button></td>
  			 </tr>
  			 </c:forEach>
    </table>
    <script type="text/javascript">
    $(function(){
    	$(".delete").click(function(){
    		if(confirm("你确定要解除关联吗？")){
    			$.ajax({
    				url:'expense/deletetoll.action',
    				data:{"tollRrecphone":$(this).attr("id")},
    				type:"post",
    				success:function(res){
    					if(res=="1"){
    						alert("解除成功！");
    						location.reload();
    					}else{
    						alert("解除失败！");
    						location.reload();
    					}
    				},error:function(){
    					location.reload();
    				}
    			});
    		   }
    		});
    });
    </script>
</body>
</html>