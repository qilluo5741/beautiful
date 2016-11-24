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
		<title>BATP停车</title>
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
        <!--<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'> -->
        <link rel="stylesheet" href="assets/css/reset.css">
        <link rel="stylesheet" href="assets/css/supersized.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!-- Javascript -->
        <script src="assets/js/jquery-1.8.2.min.js"></script>
        <script src="assets/js/supersized.3.2.7.min.js"></script>
        <script src="assets/js/supersized-init.js"></script>
        <script src="assets/js/scripts.js"></script>
    </head>
<body>
       <div class="page-container">
             <h1>BATP停车后台管理系统</h1>
             <form action="" method="post">
             <input type="text" id="userName" name="username" class="username" placeholder="用户名/手机号/账号">
             <input type="password" id="userPwd" name="password" class="password" placeholder="Password"><br/><br/>
             <font id="msg_"><strong id='msg'></strong></font>
             <button type="button" id='login'>B&nbsp;A&nbsp;T&nbsp;P&nbsp;登&nbsp;录</button>
             </form>
       </div>
        <div align="center" style="margin-top:140px;">Copyright &copy; 2015-2016 上海享泊信息科技有限公司</div>
        <script type="text/javascript">
		$(function(){
			//登陆
			$("#login").click(function(){
				//得到用户名账号密码
				var $name=$("#userName");
				var $pwd=$("#userPwd");
				if($name.val()==""){
					$name.css("borderColor","#FF6347");
					for(var i=0;i<=1;i++){
						$name.hide(50);
						$name.show(100);
					}
				}
				else if($pwd.val()==""){
					$pwd.css("borderColor","#FF6347");
					for(var i=0;i<=1;i++){
						$pwd.hide(50);
						$pwd.show(100);
					}
				}
				else{
					//登陆
					$.ajax({
						url:'userinfo/login.action',
						data:{"userName":$name.val(),"userPwd":$pwd.val()},
						type:"post",
						success:function(res){
							if(res=="0"){
								$("#msg").html("登陆失败！请检查用户名密码是否正确！");
								$("#msg_").attr("color","#FF6347");
							}else if(res=="1"){
								$("#msg").html("登陆成功！");
								$("#msg_").attr("color","#4EEE94");
								$pwd.val("");
								window.location.href='index.html';
							}else if(res=="2"){
								$("#msg").html("该账户还未通过审核!");
							}else if(res=="4"){
								$("#msg").html("后台正在维护中。。请稍候再试！");
							}else{
								$("#msg").html("后台正在维护中。。请稍候再试！");
							}
						}
					});
				}
			});
			$("#userName").keyup(function(){
				  $("#userName").css("borderColor","#c0c0c0");
				  $("#userPwd").css("borderColor","#c0c0c0");
				  $("#msg").html("");
			})
			$("#userPwd").keyup(function(){
				 $("#userName").css("borderColor","#c0c0c0");
				  $("#userPwd").css("borderColor","#c0c0c0");
				  $("#msg").html("");
			})
		});	
	</script>
    </body>
</html>