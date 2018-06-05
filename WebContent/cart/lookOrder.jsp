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
<script type="text/javascript" src="js/lookOrder.js"></script>
<link rel="stylesheet" href="css/lookOrder.css">


	<script type="text/javascript">
		
	</script>
<title>查看订单页面</title>
</head>
<body>
<%@include file="../header.jsp" %>
<%@include file="../search.jsp" %>

<div id="div1">
	<div id="orderType" style="display: block;">
		<div><a href="javascript:" id="all">所有订单</a></div>
		<div><a href="javascript:status0()" id="0">待付款</a></div>
		<div><a href="javascript:status1()" id="1">待发货</a></div>
		<div><a href="javascript:status2()" id="2">待收货</a></div>
		<div><a href="javascript:status3()" id="3">待评价</a></div>
		<div id="zhanwei" style="width:550px;">&nbsp;</div>
	</div>
	<div style="clear: both;"></div>
	<table id="order">
		<tr id="orderTitle">
			<td width="55%">宝贝</td>
			<td width="10%">单价</td>
			<td width="8%">数量</td>
			<td width="12%">实付款</td>
			<td width="15%">交易操作</td>
		</tr>
	</table>
		
		
	<div class="orderItemList">
		<!-- 遍历 superOrderBean集合，提取数据并展示-->
		<c:forEach items="${orderList }" var="sob" varStatus="st">
			<table class="order" style="display: table;">
				<input type="hidden" class="flag" value="${sob.order.status}"/>
				<!-- 该订单项的页头 -->
				<tr class="orderTitle">
					<td width="15%">${ sob.order.createDate}</td>
					<td width="50%" colspan="2">&nbsp;&nbsp;&nbsp;订单编号:<span>${ sob.order.orderCode}</span></td>
					<td width="8%"></td>
					<td width="12%"></td>
					<td width="15%" align="right"><a id="${sob.order.order_id }" href="javascript:">删除</a></td>
				</tr>
				
				<!-- 该订单每个订单项,遍历Orderbean -->
				<c:forEach items="${sob.orderBeans}" var="ob" varStatus="sb">
					<tr class="orderItem">
						<td width="10%"><img src="${ob.img_src }"/></td>
						<td width="38%"><a href="product?param=findSingleProduct&pid=${ob.product.pro_id }">${ob.product.pro_type } &nbsp;&nbsp;${ob.product.pro_name }&nbsp;&nbsp;${ob.product.pro_brand }
						&nbsp;&nbsp;${ob.product.pro_sex }&nbsp;&nbsp;${ob.product.pro_uad }&nbsp;&nbsp;${ob.prospe.pro_size }&nbsp;&nbsp;${ob.prospe.pro_color }</a></a></td>
						<td width="12%" align="center">${ob.product.pro_price }</td>
						<td width="8%" align="center">${ob.quantity}</td>
						<c:if test="${sb.index==0 }">
							<td width="12%" align="center" style="color:#b81b22;font-size: 16px;font-weight: 600">¥<span>${sob.orderPrice}</span></td>
						</c:if>
						<c:if test="${sb.index!=0 }">
							<td width="12%" align="center" style="color:#b81b22;font-size: 16px;font-weight: 600;border-color: white;"></td>
						</c:if>
						<td width="15%" align="center" style="border-color: white;">
						<c:if test="${sb.index==0 }"><!-- 只会在一个格子显示操作按钮 -->
						<!-- 这里面写订单的各种类型能够做什么样的操作 -->
							<c:if test="${sob.order.status ==0 }"><!-- 0:显示，表示订单未支付 -->
								<input type="hidden" class="sumPrice" value="${sob.orderPrice }">
								<button class="btn-immPay" value="${sob.order.order_id}">
									立即支付</button><br/>
							</c:if>
							<c:if test="${sob.order.status ==1 }"><!-- 1:用户已支付，但未发货 -->
								<button class="btn-1" value="${sob.order.order_id}">
									催卖家发货</button><br/>
									<a href="javascript:refund(${sob.order.order_id })">退款</a>
							</c:if>
							<c:if test="${sob.order.status ==2 }"><!-- 2:已发货，用户未接收 -->
								<button class="btn-2" value="${sob.order.order_id}">
									确认收货</button><br/>
									<a href="javascript:refund(${sob.order.order_id })">退款</a>
							</c:if>
							<c:if test="${sob.order.status ==3 }"><!-- 3:用户已收货，订单处于完成状态 -->
								<button class="btn-3" value="${sob.order.order_id}" style="border:1px solid black; ">
									评价</button><br/>
									<a href="javascript:refund(${sob.order.order_id })">退货</a>
							</c:if>
							<c:if test="${sob.order.status ==4 }"><!-- 4:订单处于申请退款状态 -->
								<button class="btn-4" value="${sob.order.order_id}">
									催卖家退款</button><br/>
							</c:if>
						</c:if>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:forEach>
	</div>
</div>
<%@include file="../yejiao.jsp" %>
</body>
</html>