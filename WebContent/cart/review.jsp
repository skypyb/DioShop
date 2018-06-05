<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../commons/basePath.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<style type="text/css">
	#div1{
		width: 900px;
		height: 600px;
		border: 1px solid black;
		margin: 0 auto;
	}
	
	#div1_1{
		width:40%;
		height:300px;
		float: left;
		border: 1px solid black;
	}
	#div1_2{
		width:60%;
		height:300px;
		float: right;
		border: 1px solid black;
	}
</style>

<script type="text/javascript">
	
</script>
<title>商品评论</title>
	<%@include file="../header.jsp" %>
	<%@include file="../search.jsp" %>
	
	<div id="div1">
		<div id="div1_1">
			<img src="${review.orderBeans[0].img_src }">
		</div>
		<div id="div1_2">
			
		</div>
		<div style="clear: both;"></div>
	</div>
	
	
	<%@include file="../yejiao.jsp" %>
</head>
<body>
	
</body>
</html>