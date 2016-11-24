<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>BATP停车分享领红包</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
			body{
				margin: 0px;
				width: 0px;
			}
			#main{
				width: auto;
				height: auto;
			}
			#qu{
				width: 800px;
				height: 130px;
				border: none;
				background: white;
				border-radius: 17px;
				position: absolute;
				top: 1200px;
				left: 149px;
				background: url(img/button2.png);
			
			}
			#tag{
				background: url(img/tag.png);
				width: 970px;
				height: 280px;
				position: absolute;
				top: 860px;
				left: 60px;
			}
			#red{
				color: #f94646;
				width: 285px;
				height: 150px;
				border: none;
				font-family:"黑体";
				font-size: 120px;
				position: absolute;
				top: 90px;
				left:90px;
				text-align: left;
				font-weight: bold;
			
			}
			#mobile,#time{
				color: #898989;
				width: 550px;
				height: 60px;
				border: none;
				font-family: "微软雅黑";
				font-size: 35px;
			}
			#time{
				position: absolute;
				top: 200px;
				left: 460px;
			}
			#mobile{
				position: absolute;
				top: 140px;
				left: 460px;
			}
		</style>
	<script type="text/javascript">
	function checkPhone(){
		
	    var phone = document.getElementById('qu').value;
	    if(phone!=null){ 
	    	window.location.href='http://www.sharebo.cn/share.html';
	    }
	}
	</script>
  </head>
  
  <body>
  <div accept="application/msexcel" align="center" id="main">
		<img src="img/bg2.jpg" id="bg"/>
		<form action="#" method="post" style="alignment-baseline:auto;width: auto;height: auto;">
			<div accept="application/msexcel" class="ey" align="center">
				<div id="tag" ccept="application/msexcel">
				<input type="text" id="red"  value="0" readonly="readonly"/>
				<input type="text" id="mobile" value="红包已经领取完了" readonly="readonly" />
				</div>
				<button type="button" id="qu" onclick="checkPhone()"></button>
			</div>
		</form>
	</div>
  </body>
</html>