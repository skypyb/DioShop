
$(function(){
	
	menuInit();
	menuSlide();
	
	protShowInit();
	
	
	
})


//商品分类显示初始化
function protShowInit(){
	$.ajax({
		url:"indexServlet",
		data:{action:"protShowInit"},
		dataType:"json",
		success:function(data){
			//回调数据为{protShow:jo1,proImages:jo2}
			//jo1是{"大类型名":[{具体商品1},{具体商品2}...],"大类型名":[{具体商品1},{具体商品2}...]}
			//jo2是{"商品主键":[{此商品图片1},{此商品图片2}...],"商品主键":[{此商品图片1},{此商品图片2}...]}
			var jo1 = data["protShow"];
			var jo2 = data["proImages"];
			protShowHandler(jo1,jo2);
		}
	})
}

//分类查询处理器
function protShowHandler(protShow,proImages){
	for(var k in protShow){
		var $type = $("<div id='type'></div>")//最外层div
		var $typeName = $("<div class='col-xs-12'><span class='typeName'>&nbsp;&nbsp;&nbsp;&nbsp;"+k+"</span></div>")
		var $div = $("<div></div>");//包含此类型前五个商品的div
		
		$.each(protShow[k],function(index,value){//此时value是从集合中遍历出来的商品对象
			
			var $product = $("<div class='product' id='"+value.pro_id+"'></div>");//存放单个商品的div
			
			var $img;
			if(proImages[value.pro_id].length==0){//如果遍历的该商品没有图片，则使用默认图片，有图片选择第一张图片显示
				$img = $("<img src='images/暂无图片.png'>")
			}else{
				$img = $("<img src='images/productShow/"+proImages[value.pro_id][0].img_src+"'>")
			}
			
			//将图片、商品信息、价格，放到$product这个div中
			$product.append($img); 
			$product.append($("<p style='font-size:15px;'>"+value.pro_type+k+"     "+value.pro_name+'    '+value.pro_brand+'    '+value.pro_sex+'    '+value.pro_uad+"</p>"));
			$product.append($("<p id='price'>¥"+value.pro_price+"</p>"));
			
			$div.append($product);//将$product这个商品div放入$div这个外层div
			
			if(index==4){//遍历完第五个，直接跳出
				return false;
			}
		})
		
		//将分类头和此分类的前商品放入$type这个div
		$type.append($typeName);
		$type.append($div);
		
		//将type这个div放入已存在的#div4中，并new一个用来清除浮动的空div放进去
		$("#div4").append($type);
		$("#div4").append($("<div style='clear: both;'></div>"));
	}
	
	productClick();
}

function productClick(){
	
	$(".product").click(function(){
		location.href="product?param=findSingleProduct&pid="+$(this).attr("id");
	})
}

//菜单初始化
function menuInit(){
	$.ajax({
		url:"indexServlet",
		data:{action:"menuInit"},
		dataType:"json",
		success:function(menuBean){//接受的参数为Map<String, List<String>>的JSON表现形式
			$("#div2Ul li:gt(0) >a").each(function(){//获取导航栏大类型a标签，并遍历
				
				var array = menuBean[$(this).text()];//将大类型a标签的文本内容作为参数，获取对应的集合(表现形式为数组)
				
				var $div = $("<div class='menu2'></div>");//建立一个div节点
				
				//遍历之前获取的array数组
				$.each(array,function(index,value){
					//创建准备放入div的p标签，和放入p标签的a标签
				     var $p = $("<p></p>");
				     var $a = $("<a></a>");
				     
				     $a.text(value);
				     $a.attr("href","#");
				     
				     $p.append($a);
				     $div.append($p);
				});
				//将节点创建好之后，通过parent()获取当前遍历a标签的父元素li标签，塞进这个li标签
				$(this).parent().append($div);
			})
		}
		
	})
}

//导航栏滑动特效绑定
function menuSlide(){
	$("#div2Ul > li").hover(
		function(){
			$(this).children("div").stop(true, false).slideDown("30");
		},
		function(){
			$(this).children("div").stop(true, false).slideUp("30");
		}
	)
}



