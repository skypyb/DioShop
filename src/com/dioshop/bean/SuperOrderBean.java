package com.dioshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.dioshop.dao.OrderItemDao;
import com.dioshop.pojo.Order;
import com.dioshop.pojo.OrderItem;

public class SuperOrderBean implements Serializable{
	
	private List<OrderBean> orderBeans = new ArrayList<>();//订单bean的集合，即该订单下所有的订单项信息
	private Order order;
	private double orderPrice;
	
	public SuperOrderBean(Order order) {
		this.order = order;
		
		OrderItemDao oid = new OrderItemDao();
		List<OrderItem> otderItems = oid.findByOrderId(order.getOrder_id());
		
		for(OrderItem oi:otderItems) {
			OrderBean ob = new OrderBean(oi);
			orderBeans.add(ob);
			orderPrice+=ob.getPrice();
		}
		BigDecimal b = new BigDecimal(orderPrice);  
		orderPrice = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
	}
	
	public double getOrderPrice() {
		return orderPrice;
	}

	public List<OrderBean> getOrderBeans() {
		return orderBeans;
	}

	public Order getOrder() {
		return order;
	}
	
	
}
