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
		<title>状态机上下线</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="description" content="Free HTML5 Template by FREEHTML5.CO" />
	  	<meta name="keywords" content="free html5, free template, free bootstrap, html5, css3, mobile first, responsive" />
	  	<meta name="author" content="FREEHTML5.CO" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- CSS -->
        <link rel="stylesheet" href="assets/css/reset.css">
        <link rel="stylesheet" href="assets/css/supersized.css">
        <link rel="stylesheet" href="css/03.css">
        <link rel="stylesheet" href="assets/css/style.css">
        <script src="assets/js/jquery-1.8.2.min.js"></script>
</head>
<body>  
       <div class="go">
       		<a href="javascript:void(0)" id="up" style="text-decoration:none;"><img alt="上线" src="image/01.png" style="width:150px;"> </a>
      </div>
       <div class="out">
       		<a href="javascript:void(0)" id="down" style="text-decoration:none;"><img alt="下线" src="image/02.png" style="width:150px;"></a>
       </div>
       <script type="text/javascript">
   	 $(function(){
    	$("#up").click(function(){
    			$.ajax({
    				url:'expense/updatestate.action',
    				data:{"state":"Y"},
    				type:"post",
    				success:function(res){
    					if(res=="1"){
    						alert("成功上线,请继续修改状态机状态!");
    						window.location.href="statusmachine.jsp";
    					}else{
    						alert("上线失败!");
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
    <script type="text/javascript">
    $(function(){
    	$("#down").click(function(){
  			$.ajax({
  				url:'expense/updatestate.action',
  				data:{"state":"N"},
  				type:"post",
  				success:function(res){
  					if(res=="1"){
  						alert("成功下线！");
  						location.reload();
  					}else{
  						alert("下线失败！");
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