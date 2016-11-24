<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en" class="no-js">
  	<head>
	    <base href="<%=basePath%>">
	    <html class="no-js lt-ie9 lt-ie8 lt-ie7">
		<html class="no-js lt-ie9 lt-ie8">
		<html class="no-js lt-ie9">
	    <meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>享泊管理端</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
	  	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	  	<meta name="author" content="FREEHTML5.CO" />
	  	<!-- Facebook and Twitter integration -->
		<meta property="og:title" content=""/>
		<meta property="og:image" content=""/>
		<meta property="og:url" content=""/>
		<meta property="og:site_name" content=""/>
		<meta property="og:description" content=""/>
		<meta name="twitter:title" content="" />
		<meta name="twitter:image" content="" />
		<meta name="twitter:url" content="" />
		<meta name="twitter:card" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- CSS -->
        <link rel="stylesheet" href="assets/css/reset.css">
        <link rel="stylesheet" href="assets/css/supersized.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <!-- <script src="assets/js/jquery-1.8.2.min.js"></script> -->
        <script src="layer/js/jquery-2.1.1.min.js"></script>
    </head>
<body>
        <div class="page-container">
	            <h1>享泊管理端登录</h1>
	            <form action="" method="post">
	             <input type="text" id="mobile" name="username" class="username" placeholder="请输入手机号码">
	             <input type="password" id="password" name="password" class="password" placeholder="请输入密码"><br/><br/>
	             <font id="msg_"><strong id='msg'></strong></font>
	             <button type="button" id='login'>B&nbsp;A&nbsp;T&nbsp;P&nbsp;登&nbsp;录</button>
	            </form>
        </div>
        <div align="center" style="margin-top:140px;">Copyright &copy; 2015-2016 上海享泊信息科技有限公司</div>
        <!-- Javascript -->
        <script src="assets/js/jquery-1.8.2.min.js"></script>
        <script src="assets/js/supersized.3.2.7.min.js"></script>
        <script src="assets/js/supersized-init.js"></script>
        <script src="assets/js/scripts.js"></script>
        <script type="text/javascript">
        	$(function(){
        		$("#login").click(function(){
        			//得到用户名密码
        			var mobile=$("#mobile").val();
        			var password=$("#password").val();
        			if(mobile==""){
        					//验证登录
        					$("#mobile").css("borderColor","red");
        					$("#mobile").hide(100);
        					$("#mobile").show(100);
        				
        			}else if(password==""){
        				//验证登录
        					$("#password").css("borderColor","red");
        					$("#password").hide(100);
        					$("#password").show(100);
        			}
        			else{
        				//执行登录操作前 清空密码框
        				$("#mobile").val("");
        				$("#password").val("");
        				$.ajax({
							url:'expense/user/login.action',
							data:{"mobile":mobile,"password":password},
							type:"post",
							success:function(res){
								if(res=="0"){
									//跳转首页
									window.location.href="managers.jsp";
								}else if(res=="1"){
									//跳转首页
									window.location.href="managers.jsp";
								}else if(res=="2"){
									//跳转首页
									window.location.href="managers.jsp";
								}
								else if(res=="3"){
									//跳转首页
									window.location.href="managers.jsp";
								}
								else if(res=="4"){
									//跳转首页
									window.location.href="managers.jsp";
								}
								else{
									$("#msg").html("用户名/密码 错误！");
								}
							} 
						});
        			}
        		});
        		$("input").keyup(function(){
        			//返回颜色
        			$("#password").css("borderColor","#F0FFFF");
        			$("#mobile").css("borderColor","#F0FFFF");
        			$("#msg").html("<Br/>");
        		})	
        	})
        </script>
    </body>
</html>