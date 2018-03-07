package com.model;

public class products {
	String pid;
	String name;
	int qoh;
	int qoh_threshold;
	double original_price;
	int discnt_category;
	
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQoh() {
		return qoh;
	}
	public void setQoh(int qoh) {
		this.qoh = qoh;
	}
	public int getQoh_threshold() {
		return qoh_threshold;
	}
	public void setQoh_threshold(int qoh_threshold) {
		this.qoh_threshold = qoh_threshold;
	}
	public double getOriginal_price() {
		return original_price;
	}
	public void setOriginal_price(double original_price) {
		this.original_price = original_price;
	}
	public int getDiscnt_category() {
		return discnt_category;
	}
	public void setDiscnt_category(int discnt_category) {
		this.discnt_category = discnt_category;
	}
	public products(String pid, String name, int qoh, int qoh_threshold, double original_price, int discnt_category) {
		super();
		this.pid = pid;
		this.name = name;
		this.qoh = qoh;
		this.qoh_threshold = qoh_threshold;
		this.original_price = original_price;
		this.discnt_category = discnt_category;
	}
	public products() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


}

