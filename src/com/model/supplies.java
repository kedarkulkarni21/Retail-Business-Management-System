package com.model;

public class supplies {
	int supNum;
	String pid;
	String sid;
	String sdate;
	int quantity;
	
	
	
	public supplies(String pid, String sid, String sdate, int quantity) {
		super();
		this.pid = pid;
		this.sid = sid;
		this.sdate = sdate;
		this.quantity = quantity;
	}
	public int getSupNum() {
		return supNum;
	}
	public void setSupNum(int supNum) {
		this.supNum = supNum;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public supplies(int supNum, String pid, String sid, String sdate, int quantity) {
		super();
		this.supNum = supNum;
		this.pid = pid;
		this.sid = sid;
		this.sdate = sdate;
		this.quantity = quantity;
	}
	
	

}
