<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<!-- style="overflow:hidden;width:200px;border:2px solid #e0e0e0;padding:2px;" -->
<body>
<div id=demo style="overflow:hidden;width:200px;border:2px solid #e0e0e0;padding:2px;" onmouseover="stopscroll();" onmouseout="doscroll()"> 
<div id="demo1" style="white-space:nowrap;padding:0;"> 
<a href="javascript:alert('发表新贴');"><img src="http://sharebo.oss-cn-shanghai.aliyuncs.com/image/99fea889-8127-44eb-8637-1dd10f11b1bd" height=500 width=80% border=0></a> 
<a href="javascript:alert('发表回复');"><img src="http://sharebo.oss-cn-shanghai.aliyuncs.com/image/2014489b-2b21-4c22-8513-61f9d20994bd" height=500 width=80% border=0></a>
<a href="javascript:alert('发表回复');"><img src="http://sharebo.oss-cn-shanghai.aliyuncs.com/image/9144a16b-1686-40ad-939b-d75d78b9208b" height=500 width=80% border=0></a> 
</div> 
</div> 
<!--滚动的javascript--> 
<script> 
var t=demo.scrollWidth 
demo1.innerHTML+=demo1.innerHTML 
function doMarquee() 
{ 
demo.scrollLeft=demo.scrollLeft<demo.scrollWidth-demo.offsetWidth?demo.scrollLeft+1:t-demo.offsetWidth 
} 
function doscroll() 
{ 
   sc=setInterval(doMarquee,20) 
} 
function stopscroll() 
{ 
   clearInterval(sc) 
} 
doscroll() 
</script> 
</body>
</html>