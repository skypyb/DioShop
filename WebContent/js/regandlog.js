$(function(){
	change();
	findname();
	login();
	hand();
	handtwo();
	
	//$("#imgc").css("cursor","pointer,hand")
	$("#btnSearch").click(function(){
		//alert(123);
		var user_name=$("#user_name").val();
		var user_pwd=$("#user_pwd").val();
		var verifyCode=$("#verifyCode").val();
	      //alert(empno);
		$.ajax({
			url:"user",
			data:{"user_name":user_name,"user_pwd":user_pwd,"verifyCode":verifyCode,method:"register"},
			dataType:"json",
			success:function(data){
				//alert(data);
				//location.href="login.jsp";
				if(data.pass){
					location.href="login.jsp";
				}else{
					//layer.
					alert(data.mssg);
					location.href="register.jsp";
				}

			}
	})		
});

});

function change(){
	//alert(5678)
	
	$("#codeImage").attr("src","imagecode?rand="+Math.random());
}
//根据用户名来查找
function findname(){
	//alert(4456)
	  $("#user_name").mouseleave(function(){
		  var user_name=$("#user_name").val();
		  $.ajax({
			  url:"user",
			  data:{"user_name":user_name,method:"checkname"},
			  success:function(data){
				  
				  if(data=="true"){
					  $("#divname").text("");
					  $("#divname").css({"color":"green"});
				  }else{
					  $("#divname").text("");
					  $("#divname").css({ "color": "red"});
				  }
				 
					
				}
		  });
		  });
	}
function login(){
	//alert(7788);
	$("#btnlogin").click(function(){
		//alert(7788);
		var user_name=$.trim($("#user_name").val());
		var user_pwd=$.trim($("#user_pwd").val());
		var verifyCode=$.trim($("#verifyCode").val());
		
		if(!user_name){
			alert("账户不能为空");
			return;
		}
		if(!user_pwd){
			alert("密码不能为空");
			return;
		}
		if(!verifyCode){
			alert("验证码不能为空");
			return;
		}
		$.ajax({
			url:"user",
			data:{user_name:user_name,user_pwd:user_pwd,verifyCode:verifyCode,method:"login"},
			dataType:"json",
			type:"post",
			success:function(data){
				if(data.pass){
					
					location.href="index.jsp";
				}else{
					
					alert(data.msg);
				}
			}
		});

		
	});
	
	
}
function hand(){
	$("#imgc").click(function(){
		//alert(556)
		location.href="index.jsp";
		

			
	});
}
function handtwo(){
	$("#imgcc").click(function(){
		//alert(556)
		location.href="index.jsp";
		

			
	});
}

















