package com.dioshop.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dioshop.dao.OrderItemDao;
import com.dioshop.dao.ProductDao;

public class Cart {
	//购物车中有N个购物项
	private Map<Integer,CartItem> cartItems = new HashMap<>();
	private int user_id;
	//购物车商品的总计
	private double total;
	public Cart() {
		super();
	}
	
	/**
	 * 初始化方法，通过用户ID查找当前用户的所有购物项
	 * @param user_id
	 */
	public void init(int user_id) {
		this.user_id=user_id;
		ProductDao dao = new ProductDao();
		List<OrderItem> list = new OrderItemDao().findByUserId(user_id);
		for (OrderItem item : list) {
			//通过规格Id查找到一个Prospe对象
			Prospe prospe = dao.findProspeSingle(item.getPros_id());
			//用当前商品ID查询出当前购买的是那一件商品
			Product product = dao.findSingleProduct(item.getPro_id()+"");
			//求出商品的小计
			double subtotal = product.getPro_price()*item.getQuantity();
			String produceSrc = dao.findProductImages(product.getPro_id());
			//封装数据
			CartItem items = new CartItem();
			items.setOrderItem_id(item.getItme_id());
			items.setProduct(product);
			items.setProspe(prospe);
			items.setBuyNum(item.getQuantity());
			items.setProImageSrc(produceSrc);
			items.setSubtotal(subtotal);
			this.total+=subtotal;
			this.cartItems.put(prospe.getPros_id(), items);
		}
		
		
	}

	public Cart(Map<Integer, CartItem> cartItems, double total) {
		super();
		this.cartItems = cartItems;
		this.total = total;
	}

	public Map<Integer, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<Integer, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Cart [cartItems=" + cartItems + ", total=" + total + "]";
	}
	
	
	
	
	
	
}
