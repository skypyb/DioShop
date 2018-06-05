<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="commons/basePath.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/product-list.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
			<!--引入头部文件  -->
	<%@include file="header.jsp" %>
	<%@include file="search.jsp" %>
<div class="row" style="width: 1210px; margin: 0 auto;">
	<c:forEach items="${productList }" var="pro" varStatus="ss">
	<div class="col-md-2"> 
		<a href="product?param=findSingleProduct&pid=${pro.pro_id }">
		 <img  style="cursor: pointer; " src="${imageList[ss.index] }"
			width="170" height="170" style="display: inline-block;">
		</a>
		<p>
			<a href="product?param=findSingleProduct&pid=${pro.pro_id }" style='color: pink'>${pro.pro_name}</a>
		</p>
		<p>
			<font color="#FF0000">商城价：&yen;${pro.pro_price}</font>
		</p>
	</div>
	</c:forEach>
</div>

</body>
</html>