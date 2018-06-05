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
		height: 280px;
		margin:0 auto;
		margin-top:50px;
		border: 1px solid #d4d4d4;
	}
	#div1_1{
		padding-top:15px;
		padding-left:10px;
		background-color: #ecffdc;
		height: 55px
	}
	#div1 ul{
		margin-top: 30px;
		margin-left:70px
	}
	#div1 ul li{
		font-size:13px;
		margin-top: 8px;
	}
	p a{
		color:#16ace4;
	}
	#div1_2{
		margin-left:30px;
		font-size: 12px;
	}
</style>

<script type="text/javascript">
	$(function(){
		
		var order_id=$("#orderid").val();
		$.ajax({
			url:"cart/orderServlet",
			data:{"action":"orderFind","order_id":order_id},
			dataType:"json",
			success:function(data){
				$("#receiver").text(data.receiver);
				$("#address").text(data.address);
			}
		})
		$("#tiaozhuan").click(function(){location.href="index.jsp"})
	})
</script>
<title>订单提交</title>
	<input id="orderid" type="hidden" value="${param.order_id }">
	<%@include file="../header.jsp" %>
	
	<div style="width: 900px;margin:0 auto; margin-top:50px;">
		<img src="images/DioShop.png" style="cursor: pointer;" id="tiaozhuan" width="200px;">
	</div>
	<div id="div1">
		<div id="div1_1">
			<img  src="images/paySuccess.png">
			<b>&nbsp;&nbsp;&nbsp;您已成功付款</b>
		</div>
		<ul>
			<li style="list-style: disc;">收货地址:&nbsp;&nbsp;<span id="address"></span></li>
			<li style="list-style: disc;">联系人姓名:&nbsp;&nbsp;<span id="receiver"></span></li>
			<li style="list-style: disc;">预计9月5日送达</li>
		</ul>
		<p style="margin-left: 53px">您可以
			<a href="cart/orderServlet?action=orderListShow">查看您买到的宝贝</a> 
			<a href="cart/orderServlet?action=orderListShow">查看交易详情</a></p>
			<hr style="width: 90%;margin-top: 25px">
			
			<div id="div1_2">
				<img src="images/warning.png">
				<b>安全提醒:</b><span>下单后，</span>
				<b style="color: #b81b22">用QQ给您发送链接办理退款的都是骗子！</b>
				<span>DioShop不存在系统升级，订单异常等问题，谨防假冒客服电话诈骗！</span>
			</div>
	</div>
	
	<%@include file="../yejiao.jsp" %>
</head>
<body>
	
</body>
</html>