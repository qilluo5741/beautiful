<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>BATP停车分享领红包</title>
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript">
	function checkPhone(){ 
	    var phone = document.getElementById('mobile').value;
	    if(!(/^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(phone))){ 
	        alert("手机号码有误，请重填");  
	        return false; 
	    }else{
	    	document.getElementById("myForm").submit();
	    }
	}
	</script>
	<style type="text/css">
			body{
				margin: 0px;
				width: 0px;
			}
			#main{
				width: auto;
				height: auto;
			}
			#mobile{
				width: 800px;
				height: 130px;
				border-radius: 17px;
				border: none;
				border: solid #CCCCCC;
				position: absolute;
				top: 880px;
				left: 149px;
				font-family: "微软雅黑";
				font-size: 60px;
				color: #CCCCCC;
				text-align: center;
			}
			#qu{
				width: 800px;
				height: 130px;
				border: none;
				background: white;
				border-radius: 17px;
				position: absolute;
				top: 1050px;
				left: 149px;
				background: url(img/button.png);
			}
		</style>
</head>
<body>
	<div accept="application/msexcel" align="center" id="main">
		<img src="img/bg.jpg" id="bg"/>
		<form action="share/insertshare.action" method="get" style="alignment-baseline:auto;width: auto;height: auto;" id="myForm">
			<div accept="application/msexcel" class="ey" align="center">
				<input type="hidden" name="mobile" value="<%=request.getParameter("mobile")%>">
				<input type="hidden" name="order_num" value="<%=request.getParameter("order_num")%>">
				<input type="text" name="sharemobile" placeholder="请输入手机号" id="mobile"/>
				<button type="button" id="qu" onclick="checkPhone()"></button>
			</div>
		</form>
	</div>
</body>
</html>