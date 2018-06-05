package com.dioshop.web.listener;

import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.dioshop.dao.OrderItemDao;
import com.dioshop.pojo.Cart;
import com.dioshop.pojo.OrderItem;
import com.dioshop.pojo.User;
import com.dioshop.util.OrderItemSave;


@WebListener
public class DioShopListener implements HttpSessionAttributeListener, HttpSessionListener {

	//private CartDao cd = new CartDao();
	
	// 在session中添加对象时触发此操作，就是调用setAttribute这个方法时候会触发的
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		
		if (se.getName().equals("user")) {
			System.out.println("用户登陆了~");
			User u = (User) se.getValue();
			Cart cart = new Cart();
			cart.init(u.getUser_id());
			se.getSession().setAttribute("cart", cart);
		}

	}

	// 删除session中对象时触发此操作，就是调用 removeAttribute这个方法时候会触发的
	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		System.out.println("这是removeAttribute时启动的");

		if (se.getName() != "user" || se.getValue() == null) {
			return;
		}
		if(se.getSession() == null) {
			return;
		}
		try {
			if(se.getSession().getAttribute("cart")==null) {
				return;
			}
		}catch (Exception e) {}
		
		
		

		User u = (User) se.getValue();
		// session中的新数据
		Cart cart = (Cart) se.getSession().getAttribute("cart");
		List<OrderItem> list = OrderItemSave.cartToOrderItem(cart);
		
		if(list.size() == 0) {
			new OrderItemDao().remove(u.getUser_id());
		}else {
			OrderItemSave.saveOrderItem(list);
		}

	}
	
	// session销毁时触发
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("这是session自动销毁时启动的");

		if (se.getSession().getAttribute("user") == null) {
			return;
		}
		User u = (User) se.getSession().getAttribute("user");
		// session中的新数据
		Cart cart = (Cart) se.getSession().getAttribute("cart");

		List<OrderItem> list = OrderItemSave.cartToOrderItem(cart);
		
		if(list.size() == 0) {
			new OrderItemDao().remove(u.getUser_id());
		}else {
			OrderItemSave.saveOrderItem(list);
		}

	}

}
