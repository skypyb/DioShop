<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="commons/basePath.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/product_info.css" >
<title>Insert title here</title>
</head>
<body>
<style>
#box {
		width: 330px;
		height: 80px;
		margin: 10px auto;}
		
.head {
		width: 330px;
		height: 80px;}
			
.head p {
		margin-bottom: 15px;}
			
.head p font {
		font-size: 14px;
		color: #000;
		margin-right: 15px;}
			
.head p span {
		font-size: 8px;
		color: #666;
		border: 1px solid #b81b22;
		display: inline-block;
		padding: 3px;
		cursor: pointer;}
			
.head p span.on {
		padding: :7px;}
		
.tb-selected{
	border:2px solid orangered !important;
	background: url(images/boder.png) no-repeat right bottom; }
</style>
<!--引入头部文件  -->
<%@include file="header.jsp" %>
<%@include file="search.jsp" %>


	<div class="container">
		<div class="row">
			<div id="daohan" >
				<a href="#">首页&nbsp;&nbsp;&gt;</a> <a href="#">T恤&nbsp;&nbsp;&gt;</a>
			</div>

			<div id="zhansitu" style="margin: 0 auto;width: 950px;">
				<div class="col-md-6">
					<img id="zhuimg" class="medium" src="">
				</div>

				<div class="col-md-6">
					<div>
						<strong>${currentProduct.pro_name }</strong>
					</div>
					<div id="number" style="border-bottom: 1px dotted #dddddd; width: 350px; margin: 10px 0 10px 0;">
						<div>编号：<span id="pro">${currentProduct.pro_id }</span></div>
						<!-- 隐藏域 商品ID -->
						<input type="hidden" id="pro_id" value="${currentProduct.pro_id }">
						<!-- 隐藏域 规格ID -->
						<input type="hidden" id="pros_id" value="">
						
					</div>

					<div style="margin: 10px 0 10px 0;">
						一口价: <strong style="color: #b81b22;">￥：${currentProduct.pro_price }元/件</strong> 参 考 价：
						<del>￥1200元/件</del>
					</div>

					<div style="margin: 10px 0 10px 0;">
						促销: <a target="_blank" title="限时抢购 (2018-5-28 ~ 2018-06-18)"
							style="background-color: #f07373;">限时抢购</a>
					</div>

					<div style="padding: 10px; border: 1px solid #e7dbb1; width: 330px; height:240px; margin: 15px 0 10px 0;; background-color: #fffee6;">
		<!-- 规格选择部分 -->
		<div id="box">
			<div class="head">
				<p class="model">
					<font>型号</font>
					<span id="rule" class="">L</span>
					<span id="rule" class="">M</span>
					<span id="rule" class="">XL</span>
				</p>
				
				<p class="color">
					<font>颜色</font>
					<span id="rule" class="">珍珠白</span>
					<span id="rule" class="">睿智黑</span>
					<span id="rule" class="">玫瑰红</span>
				</p>
				</div>

		</div>			
						
						
						<div style="border-bottom: 1px solid #faeac7;  padding-left: 10px;">
							购买数量: <input id="buyNum" name="buyNum" value="1"	maxlength="4" size="6" type="number">
							<span id="inventory" >库存数：  件</span>
						</div>

						<div style="margin: 20px 0 10px 0; text-align: center;">
							<a href="javascript:void(0);"  id="addcart"> 
							<input	style=" height: 36px; width: 100px;" value="加入购物车" type="button">
							</a> 
							<a href="javascript:"> 
							<input	style=" height: 36px; width: 100px;" value="立即购买" type="button">
							</a>&nbsp;<a href="#">收藏该商品</a>
						</div>
					</div>
				</div>
			</div>
			
			<div class="clear"></div>
			<div style="width: 950px; margin: 0 auto;">
		

				<div style="background-color: #b81b22; width: 930px; padding: 10px 10px; margin: 10px 0 10px 0;">
					<strong>商品参数</strong>
				</div>
				<div style="margin-top: 10px; width: 900px;">
					<table class="table table-bordered">
						<tbody>
							<tr class="active">
								<th colspan="2">基本参数</th>
							</tr>
							<tr>
								<th width="10%">类型</th>
								<td width="30%">${currentProduct.pro_type }</td>
							</tr>
							<tr>
								<th width="10%">商品名</th>
								<td>${currentProduct.pro_name }</td>
							</tr>
							<tr>
								<th width="10%">尺寸</th>
								<td>大小</td>
							</tr>
							<tr>
								<th width="10%">品牌</th>
								<td>${currentProduct.pro_brand }</td>
							</tr>
							<tr>
								<th width="10%">颜色</th>
								<td>颜色</td>
							</tr>
							<tr>
								<th width="10%">男|女</th>
								<td>${currentProduct.pro_sex }</td>
							</tr>
							<tr>
								<th width="10%">上装|下装</th>
								<td>${currentProduct.pro_uad }</td>
							</tr>
						</tbody>
					</table>
				</div>

				<div style="background-color: #b81b22; width: 930px; padding: 10px 10px; margin: 10px 0 10px 0;">
					<strong>商品介绍</strong>
				</div>

				<div>
					<img style="width: 930px;" src="images/productInfo/xq_2_1.jpg">
				</div> 
				<div>
					<img style="width: 930px;" src="images/productInfo/xq_2_2.jpg">
				</div> 
				<div>
					<img style="width: 930px;" src="images/productInfo/xq_2_3.jpg">
				</div> 
				<div style="background-color: #d3d3d3; width: 900px;">
					<table class="table table-bordered">
						<tbody>
							<tr class="active">
								<th><strong>商品评论</strong></th>
							</tr>
							<tr class="warning">
								<th>暂无商品评论信息 <a>[发表商品评论]</a></th>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">


function immediatelyBuy(){
	$("input[value='立即购买']").click(function(){
		var pro_id = $("#pro_id").val();
		var pros_id = $("#pros_id").val();
		var quantity = $("#buyNum").val();
		
		location.href="cart/orderServlet?action=immediatelyBuy&pro_id="+pro_id
				+"&pros_id="+pros_id+"&quantity="+quantity;
	});
}


$(function(){
	immediatelyBuy();
	
	/* 商品的规格操作点击事件（尺寸的选择） */
	$(".model span").click(function() {
		$(this).parent().children().removeClass();
		$(this).attr("class","tb-selected");
		model();
	});
	/* 商品的规格操作点击事件（颜色的选择） */
	$(".color span").click(function() {
		$(this).parent().children().removeClass();
		$(this).attr("class","tb-selected");
		//此方法用于ajax向后台提交当前选择的商口ID,规格,颜色
		model();
		
	});
	
	/* 获取当前商品的主图片 */
	var pro_id = $("#pro_id").val();
	$.ajax({
		url:"product",
		data:{"param":"findImageSrc",pro_id:pro_id},
		dataType:"text",
		success:function(data){
			$("#zhuimg").attr("src",data);
		}
	});
});

function addCart(){
	var buyNum = $("#buyNum").val();
	var pros_id = $("#pros_id").val();
	var pro_id = $("#pro_id").val();
	var pro_img = $("#zhuimg").attr("src");
	//alert(pro_id+":"+pros_id+":"+buyNum+":"+pro_img);
	location.href="cart/product?param=addCart&quantity="+buyNum+"&pro_id="+pro_id+"&pros_id="+pros_id+"&pro_img="+pro_img;
}


/* 查询商品库存 */
function model(){
	var model=$(".model .tb-selected").text();
	var color=$(".color .tb-selected").text();
	var pid=$("#pro_id").val();
	//alert(model+" :"+color);
	if(model==""||color==""){
		return;
		}
	/* 当用户将尺寸和颜色都选择了，再执行ajax请求 */
	$.ajax({
		url:"product",
		data:{"param":"inventory","model":model,"color":color,"pid":pid},
		dataType:"JSON",
		success:function(data){
			//alert(data.pro_inv);
			//返回当前选择商品规格库存数量
			$("#inventory").text("库存数："+data.pro_inv+"件");
			//返回当前商品规格的id,放入到session域中。
			$("#pros_id").val(data.pros_id);
			
			$("#addcart").attr("href","javascript:addCart()");
		}
	});
	
}

</script>
</html>