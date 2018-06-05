package com.dioshop.bean;

import java.io.Serializable;
import java.math.BigDecimal;

import com.dioshop.dao.ProImageDao;
import com.dioshop.dao.ProductDao;
import com.dioshop.dao.ProspeDao;
import com.dioshop.pojo.OrderItem;
import com.dioshop.pojo.Product;
import com.dioshop.pojo.Prospe;

public class OrderBean implements Serializable{
	/*
	 * 需求，通过这一个类显示出订单项的数量、商品信息、规格信息
	 */
	
	private Prospe prospe;//规格信息
	private Product product;//商品信息
	private String img_src;//图片地址
	private Integer itme_id;//订单项编号
	private int quantity;//数量
	private double price;//总价
	
	
	public OrderBean(OrderItem oi) {
		itme_id=oi.getItme_id();
		quantity = oi.getQuantity();
		prospe = new ProspeDao().findSingleProspe(oi.getPros_id()+"");
		product = new ProductDao().findSingleProduct(oi.getPro_id()+"");
		img_src = "images/productShow/"+new ProImageDao().findSingleImg(product.getPro_id());
		priceUpdata();
	}
	
	//更新总价
	private void priceUpdata() {
		if(price>0) {
			price=0;
		}
		BigDecimal b = new BigDecimal(quantity*product.getPro_price());  
		price = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();  
	}
	
	
	public Integer getItme_id() {
		return itme_id;
	}

	

	public String getImg_src() {
		return img_src;
	}

	public Prospe getProspe() {
		return prospe;
	}

	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public double getPrice() {
		return price;
	}
	
	
	
	
}
