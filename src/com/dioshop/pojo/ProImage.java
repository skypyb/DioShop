package com.dioshop.pojo;

import java.io.Serializable;

public class ProImage implements Serializable{//商品图片
	private int img_id;
	private int pro_id;
	private int img_type;
	private String img_src;
	public ProImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProImage(int img_id, int pro_id, int img_type, String img_src) {
		super();
		this.img_id = img_id;
		this.pro_id = pro_id;
		this.img_type = img_type;
		this.img_src = img_src;
	}
	public int getImg_id() {
		return img_id;
	}
	public void setImg_id(int img_id) {
		this.img_id = img_id;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public int getImg_type() {
		return img_type;
	}
	public void setImg_type(int img_type) {
		this.img_type = img_type;
	}
	public String getImg_src() {
		return img_src;
	}
	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}
	@Override
	public String toString() {
		return "ProImage [img_id=" + img_id + ", pro_id=" + pro_id + ", img_type=" + img_type + ", img_src=" + img_src
				+ "]";
	}
	
	
}
