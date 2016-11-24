<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/02.css" type="text/css"></link>
	<script src="assets/js/jquery-1.8.2.min.js"></script>
    <title>邀请好友</title>
</head>
<body>
<center>
       <form action="" id="">
        请输入邀请好友的手机号:<input type="text" id="mobile" placeholder="请输入邀请好友的手机号" style="ime-mode:Disabled" maxlength="11"><br><br>
         <label for="name">选择车牌号:</label>
		      <select class="form-control" id="provinces">
		         <option value="京">京</option>
		         <option value="津">津</option>
		         <option value="沪">沪</option>
		         <option value="渝">渝</option>
		         <option value="冀">冀</option>
		         <option value="豫">豫</option>
		         <option value="云">云</option>
		         <option value="辽">辽</option>
		         <option value="黑">黑</option>
		         <option value="湘">湘</option>
		         <option value="皖">皖</option>
		         <option value="鲁">鲁</option>
		         <option value="新">新</option>
		         <option value="苏">苏</option>
		         <option value="浙">浙</option>
		         <option value="赣">赣</option>
		         <option value="鄂">鄂</option>
		         <option value="桂">桂</option>
		         <option value="甘">甘</option>
		         <option value="晋">晋</option>
		         <option value="蒙">蒙</option>
		         <option value="陕">陕</option>
		      </select>
         <input type="text" id="vechilno" placeholder="请输入车牌号"><br><br>
               <%-- 邀请码:<strong>${invideCode}</strong><br><br> --%>
         <button type="button" id="send" name="send">发送</button>
       </form>
       </center>
       <script type="text/javascript">
    $(function(){
    	$("#send").click(function(){
    		var mobile=$("#mobile").val();
    		var plateNo=$("#provinces").val()+$("#vechilno").val();
    			$.ajax({
    				url:'expense/invitefriends.action',
    				data:{"mobile":mobile,"plateNo":plateNo},
    				type:"post",
    				success:function(res){
    					if(res=="1"){
    						alert("发送邀请成功！");
    					}else{
    						alert("发送邀请失败！");
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