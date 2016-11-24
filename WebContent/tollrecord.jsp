<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	 <title>关联收费员</title>
	 <link rel="stylesheet" href="css/01.css" type="text/css"></link>
     <script src="assets/js/jquery-1.8.2.min.js"></script>
     <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
     <link rel="stylesheet" href="assets/css/reset.css">
     <link rel="stylesheet" href="assets/css/supersized.css">
     <link rel="stylesheet" href="assets/css/style.css">
</head>
<body>
	<center>
     	<form method="post">
	           	  <span style="font-size: 16px;color: #4F94CD">收&nbsp;费&nbsp;员&nbsp;姓&nbsp;名:</span><input type="text" id="name" name="name" class="name" placeholder="请输入收费员姓名" title="请输入收费员姓名"/><br/><br/>
	          	  <span style="font-size: 16px;color: #4F94CD">收费员手机号码:</span><input type="text" id="tollRrecphone" name="tollRrecphone" class="tollRrecphone" placeholder="请输入收费员手机号码"/><br/><br/>
	             <font id="msg_"><strong id='msg'></strong></font>
	           	  <span style="font-size: 16px;color: #4F94CD">别&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名:</span><input type="text" id="tollRremname" name="tollRremname" class="tollRremname" placeholder="请输入别名"/><br><br>
	             <button type="button" id="ass">关联</button>
	      </form>
	      </center>
 		<script type="text/javascript">
		$(function(){
			$("#ass").click(function(){
				//得到用户名账号密码
				var name=$("#name").val();
				var tollRrecphone=$("#tollRrecphone").val();
				var tollRremname=$("#tollRremname").val();
				if(name==""){
					//验证姓名是否为空
					$("#name").css("borderColor","red");
					$("#name").hide(100);
					$("#name").show(100);
				
			}else if(tollRrecphone==""){
				//验证收费员手机号码是否为空
					$("#tollRrecphone").css("borderColor","red");
					$("#tollRrecphone").hide(100);
					$("#tollRrecphone").show(100);
			}
			else if(tollRremname==""){
				//验证别名是否为空
					$("#tollRremname").css("borderColor","red");
					$("#tollRremname").hide(100);
					$("#tollRremname").show(100);
			}
			else{
				$("#name").val("");
				$("#tollRrecphone").val("");
				$("#tollRremname").val("");
				$.ajax({
					url:'expense/addtoll.action',
					data:{"name":name,"tollRrecphone":tollRrecphone,"tollRremname":tollRremname},
					type:"post",
					success:function(res){
						if(res==true){
							alert("关联成功！");
						}else if(res==false){
							alert("关联失败！");
						}
					} 
				});
			}
		});
		$("input").keyup(function(){
			//返回颜色
			$("#name").css("borderColor","#F0FFFF");
			$("#tollRrecphone").css("borderColor","#F0FFFF");
			$("#tollRremname").css("borderColor","#F0FFFF");
			$("#msg").html("<Br/>");
		})	
	})
</script>
</body>
</html>