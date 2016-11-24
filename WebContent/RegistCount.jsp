<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>用户注册报表</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
</head>
<body>
	<div style="width:100%;height:26px;">
			<div style="padding-left:10px;width:20px;float:left;">&nbsp;&nbsp;</div>
			<div style="width:100px;float:left">
			<label for="name">选择年份</label>
		      <select class="form-control" id="year">
		         <option value="2016">2016</option>
		         <option value="2017">2017</option>
		         <option value="2018">2018</option>
		         <option value="2019">2019</option>
		         <option value="2020">2020</option>
		         <option value="2021">2021</option>
		         <option value="2022">2022</option>
		         <option value="2023">2023</option>
		         <option value="2024">2024</option>
		         <option value="2025">2025</option>
		         <option value="2026">2026</option>
		         <option value="2027">2027</option>
		         <option value="2028">2028</option>
		         <option value="2029">2029</option>
		      </select>
		</div>
		<div style="width:100px;float:left;">
			<label for="name">选择月份</label>
		      <select class="form-control" id="month">
			      <option value="1">1</option>
			      <option value="2">2</option>
			      <option value="3">3</option>
			      <option value="4">4</option>
			      <option value="5">5</option>
			      <option value="6">6</option>
			      <option value="7">7</option>
			      <option value="8">8</option>
			      <option value="9">9</option>
			      <option value="10">10</option>
			      <option value="11">11</option>
			      <option value="12">12</option>
		      </select>
		</div>
		<div style="width:100px;float:left;padding-top:6px;padding-left:5px;">
			 <button id="sel" type="button" class="btn btn-success">查询</button>
		</div>
		<script type="text/javascript">
			$(function(){
				$("#sel").click(function(){
					//获取年
					//获取月
					window.location.href='<%=basePath%>user/getRegistCount.action?year='+$("#year").val()+'&month='+ $("#month").val();
				});
				 $("#year").val(${year});
				 $("#month").val(${month});
			})
		</script>
	</div>
	<hr/>
    <div id="main" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
   <script type="text/javascript" src="<%=basePath%>js/echarts.js"></script>
    <script type="text/javascript">
    require.config({
        paths: {
            echarts: '<%=basePath%>./dist'
        }
    });
    require(
        [
            'echarts',
            'echarts/chart/bar',
            'echarts/chart/line'
        ],
        function (ec) {
            //--- 折柱 ---
            var myChart = ec.init(document.getElementById('main'));
            myChart.setOption({
                tooltip : {
                    trigger: 'axis'
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: true},
                        dataView : {show: false, readOnly: true},
                        magicType : {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'category',
                        data : ${daysList}
                    }
                ],
                yAxis : [
                    {
                        type : 'value',
                        splitArea : {show : true}
                    }
                ],
                series : [
                    {
                        name:'用户注册量',
                        type:'bar',
                        data:${daysListDate}
                    }
                ]
            });

            window.onresize = function() {
                myChart.resize();
                myChart2.resize();
            }
        }
    );
    </script>
</body>
</html>
