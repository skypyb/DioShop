package com.dioshop.pojo;

import java.io.Serializable;

public class Prospe implements Serializable{//商品规格表
	private int pros_id;
	private int pro_id;//商品id(shop_product表)
	private String pro_size;//尺码
	private String pro_color;//颜色
	private String pro_inv;//库存
	public Prospe() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Prospe(int pros_id, int pro_id, String pro_size, String pro_color, String pro_inv) {
		super();
		this.pros_id = pros_id;
		this.pro_id = pro_id;
		this.pro_size = pro_size;
		this.pro_color = pro_color;
		this.pro_inv = pro_inv;
	}
	public int getPros_id() {
		return pros_id;
	}
	public void setPros_id(int pros_id) {
		this.pros_id = pros_id;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public String getPro_size() {
		return pro_size;
	}
	public void setPro_size(String pro_size) {
		this.pro_size = pro_size;
	}
	public String getPro_color() {
		return pro_color;
	}
	public void setPro_color(String pro_color) {
		this.pro_color = pro_color;
	}
	public String getPro_inv() {
		return pro_inv;
	}
	public void setPro_inv(String pro_inv) {
		this.pro_inv = pro_inv;
	}
	@Override
	public String toString() {
		return "Prospe [pros_id=" + pros_id + ", pro_id=" + pro_id + ", pro_size=" + pro_size + ", pro_color="
				+ pro_color + ", pro_inv=" + pro_inv + "]";
	}
	
	
	
}
