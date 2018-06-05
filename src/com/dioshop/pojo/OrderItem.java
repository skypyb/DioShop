package com.dioshop.pojo;

import java.io.Serializable;

public class OrderItem implements Serializable{//订单项
	private int itme_id;
	private int user_id;//用户表主键，对应某个用户
	private int pro_id;//商品表主键，对应某个商品
	private Integer order_id;//订单表主键，对应某个订单
	private int quantity;
	private int pros_id;//规格表主页，对应某个规格
	
	
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}


	public OrderItem(int itme_id, int user_id, int pro_id, Integer order_id, int pros_id, int quantity) {
		super();
		this.itme_id = itme_id;
		this.user_id = user_id;
		this.pro_id = pro_id;
		this.order_id = order_id;
		this.pros_id = pros_id;
		this.quantity = quantity;
	}


	public int getItme_id() {
		return itme_id;
	}


	public void setItme_id(int itme_id) {
		this.itme_id = itme_id;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public int getPro_id() {
		return pro_id;
	}


	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}


	public Integer getOrder_id() {
		return order_id;
	}


	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}


	public int getPros_id() {
		return pros_id;
	}


	public void setPros_id(int pros_id) {
		this.pros_id = pros_id;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Override
	public String toString() {
		return "ShopOrderItem [itme_id=" + itme_id + ", user_id=" + user_id + ", pro_id=" + pro_id + ", order_id="
				+ order_id + ", pros_id=" + pros_id + ", quantity=" + quantity + "]";
	}
	
	
}
