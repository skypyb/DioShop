package com.dioshop.pojo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Protype implements Serializable{//商品大类型
	private Integer prot_id;
	private String prot_name;
	public Protype() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Protype(int prot_id, String prot_name) {
		super();
		this.prot_id = prot_id;
		this.prot_name = prot_name;
	}
	public int getProt_id() {
		return prot_id;
	}
	public void setProt_id(int prot_id) {
		this.prot_id = prot_id;
	}
	public void setProt_id(BigDecimal prot_id) {
		this.prot_id = prot_id.intValue();
	}
	public String getProt_name() {
		return prot_name;
	}
	public void setProt_name(String prot_name) {
		this.prot_name = prot_name;
	}
	@Override
	public String toString() {
		return "Protype [prot_id=" + prot_id + ", prot_name=" + prot_name + "]";
	}
	
	
	
	
}
