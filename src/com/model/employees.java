package com.model;

public class employees {
	String eid;
	String name;
	String telephoneNum;
	String email;
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephoneNum() {
		return telephoneNum;
	}
	public void setTelephoneNum(String telephoneNum) {
		this.telephoneNum = telephoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public employees(String eid, String name, String telephoneNum, String email) {
		super();
		this.eid = eid;
		this.name = name;
		this.telephoneNum = telephoneNum;
		this.email = email;
	}
	
	

}
