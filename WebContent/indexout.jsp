<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>BATP停车后台管理</title>
	<link href="layer/css/bootstrap.min.css?v=3.4.0" rel="stylesheet">
    <link href="layer/font-awesome/css/font-awesome.css?v=4.3.0" rel="stylesheet">
    <link href="layer/css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
    <link href="layer/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">
    <link href="layer/css/animate.css" rel="stylesheet">
    <link href="layer/css/style.css?v=2.2.0" rel="stylesheet">
</head>
<body>
<div id="wrapper" style="position: fixed;">
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                   <%--<li class="nav-header">
                        <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="layer/img/logo.png" width="80px;" height="95px;"/>
                             </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="indexout.html#">
                                <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">${user.userName}</strong>
                             </span> <span class="text-muted text-xs block">超级管理员 <b class="caret"></b></span> </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a href="javascript:void(0);">修改头像</a>
                                </li>
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
                    </li>--%>
                    <li class="active">
                        <a href="indexout.jsp"><i class="fa fa-th-large"></i> <span class="nav-label">我的主页</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="indexout.jsp">我的首页</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="indexout.jsp#"><i class="fa fa fa-globe"></i> <span class="nav-label">账户管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                          <li><a href="drawcash/selectAll.action" target="menuFrame">账户提现</a>
                            </li>
                        </ul>
                    </li>
                     <li>
                        <a href="indexout.jsp#"><i class="fa fa-files-o"></i> <span class="nav-label">保安管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="relation/selectAll.action" target="menuFrame">保安拉单</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="indexout.jsp#"><i class="fa fa-sitemap"></i> <span class="nav-label">交易管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="detail/selectAll.action" target="menuFrame">交易信息</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="indexout.jsp#"><i class="fa fa-flask"></i> <span class="nav-label">用户管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="relation/selectAlls.action" target="menuFrame">用户权限</a></li>
                        </ul>
                    </li>
                     <li>
                        <a href="indexout.jsp#"><i class="fa fa-edit"></i> <span class="nav-label">订单管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="orderinfo/selectAll.action" target="menuFrame">订单信息</a></li>
                        </ul>
                    </li>
                     <li>
                        <a href="indexout.jsp#"><i class="fa fa-flask"></i> <span class="nav-label">停车管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="Park/selectAlls.action" target="menuFrame">停车信息</a> </li>
                        </ul>
                    </li>
                    <li>
                        <a href="indexout.jsp#"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">统计报表</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a href="user/getRegistCount.action" target="menuFrame">用户注册</a>
                            </li>
                            <li><a href="Park/getRegistCount.action" target="menuFrame">停车数据</a>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>

        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
                        <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="indexout.jsp#"><i class="fa fa-bars"></i> </a>
                        <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                            <div class="form-group">
                                <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                            </div>
                        </form>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li>
                            <span class="m-r-sm text-muted welcome-message"><a href="indexout.jsp" title="返回首页"><i class="fa fa-home"></i></a>欢迎使用BATP停车</span>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="indexout.jsp#">
                                <i class="fa fa-envelope"></i> <span class="label label-warning">76</span>
                            </a>
                            <ul class="dropdown-menu dropdown-messages">
                                <li>
                                    <div class="dropdown-messages-box">
                                        <a href="javascript:void(0);"  target="menuFrame" class="pull-left">
                                            <img alt="image" class="img-circle" src="layer/img/a7.jpg">
                                        </a>
                                        <div class="media-body">
                                            <small class="pull-right">9小时前</small>
                                            <strong>旧梦丶毁佳人</strong> 项目已处理完结
                                            <br>
                                            <small class="text-muted">1天前 2016.8.02</small>
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
                                            <small class="pull-right text-navy">14小时前</small>
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
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="indexout.jsp#">
                                <i class="fa fa-bell"></i> <span class="label label-primary">7</span>
                            </a>
                            <ul class="dropdown-menu dropdown-alerts">
                                <li>
                                    <a href="javascript:void(0);">
                                        <div>
                                            <i class="fa fa-envelope fa-fw"></i>您有1条未读消息
                                            <span class="pull-right text-muted small">5分钟前</span>
                                        </div>
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <a href="javascript:void(0);">
                                        <div>
                                            <i class="fa fa-qq fa-fw"></i>您有6条新回复
                                            <span class="pull-right text-muted small">54分钟钱</span>
                                        </div>
                                    </a>
                                </li>
                                <li class="divider"></li>
                                <li>
                                    <div class="text-center link-block">
                                        <a href="javascript:void(0);"  target="menuFrame">
                                            <strong>查看所有 </strong>
                                            <i class="fa fa-angle-right"></i>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <a href="Exit.jsp">
                                <i class="fa fa-sign-out"></i>退出
                            </a>
                        </li>
                    </ul>

                </nav>
            </div>
            <div class="row  border-bottom white-bg dashboard-header">
       			 <iframe id="menuFrame" name="menuFrame" src="layer/img/bg3.jpg" style="overflow:visible;border:none;" scrolling="auto" width="100%" height="1420px;"></iframe>
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
</body>
</html>