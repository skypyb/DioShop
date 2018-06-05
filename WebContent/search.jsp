<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	#seadiv1{
		width: 1200px;
		height: 80px;
		/* border: 1px solid black; */
		margin: 0 auto;
		margin-top: 40px;
	}
	#seadiv1_1{
		width: 35%;
		float: right;
	}
	#seadiv1_1 button{
		background-color: #b81b22;
		color: white;
		border-radius: 0;
	}
	
	#seadiv1_1 input,button{
		height:35px;
		margin-right: 25px;
	}
	ul{
		padding: 0;
	}
	li{
		list-style-type: none;
	}
	a{
		color:gray;
	}
	a:hover{
		color: #b81b22;
		text-decoration: none;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
	<!-- 搜索框框和购物车框框 -->
	<div id="seadiv1">
		<div class="input-group" id="seadiv1_1">
      		<input type="text" class="form-control" placeholder="搜索..." width="200px">
      		<span class="input-group-btn">
       			<button class="btn btn-default" type="button">搜索</button>
      		</span>
      		<span class="input-group-btn">
      			<a href="cart/product_cart.jsp">
       			<button class="btn btn-default" type="button" style="width: 90px;">购物车(0)</button>
       			</a>
      		</span>
    	</div>
    	<div style="clear: both;"></div>
    	<p align="right" style="font-size: 12px;margin-right: 50px;margin-top: 10px; color: gray">热门搜索：免烫衬衫  水柔棉  熊本熊  麻衬衫  帆布鞋  运动  户外  家居</p>
	</div>
</body>
</html>