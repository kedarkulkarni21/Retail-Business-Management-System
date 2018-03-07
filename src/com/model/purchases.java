package com.model;

public class purchases {
	int purNum;
	String eid;
	String pid;
	String cid;
	int qty;
	String date;
	double total_price;
	
	
	public purchases(String eid, String pid, String cid, int qty, String date, double total_price) {
		super();
		this.eid = eid;
		this.pid = pid;
		this.cid = cid;
		this.qty = qty;
		this.date = date;
		this.total_price = total_price;
	}
	public int getPurNum() {
		return purNum;
	}
	public void setPurNum(int purNum) {
		this.purNum = purNum;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public double getTotal_price() {
		return total_price;
	}
	public void setTotal_price(double total_price) {
		this.total_price = total_price;
	}
	public purchases(int purNum, String eid, String pid, String cid, int qty, String date, double total_price) {
		super();
		this.purNum = purNum;
		this.eid = eid;
		this.pid = pid;
		this.cid = cid;
		this.qty = qty;
		this.date = date;
		this.total_price = total_price;
	}
	

}
