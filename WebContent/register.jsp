<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html >
<html>
<head>

<%@include file="commons/basePath.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员注册</title>
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
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
    color: #3164af;
    font-size: 18px;
    font-weight: normal;
    padding: 0 10px;
}
 </style>
<script type="text/javascript">
function checkna(){
	na=form1.user_name.value;
  	if( na.length <1 || na.length >12)  
		{  	
			divname.innerHTML='<font class="tips_false">长度是1~12个字符</font>';
		     
		}else{  
		    divname.innerHTML='<font class="tips_true">可以注册</font>';
		   
		}  
	
}
//验证密码 
function checkpsd1(){    
	psd1=form1.user_pwd.value;  
	var flagZM=false ;
	var flagSZ=false ; 
	var flagQT=false ;
	if(psd1.length<6 || psd1.length>12){   
		divpassword1.innerHTML='<font class="tips_false">长度错误</font>';
	}else
		{   
		  for(i=0;i < psd1.length;i++)   
			{    
				if((psd1.charAt(i) >= 'A' && psd1.charAt(i)<='Z') || (psd1.charAt(i)>='a' && psd1.charAt(i)<='z')) 
				{   
					flagZM=true;
				}
				else if(psd1.charAt(i)>='0' && psd1.charAt(i)<='9')    
				{ 
					flagSZ=true;
				}else    
				{ 
					flagQT=true;
				}   
			}   
			if(!flagZM||!flagSZ||flagQT){
			divpassword1.innerHTML='<font class="tips_false">密码必须是字母数字的组合</font>'; 
			 
			}else{
				
			divpassword1.innerHTML='<font class="tips_true">输入正确</font>';
			 
			}  
		 
		}	
}
function checkpsd2(){ 
	if(form1.user_pwd.value!=form1.confirmpwd.value) { 
	     divpassword2.innerHTML='<font class="tips_false">您两次输入的密码不一样</font>';
	} else { 
	     divpassword2.innerHTML='<font class="tips_true">输入正确</font>';
	}
}
</script>

</head>

<script type="text/javascript" src="js/regandlog.js"></script>
<body>
<%@include file="header.jsp" %>
<div class="container-fluid">
				<div class="col-md-4">
					<img src="images/regandlog/DioShop.png" style="cursor: pointer" id="imgc" height="70px" width="250px" />
				</div>
				<div class="col-md-5">
					<img src="images/regandlog/header.jpg" />
				</div>
				
			</div>
				<%@include file="search.jsp" %>
			<div class="container" style="width:100%;background:url('images/regandlog/regist_bg.jpg');">
<div class="row"> 

	<div class="col-md-2"></div>
			
<div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;width: 700px;">
		<font>会员注册</font>USER REGISTER
<form name="form1" class="form-horizontal" style="margin-top:5px;">
<div class="form-group">
			    <label for="username" class="col-sm-2 control-label">用户名</label>
			    <div class="col-sm-6">
       
      <input class="form-control" name="user_name" id="user_name" onblur="checkna()" href="javascript:findname();"  placeholder="请输入用户名"><span id="divname" class="tips">长度是12个字符</span>
        </div>
			  </div>
			  <div class="form-group">
			  <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
			  <div class="col-sm-6">
			<input type="password" class="form-control"  name="user_pwd" id="user_pwd" onBlur="checkpsd1()" placeholder="请输入密码"><span class="tips" id="divpassword1">密码必须由字母和数字组成</span>
			  </div>
			  </div>
			  <div class="form-group">
			    <label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" id="confirmpwd" onBlur="checkpsd2()" placeholder="请输入确认密码"><span class="tips" id="divpassword2">两次密码需要相同</span>
			    </div>
			  </div>
		<div class="form-group">
			    <label for="date" class="col-sm-2 control-label">验证码</label>
			    <div class="col-sm-3" >
		<input id="verifyCode" name="verifyCode"   type="text" class="form-control" placeholder="验证码"   style="width:150px;">
		</div>
		<div class="col-sm-2" style="width:300px">
          <img src="imagecode" id="codeImage" name="codeImage"> <a id="kanbuq" href="javascript:change();">看不清，换一张</a> </div>
		</div>
					  <div class="form-group">
					  <div class="col-sm-offset-2 col-sm-10">
		
		<input type="button" width="100" name="metho" border="0" id="btnSearch" value="提交">
		   </div>
			  </div>
			</form>
			</div>
			<div class="col-md-2"></div>
			</div>
</div>
	<div style="margin-top:50px;">
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