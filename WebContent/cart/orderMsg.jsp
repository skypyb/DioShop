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
<link rel="stylesheet" type="text/css" href="css/orderMsg.css"/>
<title>订单信息填写</title>
<script type="text/javascript">
	$(function(){
		
        init();
        submit();
	})
	
	//页面初始化，动态计算所有商品的总价和件数
	function init(){
		var priceSum=0;
        for(var i=0;i<$(".price").length;i++){
       	 priceSum += parseFloat($(".price").get(i).innerText);
        }
		var quantitySum=0;
        for(var i=0;i<$(".quantity").length;i++){
        	quantitySum += parseFloat($(".quantity").get(i).innerText);
        }
        
        $("#div1_2 table tr:eq(0) td:eq(0) span").text(quantitySum);
        $("#priceSum").text(priceSum);
	}
	
	//订单数据提交，得到所有的订单项item_ID,保存为一个数组，传到控制层
	function submit(){
		$("#div1_2 button").click(function(){
			//效验部分
			
			var receiver = $.trim($("#receiver").val());
			var mobile = $("#mobile").val();
			var address = $.trim($("#address").val());
			
			if(!receiver || !mobile || !address){
				alert("收货人信息不完整");
				return;
			}
			var regex = new RegExp("^1[34578][0-9]{9}$");
			if(!regex.test(mobile)){
				alert("请输入正确的手机号码！");
				return true;
			}
			
			var itemArray = new Array();
			for(var i=0;i<$("input[name='itme_id']").length;i++){
		        var item = parseFloat($("input[name='itme_id']").get(i).value);
		        itemArray.push(item);
		    }
			
			//发ajax请求，将用户填写的数据和保存的订单ID数组传输过去
			$.ajax({
				  url: "cart/orderServlet",
				  data: {
					"action":"submit",
				    "itemArray": itemArray,
				    "receiver":receiver,
				    "mobile":mobile,
				    "address":address
				  },
				  traditional: true,
				  success: function(data) {
					  location.href="cart/orderPay.jsp?order_id="+data+
							  "&priceSum="+$("#priceSum").text();
				  }
			});
			
		})
	}
	
	
	$(function(){
		$("#tiaozhuan").click(function(){location.href="index.jsp"})
	})
</script>
</head>
<body>
	
	<%@include file="../header.jsp" %>
	<%@include file="../search.jsp" %>
	
	
	<div id="div1">
	<img src="images/DioShop.png" style="cursor: pointer; width: 250px; margin-bottom: 50px;" id="tiaozhuan">
		<p style="color:#7f7f7f;font-size:18px;margin-bottom: 3px;">填写并核对订单信息</p>
		<div id="div1_1">
			<p class="biaoti">收货人信息</p>
			<div class="div1_1_1">
				<table>
					<tr>
						<td>收货人姓名:&nbsp;&nbsp;</td>
						<td><input id="receiver" class="form-control"></td>
					</tr>	
					<tr>
						<td>收货人手机:&nbsp;&nbsp;</td>
						<td><input id="mobile" class="form-control"></td>
					</tr>
					<tr>
						<td>收货人地址:&nbsp;&nbsp;</td>
						<td><textarea id="address" class="form-control">
						</textarea></td>
					</tr>
				</table>
			</div>
			
			
			<!-- 下面是显示订单列表 -->
			<p class="biaoti">送货清单</p>
			<div class="div1_1_1">
				<div id="tempOrder">
					<div class="container">
					    
					    <!-- 一个订单项 -->
					    <c:forEach items="${orderBeanList }" var="ol" varStatus="st">
					    	<div class="row">
					    		<input type="hidden" name="itme_id" value="${ol.itme_id }">
					    		<input type="hidden" name="itme_id" value="8">
					    		<input type="hidden" name="itme_id" value="4">
						        <div class="col-xs-1 ">
						        	<img src="${ol.img_src}">
						        </div>
						        <div class="col-xs-3 ">${ol.product.pro_brand }  ${ol.product.pro_type }  ${ol.product.pro_name }  ${ol.product.pro_uad }  ${ol.product.pro_sex }</div>
						        <div class="col-xs-1 ">颜色:${ol.prospe.pro_color }</div>
						        <div class="col-xs-1 ">尺码:${ol.prospe.pro_size }</div>
						        <div class="col-xs-1 ">单价:${ol.product.pro_price }</div>
						        <div class="col-xs-1 ">数量:<span class="quantity">${ol.quantity }</span></div>
						        <div class="col-xs-2 ">小计:<span class="price">${ol.price }</span></div>
					    	</div>
					    </c:forEach>
					</div>
					
					
				</div>
			</div>
			<p class="biaoti">备注信息</p>
			<div class="div1_1_1">
				<textarea style="width: 350px;" class="form-control">
				</textarea>
			</div>
		</div>
		<div id="div1_2">
			<table>
				<tr>
					<td><span style="color: red"></span> 件商品,总商品金额:</td>
					<td>¥<span id="priceSum"></span></td>
				</tr>
				<tr>
					<td>返现:</td>
					<td>-¥0.00</td>
				</tr>
				<tr>
					<td>运费:</td>
					<td>¥0.00</td>
				</tr>
				<tr>
					<td>服务费:</td>
					<td>¥0.00</td>
				</tr>
				<tr>
					<td></td>
					<td><button>提交订单</button></td>
				</tr>
			</table>
		</div>
		<div style="clear: both;"></div>
	</div>
	<%@include file="../yejiao.jsp" %>
</body>
</html>