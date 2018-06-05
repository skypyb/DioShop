package com.dioshop.pojo;

import java.io.Serializable;

//具体商品表
public class Product implements Serializable{
	private int pro_id;//主键
	private int prot_id;//大类型主键
	private String pro_type;//具体类型名
	private String pro_name;//商品名
	private String pro_brand;//品牌
	private String pro_sex;//男|女
	private String pro_uad;//上装|下装
	private Double pro_price;//单价
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Product(int pro_id, int prot_id, String pro_type, String pro_name, String pro_brand, String pro_sex,
			String pro_uad, Double pro_price) {
		super();
		this.pro_id = pro_id;
		this.prot_id = prot_id;
		this.pro_type = pro_type;
		this.pro_name = pro_name;
		this.pro_brand = pro_brand;
		this.pro_sex = pro_sex;
		this.pro_uad = pro_uad;
		this.pro_price = pro_price;
	}

	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public int getProt_id() {
		return prot_id;
	}
	public void setProt_id(int prot_id) {
		this.prot_id = prot_id;
	}
	public String getPro_type() {
		return pro_type;
	}
	public void setPro_type(String pro_type) {
		this.pro_type = pro_type;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	
	public String getPro_brand() {
		return pro_brand;
	}
	public void setPro_brand(String pro_brand) {
		this.pro_brand = pro_brand;
	}
	
	public String getPro_sex() {
		return pro_sex;
	}
	public void setPro_sex(String pro_sex) {
		this.pro_sex = pro_sex;
	}
	public String getPro_uad() {
		return pro_uad;
	}
	public void setPro_uad(String pro_uad) {
		this.pro_uad = pro_uad;
	}
	
	public Double price() {
		return pro_price;
	}
	public void setPro_price(Double pro_price) {
		this.pro_price = pro_price;
	}

	public Double getPro_price() {
		return pro_price;
	}

	@Override
	public String toString() {
		return "ProductServlets [pro_id=" + pro_id + ", prot_id=" + prot_id + ", pro_type=" + pro_type + ", pro_name="
				+ pro_name + ", pro_brand=" + pro_brand + ", pro_sex=" + pro_sex + ", pro_uad=" + pro_uad
				+ ", pro_price=" + pro_price + "]";
	}
	
	
	
	
	
}
