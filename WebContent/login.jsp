<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>
<%@include file="commons/basePath.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
		<title>会员登录</title>
		<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
		<!--  <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>-->
		<script src="js/bootstrap.min.js" type="text/javascript"></script>
		<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css"/>
<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #666;
    font-size: 22px;
    font-weight: normal;
    padding-right:17px;
}
 </style>
</head>
<script type="text/javascript" src="js/regandlog.js"></script>


<body>
<%@include file="header.jsp" %>
<div class="container-fluid">
				<div class="col-md-4">
					<img src="images/regandlog/DioShop.png" style="cursor: pointer" id="imgcc" height="70px" width="250px" />
				</div>
				<div class="col-md-5">
					<img src="images/regandlog/header.jpg" />
				</div>
			
			</div>

			<%@include file="search.jsp" %>
			<div class="container"  style="width:100%;height:460px;background:#FF2C4C url('images/regandlog/loginbg.jpg') no-repeat;">
<div class="row"> 
	<div class="col-md-7">
		<!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
	</div>
	
	<div class="col-md-5">
				<div style="width:440px;border:1px solid #E7E7E7;padding:20px 0 20px 30px;border-radius:5px;margin-top:60px;background:#fff;">
				<font>会员登录</font>USER LOGIN

				<div>&nbsp;</div>
<form class="form-horizontal">
  
 <div class="form-group">
    <label for="username" class="col-sm-2 control-label">用户名</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" id="user_name" name="user_name" placeholder="请输入用户名">
    </div>
  </div>
   <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-6">
      <input type="password" class="form-control" id="user_pwd" name="user_pwd" placeholder="请输入密码">
    </div>
  </div>
   <div class="form-group">
        <label for="inputPassword3" class="col-sm-2 control-label">验证码</label>
    <div class="col-sm-3" style="width:150px">
      <input type="text" class="form-control" id="verifyCode" name="verifyCode" placeholder="请输入验证码">
    </div>
    <div class="col-sm-3" style="width:210px">
      <img src="imagecode" id="codeImage" name="codeImage" /> <a id="kanbuq" href="javascript:change();">看不清，换一张</a>
    </div>
    
  </div>
   <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <div class="checkbox">
        <label>
          <input type="checkbox"> 自动登录
        </label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <label>
          <input type="checkbox"> 记住用户名
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    <input type="button" id="btnlogin"  width="100" value="登录" name="submit" border="0"
    style="background: url('images/regandlog/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
    height:35px;width:100px;color:white;">
    </div>
  </div>
</form>
</div>			
	</div>
</div>
</div>	

	<div style="margin-top:50px;width: 1300px;margin: 0 auto;">
			<img src="images/regandlog/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a>关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2010000-2020000 DioShop商城 版权所有
		</div>
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			


</body>
</html>