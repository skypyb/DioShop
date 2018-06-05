<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<!DOCTYPE html>
<html>
<head>
<style>
	header{
		height:30px;
		background-color: #f7f7f7;
		border-bottom: 1px solid #cccccc;
	}
	#headUl1{
		float:right;
		margin: 0px;
		width: 40%;
		margin-right: 60px;
	}
	#headUl1 li{
		font-size:12px;
		float: right;
		margin-right: 30px;
		margin-top: 10px;
	}
	#headUl2{
		float:left;
		margin: 0px;
		width: 40%;
		margin-left: 60px;
	}
	#headUl2 li{
		font-size:12px;
		float: left;
		margin-left: 30px;
		margin-top: 10px;
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
	<!-- 头部  -->
	<header>
		<div style="width: 70%; height: 100%;margin: 0 auto;">
			<ul id="headUl1">
				
				<c:if test="${user==null }">
					<li><a href="cart/orderServlet?action=orderListShow">我的订单</a></li>
					<li><a href="login.jsp">登陆</a></li>
					<li><a href="register.jsp">注册</a></li>
					
				</c:if>
				
				<c:if test="${user!=null }">
					<li><a href="cart/orderServlet?action=orderListShow">我的订单</a></li>
					<li><a href="#">个人中心</a></li>
					<li><a href="javascript:remove()">退出</a></li>
					<li><a href="#">${user.user_name}</a></li>
				</c:if>
			</ul>
			
			<ul id="headUl2">
				<li><a href="index.jsp">DioShop首页</a></li>
				<li><span>你好,欢迎光临DioShop</span></li>
				
			</ul>
		</div>
	</header>
	<script type="text/javascript">
		function remove(){
			layer.confirm("确认要退出吗?",function(){
				location.href="user?method=remove";
			});
		}
	</script>
</body>
</html>