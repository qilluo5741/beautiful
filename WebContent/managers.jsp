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
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>BATP管理端</title>
    <!-- Favicon -->
    <link rel="shortcut icon" href="assets/img/favicon.ico" type="image/x-icon">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.min.css">
    <!-- Fonts from Font Awsome -->
    <link rel="stylesheet" href="assets/css/font-awesome.min.css">
    <!-- CSS Animate -->
    <link rel="stylesheet" href="assets/css/animate.css">
    <!-- Custom styles for this theme -->
    <link rel="stylesheet" href="assets/css/main.css">
    <!-- Vector Map  -->
    <link rel="stylesheet" href="assets/plugins/jvectormap/css/jquery-jvectormap-1.2.2.css">
    <!-- ToDos  -->
    <link rel="stylesheet" href="assets/plugins/todo/css/todos.css">
    <!-- Morris  -->
    <link rel="stylesheet" href="assets/plugins/morris/css/morris.css">
    <!-- Fonts -->
    <script src="assets/js/modernizr-2.6.2.min.js"></script>
    <script src="assets/js/html5shiv.js"></script>
    <script src="assets/js/respond.min.js"></script>
    <!-- ToDo List  -->
    <script src="assets/plugins/todo/js/todos.js"></script>
    <script src="layer/js/jquery-2.1.1.min.js"></script>
    <script src="layer/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="layer/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="layer/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="layer/js/hplus.js?v=2.2.0"></script>
    <script src="layer/js/plugins/pace/pace.min.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
    <script>
	    $(document).ready(function() {
	        app.timer();
	        app.map();
	        app.weather();
	        app.morrisPie();
	    });
    </script>
</head>
<body>
	<section id="container">
        <header id="header">
            <!--logo start-->
            <div class="brand">
                <a href="charts-c3.html"  target="menuFrame" target="menuFrame"class="logo" >
                    <span>BATP</span>管理</a>
            </div>
            <!--logo end-->
            <div class="toggle-navigation toggle-left">
                <button type="button" class="btn btn-default" id="toggle-left" data-toggle="tooltip" data-placement="right" title="BATP 管理">
                    <i class="fa fa-bars"></i>
                </button>
            </div>
            <div class="user-nav">
                <ul>
                    <li class="dropdown messages">
                       <i class="fa fa-envelope"></i><span class="label label-warning"></span>
                    </li>
                    <li>
                    	<i class="fa fa-bell"></i><span class="label label-primary"></span>
                    </li>
                    <li class="dropdown settings">
                        <i></i><span>${mobile}</span>
                    </li>
                    <li><i></i><textarea id="isadmin" style="display: none;">${isadmin}</textarea></li>
                    <li>
                    	<i class="fa fa-sign-out"></i><a href="Exit.jsp">&nbsp;&nbsp;退出</a>
                    </li>
                </ul>
            </div>
        </header>
        <!--sidebar left start-->
        <aside class="sidebar">
            <div id="leftside-navigation" class="nano">
                <ul class="nano-content">
                	<li class="active">
                        <a href="charts-c3.html" target="menuFrame"><i class="fa fa-dashboard"></i><span>我的主页</span></a>
                    </li>
                    <li class="sub-menu">
                        <a href="managers.jsp#" id="state"><i class="fa fa-cogs"></i><span>状态机管理</span><i class="arrow fa fa-angle-right pull-right"></i></a>
                        <ul>
                            <li><a href="statemachine.jsp" target="menuFrame" id="state1">状态机上下线</a></li>
                        </ul>
                    </li>
                    <li class="sub-menu">
                        <a href="managers.jsp#" id="in"><i class="fa fa-table"></i><span>邀请好友</span><i class="arrow fa fa-angle-right pull-right"></i></a>
                        <ul>
                           <li><a href="invite.jsp" target="menuFrame" id="in2">邀请好友</a></li>
                            <li><a href="detail/user/invite.action?mobile=${mobile}" target="menuFrame" id="in1">邀请记录</a></li>
                        </ul>
                    </li>
                    <li class="sub-menu">
                        <a href="managers.jsp#" id="or"><i class="fa fa fa-tasks"></i><span>预约订单</span><i class="arrow fa fa-angle-right pull-right"></i></a>
                        <ul>
                            <li><a href="detail/user/orderRecord.action?mobile=${mobile}" target="menuFrame" id="or1">预约订单</a></li>
                        </ul>
                    </li>
                    <li class="sub-menu">
                        <a href="managers.jsp#" id="pa"><i class="fa fa-envelope"></i><span>停车收费</span><i class="arrow fa fa-angle-right pull-right"></i></a>
                        <ul>
                           <li><a href="detail/parkfee.action?mobile=${mobile}" target="menuFrame" id="pa1">停车收费</a></li>
                        </ul>
                    </li>
                    <li class="sub-menu">
                        <a href="managers.jsp#" id="pu"><i class="fa fa-map-marker"></i><span>我的钱包</span><i class="arrow fa fa-angle-right pull-right"></i></a>
                        <ul>
                           <li><a href="detail/selectPurse.action?mobil=${mobile}" target="menuFrame" id="pu1">余额明细</a></li>
                            <li><a href="javascript:void(0);" target="menuFrame" id="pu2">余额提现</a></li>
                            <li><a href="detail/balance.action?mobile=${mobile}" target="menuFrame" id="pu3">交易记录</a></li>
                        </ul>
                    </li>
                    <li class="sub-menu">
                        <a href="managers.jsp#" id="toll"><i class="fa fa-file"></i><span>收费员管理</span><i class="arrow fa fa-angle-right pull-right"></i></a>
                        <ul>
                            <li><a href="tollrecord.jsp" target="menuFrame" id="toll3">关联收费员</a></li>
                            <li><a href="detail/tollrecord.action" target="menuFrame" id="toll1">创建记录</a></li>
                            <li><a href="feestype.jsp" target="menuFrame" id="toll2">收费模式</a></li>
                        </ul>
                    </li>
                    <li class="sub-menu">
                        <a href="managers.jsp#" id="table"><i class="fa fa-bar-chart-o"></i><span>统计报表</span><i class="arrow fa fa-angle-right pull-right"></i></a>
                        <ul>
                           <li><a href="Park/getparkorder.action?mobile=${mobile}" target="menuFrame" id="table1">停车场报表</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </aside>
        <!--main content start-->
        <section class="main-content-wrapper">
            <section id="main-content">
                <!--tiles start-->
                <div class="row">
                    <iframe id="menuFrame" name="menuFrame" src="charts-c3.html" style="overflow:visible;border:none;" scrolling="auto" width="99%" height="1420px;"></iframe>
                </div>
            </section>
        </section>
    </section>
    <!--Global JS-->
    <script src="assets/js/jquery-1.10.2.min.js"></script>
    <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/plugins/waypoints/waypoints.min.js"></script>
    <script src="assets/js/application.js"></script>
    <!--Page Level JS-->
    <script src="assets/plugins/countTo/jquery.countTo.js"></script>
    <script src="assets/plugins/weather/js/skycons.js"></script>
    <!-- FlotCharts  -->
    <script src="assets/plugins/flot/js/jquery.flot.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.resize.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.canvas.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.image.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.categories.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.crosshair.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.errorbars.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.fillbetween.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.navigate.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.pie.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.selection.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.stack.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.symbol.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.threshold.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.colorhelpers.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.time.min.js"></script>
    <script src="assets/plugins/flot/js/jquery.flot.example.js"></script>
    <!-- Morris  -->
    <script src="assets/plugins/morris/js/morris.min.js"></script>
    <script src="assets/plugins/morris/js/raphael.2.1.0.min.js"></script>
    <!-- Vector Map  -->
    <script src="assets/plugins/jvectormap/js/jquery-jvectormap-1.2.2.min.js"></script>
    <script src="assets/plugins/jvectormap/js/jquery-jvectormap-world-mill-en.js"></script>
    <script type="text/javascript">
	$(function(){
	    $("#toll").click(function(){
		var isadmin=$("#isadmin").val();
		if(isadmin=="2"||isadmin=="0"||isadmin=="4"){
			$("#toll1").hide();
			$("#toll2").hide();
			$("#toll3").hide();
			alert("您没有访问的权限！");
			}
		})
		$("#state").click(function(){
			var isadmin=$("#isadmin").val();
		if(isadmin=="1"||isadmin=="0"||isadmin=="4"){
			$("#state1").hide();
			alert("您没有访问的权限！");
			}
		})
		$("#in").click(function(){
			var isadmin=$("#isadmin").val();
		if(isadmin==""||isadmin==""){
			$("#in1").hide();
			$("#in2").hide();
			alert("您没有访问的权限！");
			}
		})
		$("#or").click(function(){
			var isadmin=$("#isadmin").val();
		if(isadmin=="1"||isadmin=="0"||isadmin=="4"){
			$("#or1").hide();
			alert("您没有访问的权限！");
			}
		})
		$("#pa").click(function(){
			var isadmin=$("#isadmin").val();
		if(isadmin=="1"||isadmin=="0"){
			$("#pa1").hide();
			alert("您没有访问的权限！");
			}
		})
		$("#pu").click(function(){
			var isadmin=$("#isadmin").val();
		if(isadmin==""||isadmin=="0"){
			$("#pu1").hide();
			$("#pu2").hide();
			$("#pu3").hide();
			alert("您没有访问的权限！");
			}
		})
		$("#table").click(function(){
			var isadmin=$("#isadmin").val();
		if(isadmin!="1"){
			$("#table1").hide();
			alert("您没有访问的权限！");
			}
		})
	});
</script>
</body>
</html>