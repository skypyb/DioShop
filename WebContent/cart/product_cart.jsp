<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="../commons/basePath.jsp" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-3.2.1.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>dio商城购物车</title>
</head>
<script type="text/javascript">
$(function(){
	//提交订单
	$("#submit").click(function(){
		var pros_ids = new Array();
		for(var i=0; i<$(".check:checked").length;i++){
			var pros = $(".check:checked").get(i).value;
			pros_ids.push(pros);
		}
		
		//发ajax请求，将保存的规格ID数组传输过去
		$.ajax({
			  url: "cart/orderServlet",
			  data: {
				"action":"cartToOrder",
			    "pros_ids": pros_ids
			  },
			  traditional: true,
			  success:function(data){
				  location.href="cart/orderMsg.jsp";
			  }
		});
	});
	
	
	
	//alert(111);
	/* 数量添加操作 */
	var num;
	$(".add").click(function(){
			//数量加1
			 num = parseFloat( $(this).siblings(".num").val())+1;
			 $(this).siblings(".num").val(num);
			 //获取价格*数量=小计
			var price=parseFloat($(this).parent().parent().children("#price").text());
			$(this).parent().parent().children().eq(7).children().text(num*price);
			var newTotal = $(this).parent().parent().children().eq(7).children().text();
			//alert(newTotal);
			//$("#newTotal").text(newTotal);
			setTotal();
		}); 
	
	/* 数量减少操作 */
	$(".min").click(function(){
		num = parseFloat( $(this).siblings(".num").val())-1;
			 $(this).siblings(".num").val(num);
			if(num<1){
				num=1;
			}
			 $(this).siblings(".num").val(num);
			 var price = parseFloat($(this).parent().parent().children("#price").text());
			 $(this).parent().parent().children().eq(7).children().text(num*price);
			 setTotal();
	});
	
	function setTotal(){
		var s=0;
		$(".subtotal").each(function(){
			s+=parseFloat($(this).parent().parent().children().eq(7).children().text());
		});
		//alert(s);
		$("#newTotal").text(s);
	}
	
	
	
	
	
});
	function deleteItem(pid){
		if(confirm("您确认删除吗？")){
			location.href="cart/product?param=deleteItem&pid="+pid;
		}
	}
	function emptyItems(){
		if(confirm("您确认清空购物车吗？")){
			location.href="cart/product?param=emptyItems";
		}
	}
	
	
	
</script>
<style>
	th{
	color: white;
	}

</style>
<body>
<%@include file="../header.jsp" %>
<%@include file="../search.jsp" %>



<c:if test="${empty cart.cartItems }">
<div>
	<div style="width:50px;height: 50px;float: left;margin-left: 620px;margin-top: 230px;position:absolute;">
		<a href="index.jsp">
		<button class="btn btn-danger" style="width:300px;height: 60px;font-size: 40px">马上购物</button>
		</a>
	</div>
	<div style="margin: 0 auto; width:1024;height:600px">
		<img style="" width="1024px;" height="500px" src="images/emptycart.png">
	</div>

</div>
</c:if>
<c:if test="${!empty cart.cartItems }">
<div class="container">
			<div class="row">
				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong style="font-size:16px;margin-left:15px;">购物车清单</strong>
					<table class="table table-bordered">
						<tbody>
							<tr  style="background-color: #b81b22;">
								<th>选择</th>
								<th>图片</th>
								<th>商品</th>
								<th>价格</th>
								<th>颜色</th>
								<th>尺寸</th>
								<th>数量</th>
								<th>小计</th>
								<th>操作</th>
							</tr>
				<c:forEach items="${cart.cartItems }" var="entry">
							<tr class="active">
							
							<td><input class="check" name="check" type="checkBox" value="${entry.value.prospe.pros_id }" ></td>
								<td width="60" width="40%">
									<input type="hidden" id="pid" value="${entry.value.product.pro_id }">
									<input type="hidden" id="pros_id" value="${entry.value.prospe.pros_id }">
									<img src="${entry.value.proImageSrc }" width="70" height="60">
								</td>
								<td width="20%">
									<a target="_blank"> ${entry.value.product.pro_name }</a>
								</td>
								<td width="10%" id="price">${entry.value.product.pro_price }</td>
								<td >${entry.value.prospe.pro_color }</td>
								<td >${entry.value.prospe.pro_size }</td>
								<td width="20%" >
									<input  class="min btn btn-default" name="" type="button" value="-" style="height: 25px;padding-top:0px"/>
									<input size="1px" class="num" name="" type="text" value="${entry.value.buyNum }"  style="text-align: center;"/>
									<input class="add btn btn-default" name="" type="button" value="+" style="height: 25px;padding-top:0px"/>
								</td>
								<td width="15%" id="td1"><!-- 小计 -->
									<span id="Total" class="subtotal">${entry.value.product.pro_price*entry.value.buyNum }</span>
								</td>
								<td>
									<a id="delete" href="javascript:onClick=deleteItem('${entry.value.prospe.pros_id }');" class="delete">删除</a>
								</td>
							</tr>
				</c:forEach>
						</tbody>
					</table>
				</div>
			</div>

			<div style="margin-right:130px;">
				<div style="text-align:right;">
					<em style="color:#ff6600;">	&nbsp;&nbsp;</em> 
					赠送积分: 
			<em style="color:#ff6600;">${cart.total }</em>&nbsp; 
			商品金额:￥ 
			<strong id="newTotal" style="color:#ff6600;">${cart.total }元</strong>
				</div>
				<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
					<a href="javascript:emptyItems()" id="clear" class="clear">清空购物车</a>
						<input width="100" type="button" value="提交订单" id="submit" border="0" style="background: url('images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
						height:35px;width:100px;color:white;">
				</div>
			</div>

		</div>


</c:if>


</body>
</html>