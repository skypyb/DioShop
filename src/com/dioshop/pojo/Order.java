package com.dioshop.pojo;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{//订单表
	private int order_id;//主键值
	private long orderCode;//订单号
	private String address;//收货地址
	private String receiver;//收货人信息
	private String mobile;//手机号码
	private String userMessage;//备注
	private Date createDate;//订单创建日期
	private Date payDate;//支付日期
	private Date deliveryDate;//发货日期
	private Date confirmDate;//确认收货日期
	private int status;//订单状态
	private int user_id;//代表是哪个用户的订单
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(int order_id, long orderCode, String address, String receiver, String mobile, String userMessage,
			Date createDate, Date payDate, Date deliveryDate, Date confirmDate, int status, int user_id) {
		super();
		this.order_id = order_id;
		this.orderCode = orderCode;
		this.address = address;
		this.receiver = receiver;
		this.mobile = mobile;
		this.userMessage = userMessage;
		this.createDate = createDate;
		this.payDate = payDate;
		this.deliveryDate = deliveryDate;
		this.confirmDate = confirmDate;
		this.status = status;
		this.user_id = user_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public long getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(long orderCode) {
		this.orderCode = orderCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserMessage() {
		return userMessage;
	}
	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "Order [order_id=" + order_id + ", orderCode=" + orderCode + ", address=" + address + ", receiver="
				+ receiver + ", mobile=" + mobile + ", userMessage=" + userMessage + ", createDate=" + createDate
				+ ", payDate=" + payDate + ", deliveryDate=" + deliveryDate + ", confirmDate=" + confirmDate
				+ ", status=" + status + ", user_id=" + user_id + "]";
	}
	
}


