<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  <script type="text/javascript" src="js/go.js"></script>
  <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>
  <script type="text/javascript" src="js/gojsDemo.js"></script>
  <script type="text/javascript">
  	$.ajax({
  		url:'GOJSDemoServlet',
  		type:'post',
  		async:true,
  		timeout:5000,
  		success:function(data,textStatus,jqXHR){
  			console.log(data);
  			init2(data);
  		},
  		error:function(xhr,textStatus){
  			console.log(xhr);
  		}
  	});
  </script>
  <body>
    <div id = "myDiagramDiv" style="width:100%;height:100%;background-image:url(images/grid.gif)"><div>
  </body>
</html>