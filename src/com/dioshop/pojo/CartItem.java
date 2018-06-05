package com.dioshop.pojo;

public class CartItem {
	private Prospe prospe;//规格
	private Product product;//商品
	private String proImageSrc;//图片地址
	private int buyNum;//用户购买的商品数量
	private double subtotal;//小计
	private int orderItem_id;
	public CartItem() {
		super();
	}
	public CartItem(Prospe prospe, Product product, String proImageSrc, int buyNum, double subtotal, int orderItem_id) {
		super();
		this.prospe = prospe;
		this.product = product;
		this.proImageSrc = proImageSrc;
		this.buyNum = buyNum;
		this.subtotal = subtotal;
		this.orderItem_id = orderItem_id;
	}
	public Prospe getProspe() {
		return prospe;
	}
	public void setProspe(Prospe prospe) {
		this.prospe = prospe;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getProImageSrc() {
		return proImageSrc;
	}
	public void setProImageSrc(String proImageSrc) {
		this.proImageSrc = proImageSrc;
	}
	public int getBuyNum() {
		return buyNum;
	}
	public void setBuyNum(int buyNum) {
		this.buyNum = buyNum;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public int getOrderItem_id() {
		return orderItem_id;
	}
	public void setOrderItem_id(int orderItem_id) {
		this.orderItem_id = orderItem_id;
	}
	@Override
	public String toString() {
		return "CartItem [prospe=" + prospe + ", ProductServlets=" + product + ", proImageSrc=" + proImageSrc + ", buyNum="
				+ buyNum + ", subtotal=" + subtotal + ", orderItem_id=" + orderItem_id + "]";
	}

	

}
