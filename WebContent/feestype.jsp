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
   		<html class="no-js lt-ie9 lt-ie8 lt-ie7">
		<html class="no-js lt-ie9 lt-ie8">
		<html class="no-js lt-ie9">
	    <meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>收费模式</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
	  	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	  	<meta name="author" content="FREEHTML5.CO" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- CSS -->
        <!-- <link rel="stylesheet" href="assets/css/reset.css">
        <link rel="stylesheet" href="assets/css/supersized.css"> -->
        <!-- <link rel="stylesheet" href="css/03.css"> -->
       <!--  <link rel="stylesheet" href="assets/css/style.css"> -->
        <script src="assets/js/jquery-1.8.2.min.js"></script>
</head>
<body>   
        <div class="sty">
        <center>
        <h3>可租时间及费用设置</h3>
        <form action="" >前<input type="text" id="frertime" value="10" style="border:0px;border-bottom:#000000 1px solid;width: 30px " >分钟内免费 <br><br>
	        <input type="text" id="freemoney" value="20" style="border:0px;border-bottom:#000000 1px solid;width: 30px"> 
	        <input type="radio" name="shargetype" id="shargetype" checked="checked" value="1">元/次 
	        <input type="radio" name="shargetype" id="shargetype" value="2">元/小时<br><br>
	        <input type="checkbox" name="sss" id="clock" checked="checked">24小时内最高收费<input type="text" id="maxMoney" value="00" style="border:0px;border-bottom:#000000 1px solid;width: 30px">元<br><br>
	        <button type="button" id="save">保存</button>
        </form>
        </center>
        </div>
		<script type="text/javascript">
		    $(function(){
	    	$("#save").click(function(){
	    		if(confirm("你确定要保存修改设置吗？")){
	    			var frertime=$("#frertime").val();
	    			var freemoney=$("#freemoney").val();
	    			var shargetype=$("input[name='shargetype'][checked]").val();
	    			/* var frertime=$("#frertime").val(); */
	    			var maxMoney=$("#maxMoney").val();
	    			$.ajax({
	    				url:'expense/updateTollrecord.action',
	    				data:{"frertime":frertime,"freemoney":freemoney,"shargetype":shargetype,"frertime":frertime,"maxMoney":maxMoney},
	    				type:"post",
	    				success:function(res){
	    					if(res=="1"){
	    						alert("修改成功！");
	    					}else{
	    						alert("修改失败！");
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