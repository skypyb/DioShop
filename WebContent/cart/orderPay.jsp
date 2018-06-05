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
		height: 500px;
		margin:0 auto;
		margin-top:50px;
	}
	#div1 p,h2,img{
		text-align:center;
		width: 200px;
		margin: 0 auto;
		margin-top: 10px;
	}
	#div1 button{
		padding:5px;
		margin: 0 auto;
		background-color: #409bff;
		color: white;
		border: 0px;
		width: 120px;
		font-size: 16px;
		font-weight: bold;
	}
	h2{
		color:red;
	}
	#div1 button:hover{
		background-color: #8ac8ff;
	}
</style>

<script type="text/javascript">
	$(function(){
		$("#tiaozhuan").click(function(){location.href="index.jsp"})
		
		$("button").click(function(){
			var order_id = $(this).attr("id");
			layer.confirm("确认要支付吗?谨防商家恶意诈骗，否则您可能钱货两空!",function(){
				location.href="cart/orderFlow?action=pay&order_id="+order_id;
			});
			
		})
	})
</script>
<title>订单提交</title>
	<%@include file="../header.jsp" %>
	<div id="div1">
	<img src="images/DioShop.png" style="cursor: pointer;" id="tiaozhuan">
	<hr>
		<p>扫一扫付款（元）</p>
		<h2>¥<span id="sumPrice">${param.priceSum }</span></h2>
		<p><img  src="images/pay.png"></p>
		<p><button id="${param.order_id}">确认支付</button></p>
	</div>
	
	<%@include file="../yejiao.jsp" %>
</head>
<body>
	
</body>
</html>