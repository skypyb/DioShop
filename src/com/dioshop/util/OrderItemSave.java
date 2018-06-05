package com.dioshop.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dioshop.dao.OrderItemDao;
import com.dioshop.pojo.Cart;
import com.dioshop.pojo.CartItem;
import com.dioshop.pojo.OrderItem;

public class OrderItemSave {
	private static OrderItemDao dao = new OrderItemDao();
	
	/**
	 * 传一个cart，将他转成OrderItem集合
	 * @param cart
	 * @return
	 */
	public static List<OrderItem> cartToOrderItem(Cart cart){
		List<OrderItem> item = new ArrayList<>();
		Map<Integer, CartItem> cartItems = cart.getCartItems();
		
		for (Integer  k : cartItems.keySet()) {
			CartItem cartItem = cartItems.get(k);
			item.add(new OrderItem(cartItem.getOrderItem_id(), cart.getUser_id(),
					cartItem.getProduct().getPro_id(), null, cartItem.getProspe().getPros_id(),
					cartItem.getBuyNum())); 
		}
		return item;
		
	} 
	
	
	
	
	public static void saveOrderItem(List<OrderItem> sessionList) {
		
		System.out.println("正在往数据库保存订单项数据");
		List<OrderItem> tableList = dao.findByUserId(sessionList.get(0).getUser_id());
		for(OrderItem table : tableList ) {
			boolean flag=true;
			int tabPro_id = table.getPro_id();
			int tabPros_id = table.getPros_id();
			
			for(OrderItem session : sessionList ) {
				int sessionPro_id = session.getPro_id();
				int sessionPros_id = session.getPros_id();
				
				if(tabPro_id==sessionPro_id&&tabPros_id==sessionPros_id) {
					flag = false;
				}
			}
			if(flag) {
				int itme_id = table.getItme_id();
				dao.deleteCartItem(itme_id+"");
			}
		}
		
		for(OrderItem session : sessionList) {
			boolean flag=true;
			int sessionPro_id = session.getPro_id();
			int sessionPros_id = session.getPros_id();
			for(OrderItem table : tableList) {
				int tabPro_id = table.getPro_id();
				int tabPros_id = table.getPros_id();
				
				if(tabPro_id==sessionPro_id&&tabPros_id==sessionPros_id) {
					flag=false;
					int sessionQuen = session.getQuantity();
					int tableQuen = table.getQuantity();
					if(sessionQuen==tableQuen) {
						continue;
					}
					dao.modifCartItem(session);
				}
			}
			if(flag) {
				dao.addCartItem(session);
			}
			
			
		}
		
		
		
		
		
	}
	

}
