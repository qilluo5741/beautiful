<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>享泊管理端</title>
	<link href="layer/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="layer/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">
    <link href="layer/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
    <link href="layer/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
    <link href="layer/css/animate.css" rel="stylesheet">
    <link href="layer/css/style.css?v=2.2.0" rel="stylesheet">
</head>
<body>
<div id="wrapper" style="position:fixed;">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element"><span>
                            <img alt="image" class="img-circle" src="layer/img/logo.png" width="80px;" height="95px;"/>
                             </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="index.html#">
                                <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${mobile}</strong>
                             </span> <span class="text-muted text-xs block"><b class="caret"></b></span> </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a href="javascript:void(0);">修改头像</a>
                                </li>
                                <textarea id="isadmin" style="border: 0;color:#f8f8f8">${isadmin}</textarea>
                                <li><a href="javascript:void(0);">个人资料</a>
                                </li>
                                <li><a href="javascript:void(0);">联系我们</a>
                                </li>
                                <li><a href="javascript:void(0);">信箱</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="Exit.jsp">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">
                            BATP
                        </div>
                    </li>
                    <li class="active">
                        <a href="manager.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">主页</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="manager.jsp">我的主页</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="manager.jsp" id="state"><i class="fa fa fa-globe"></i> <span class="nav-label">状态机管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                          <li><a href="statemachine.jsp" target="menuFrame" id="state1">状态机上下线</a></li>
                            </ul>
                    </li>
                    <li>
                        <a href="manager.jsp#" id="in"><i class="fa fa-edit"></i> <span class="nav-label">邀请好友</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="invite.jsp" target="menuFrame" id="in2">邀请好友</a></li>
                            <li><a href="detail/user/invite.action?mobile=${mobile}" target="menuFrame" id="in1">邀请记录</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="manager.jsp#" id="or"><i class="fa fa-edit"></i> <span class="nav-label">预约订单</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="detail/user/orderRecord.action?mobile=${mobile}" target="menuFrame" id="or1">预约订单</a>
                            </li>
                        </ul> 
                    </li>
                     <li>
                        <a href="manager.jsp#" id="pa"><i class="fa fa-edit"></i> <span class="nav-label">停车收费</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="detail/parkfee.action?mobile=${mobile}" target="menuFrame" id="pa1">停车收费</a>
                            </li>
                        </ul> 
                    </li>
                    <li>
                        <a href="manager.jsp#" id="pu"><i class="fa fa-edit"></i> <span class="nav-label">我的钱包</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="detail/selectPurse.action?mobil=${mobile}" target="menuFrame" id="pu1">余额明细</a></li>
                            <li><a href="javascript:void(0);" target="menuFrame" id="pu2">余额提现</a></li>
                            <li><a href="detail/balance.action?mobile=${mobile}" target="menuFrame" id="pu3">交易记录</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="manager.jsp" id="toll"><i class="fa fa-edit"></i> <span class="nav-label">收费员管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="tollrecord.jsp" target="menuFrame" id="toll3">关联收费员</a></li>
                            <li><a href="detail/tollrecord.action" target="menuFrame" id="toll1">创建记录</a></li>
                            <li><a href="feestype.jsp" target="menuFrame" id="toll2">收费模式</a></li>
                          </ul>
                    </li>
                    <li>
                        <a href="#" id="table"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">统计报表</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="Park/getparkorder.action?mobile=${mobile}" target="menuFrame" id="table1">停车场报表</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>

        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="manager.jsp#"><i class="fa fa-bars"></i> </a>
                        <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                            <div class="form-group">
                                <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                            </div>
                        </form>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li>
                            <span class="m-r-sm text-muted welcome-message"><a href="manager.jsp" title="返回首页"><i class="fa fa-home"></i></a>欢迎使用享泊管理端</span>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="index.jsp#">
                                <i class="fa fa-envelope"></i> <span class="label label-warning"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-messages">
                                <li>
                                    <div class="dropdown-messages-box">
                                        <a href="javascript:void(0);"  target="menuFrame" class="pull-left">
                                            <img alt="image" class="img-circle" src="layer/img/a7.jpg">
                                        </a>
                                        <div class="media-body">
                                            <small class="pull-right">46小时前</small>
                                            <strong>旧梦丶毁佳人</strong> 项目已处理完结
                                            <br>
                                            <small class="text-muted">3天前 2016.6.19</small>
                                        </div>
                                    </div>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <div class="dropdown-messages-box">
                                        <a href="javascript:void(0);"  target="menuFrame" class="pull-left">
                                            <img alt="image" class="img-circle" src="layer/img/a4.jpg">
                                        </a>
                                        <div class="media-body ">
                                            <small class="pull-right text-navy">25小时前</small>
                                            <strong>指尖丶绕情缘</strong> 这是一条测试信息
                                            <br>
                                            <small class="text-muted">昨天</small>
                                        </div>
                                    </div>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <div class="text-center link-block">
                                        <a href="javascript:void(0);" target="menuFrame">
                                            <i class="fa fa-envelope"></i> <strong> 查看所有消息</strong>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="index.jsp#">
                                <i class="fa fa-bell"></i> <span class="label label-primary"></span>
                            </a>
                            <ul class="dropdown-menu dropdown-alerts">
                                <li>
                                    <a href="javascript:void(0);">
                                        <div>
                                            <i class="fa fa-envelope fa-fw"></i> 您有16条未读消息
                                            <span class="pull-right text-muted small">4分钟前</span>
                                        </div>
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="javascript:void(0);">
                                        <div>
                                            <i class="fa fa-qq fa-fw"></i> 3条新回复
                                            <span class="pull-right text-muted small">12分钟前</span>
                                        </div>
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <div class="text-center link-block">
                                        <a href="javascript:void(0);"  target="menuFrame">
                                            <strong>查看所有</strong>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div> 
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="Exit.jsp">
                                <i class="fa fa-sign-out"></i> 退出
                            </a>
                        </li>
                    </ul>

                </nav>
            </div>
            <div class="row  border-bottom white-bg dashboard-header">
       			 <iframe id="menuFrame" name="menuFrame" src="layer/img/bg3.jpg" style="overflow:visible;border:none;" scrolling="yes" width="100%" height="850px;"></iframe>
            </div>
        </div>
    </div>
    <script src="layer/js/jquery-2.1.1.min.js"></script>
    <script src="layer/js/bootstrap.min.js?v=3.4.0"></script>
    <script src="layer/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="layer/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="layer/js/hplus.js?v=2.2.0"></script>
    <script src="layer/js/plugins/pace/pace.min.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
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
})
</script>
</body>
</html>