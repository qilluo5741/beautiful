/**
 * 
 */
$(function(){
	$("#toll").click(function(){
		var isadmin=$("#isadmin").val();
		if(isadmin=="2"||isadmin=="0"||isadmin=="4"){
			$("#toll").removeAttr("href");
			$("#toller").removeAttr("href");
			alert("您没有访问的权限！");
			}
		})
		$("#toller").click(function(){
		var isadmin=$("#isadmin").val();
		if(isadmin=="2"||isadmin=="0"||isadmin=="4"){
			$("#toll").removeAttr("href");
			$("#toller").removeAttr("href");
			alert("您没有访问的权限！");
			}
		})
		$("#state").click(function(){
		var isadmin=$("#isadmin").val();
		if(isadmin=="1"||isadmin=="0"||isadmin=="4"){
			$("#state").removeAttr("href");
			$("#stater").removeAttr("href");
			alert("您没有访问的权限！");
			}
		})
		$("#stater").click(function(){
		var isadmin=$("#isadmin").val();
		if(isadmin=="1"||isadmin=="0"||isadmin=="4"){
			$("#state").removeAttr("href");
			$("#stater").removeAttr("href");
			alert("您没有访问的权限！");
			}
		})
		$("#in").click(function(){
		var isadmin=$("#isadmin").val();
		if(isadmin==""||isadmin==""){
			$("#in").removeAttr("href");
			$("#iner").removeAttr("href");
			alert("您没有访问的权限！");
			}
		})
		$("#iner").click(function(){
		var isadmin=$("#isadmin").val();
		if(isadmin==""||isadmin==""){
			$("#in").removeAttr("href");
			$("#iner").removeAttr("href");
			alert("您没有访问的权限！");
			}
		})
		$("#or").click(function(){
		var isadmin=$("#isadmin").val();
		if(isadmin=="1"||isadmin=="0"||isadmin=="4"){
			$("#or").removeAttr("href");
			$("#orer").removeAttr("href");
			alert("您没有访问的权限！");
			}
		})
		$("#orer").click(function(){
		var isadmin=$("#isadmin").val();
		if(isadmin=="1"||isadmin=="0"||isadmin=="4"){
			$("#or").removeAttr("href");
			$("#orer").removeAttr("href");
			alert("您没有访问的权限！");
			}
		})
		$("#pa").click(function(){
		var isadmin=$("#isadmin").val();
		if(isadmin=="1"||isadmin=="0"){
			$("#pa").removeAttr("href");
			$("#paer").removeAttr("href");
			alert("您没有访问的权限！");
			}
		})
		$("#paer").click(function(){
		var isadmin=$("#isadmin").val();
		if(isadmin=="1"||isadmin=="0"){
			$("#pa").removeAttr("href");
			$("#paer").removeAttr("href");
			alert("您没有访问的权限！");
			}
		})
		$("#pu").click(function(){
		var isadmin=$("#isadmin").val();
		if(isadmin==""||isadmin=="0"){
			$("#pu").removeAttr("href");
			$("#puer").removeAttr("href");
			alert("您没有访问的权限！");
			}
		})
		$("#puer").click(function(){
		var isadmin=$("#isadmin").val();
		if(isadmin==""||isadmin=="0"){
			$("#pu").removeAttr("href");
			$("#puer").removeAttr("href");
			alert("您没有访问的权限！");
			}
		})
	})