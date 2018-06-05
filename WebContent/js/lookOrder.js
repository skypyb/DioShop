
		$(function(){
			
			$(".btn-immPay").click(function(){
				location.href="cart/orderPay.jsp?order_id="+$(this).val()+
				  "&priceSum="+$(this).parent().children(".sumPrice").val()
			})
			$(".btn-1").click(function(){
				var t1 = $(this).val();
				layer.confirm("确认要催卖家发货吗?",function(){
					layer.msg("卖家已经秒发!");
					setTimeout(function(){
						location.href="cart/orderFlow?action=delivery&order_id="+t1;
					},1500);
				});
				
			})
			$(".btn-2").click(function(){
				var t1 = $(this).val();
				layer.confirm("确认要收货吗?谨防商家恶意诈骗，否则您可能钱货两空!",function(){
					location.href="cart/orderFlow?action=confirm&order_id="+t1;
				});
			})
			$(".btn-3").click(function(){
				var t1 = $(this).val();
				layer.confirm("确定要进行评价吗？评价后则无法进行退款操作!",function(){
					location.href="cart/reviewServlet?order_id="+t1;
				});
			})
			
			$(".btn-4").click(function(){
				var t1 = $(this).val();
				layer.confirm("确认要催卖家退款吗？",function(){
					layer.msg("卖家已秒退!钱已汇入您的账户");
					setTimeout(function(){
						location.href="cart/orderFlow?action=removeOrderItem&order_id="+t1;
					},1500);
				});
			})
			
			
			
			
			$("#orderType div a").click(function(){
				$(this).parent().siblings().css("border-color","#cccccc");
				$(this).parent().css("border-color","#b81b22");
				
				var id = $(this).attr("id");
				if(id=="all"){
					$(".order").css("display","table");
				}
			})
			$(".orderTitle a").click(function(){
				var id = $(this).attr("id");
				layer.confirm("确认要删除该条订单？",function(){
					layer.msg("订单已经删除！");
					setTimeout(function(){
						location.href="cart/orderFlow?action=removeOrderItem&order_id="+id;
					},1500);
					
				})
			})
			
		})
		
		
		function refund(order_id){
			layer.confirm("确认要退款吗？",function(){
				layer.msg("已处于申请退款状态");
				setTimeout(function(){
					location.href="cart/orderFlow?action=refund&order_id="+order_id;
				},1500);
				
			})
		}
		
		/**
		 * 这下面是选项菜单的特效
		 * @returns
		 */
		function status0(){
			if($(".flag[value=0]").val()==undefined){
				$(".order").css("display","none");
				return;
			}
			$(".flag[value=0]").parent().siblings().css("display","none").end().css("display","table")
		}
		function status1(){
			if($(".flag[value=1]").val()==undefined){
				$(".order").css("display","none");
				return;
			}
			$(".flag[value=1]").parent().siblings().css("display","none").end().css("display","table")
		}
		function status2(){
			if($(".flag[value=2]").val()==undefined){
				$(".order").css("display","none");
				return;
			}
			$(".flag[value=2]").parent().siblings().css("display","none").end().css("display","table")
		}
		function status3(){
			if($(".flag[value=3]").val()==undefined){
				$(".order").css("display","none");
				return;
			}
			$(".flag[value=3]").parent().siblings().css("display","none").end().css("display","table")
		}
		