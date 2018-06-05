<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="commons/basePath.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>

<title>DioShop</title>
<link rel="stylesheet" type="text/css" href="css/index.css"/>
</head>
<body>
	
	<%@include file="header.jsp" %>
	<%@include file="search.jsp" %>
	
	
	
	<div id="div2" >
		<img  src="images/DioShop.png" width="20%;">
		
		<ul id="div2Ul" style="float: right;">
			<li style="margin-left: 30px;"><a href="#">首页</a></li>
			<li><a href="product?param=productList&type=2">T恤</a></li>
			<li><a href="#">衬衫</a></li>
			<li><a href="#">短裤</a></li>
			<li><a href="#">外套</a></li>
			<li><a href="#">休闲裤</a></li>
			<li><a href="#">牛仔裤</a></li>
		</ul>
	</div>
	<!-- 滚动头图 -->
	<div id="div3">
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
		  <!-- Indicators -->
		  <ol class="carousel-indicators">
		    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
		    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
		    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
		    <li data-target="#carousel-example-generic" data-slide-to="3"></li>
		  </ol>
		 
		  <!-- Wrapper for slides -->
		  <div class="carousel-inner" role="listbox">
		    <div class="item active">
		      <img src="images/大头图/tt1.jpg" >
		    </div>
		    <div class="item">
		             <img src="images/大头图/tt2.jpg" >
		    </div>
		    <div class="item">
		             <img src="images/大头图/tt3.jpg" >
		    </div>
		    <div class="item">
		             <img src="images/大头图/tt4.jpg" >
		    </div>
		 
		    <div class="item">
		             <img src="images/大头图/tt5.jpg" >
		    </div>
		 
		  </div>
		 
		  <!-- Controls -->
		  <a class="lb_zuo" href="#carousel-example-generic" role="button" data-slide="prev">
		    <span class="glyphicon glyphicon-triangle-left" aria-hidden="true"></span>
		 <!-- 原来的左右class:::: left carousel-control         right carousel-control-->
		  </a>
		  <a class="lb_you" href="#carousel-example-generic" role="button" data-slide="next">
		    <span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span>
		 
		  </a>
		 		</div>
	</div>
	
	<!-- 商品分类显示 -->
	<div id="div4">
		
	</div>
	
	<%@include file="yejiao.jsp" %>
	
</body>
</html>