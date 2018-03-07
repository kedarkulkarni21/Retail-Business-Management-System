package com.model;

public class suppliers {
	String sid;
	String name;
	String city;
	String telephoneNum;
	String email;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public suppliers(String sid, String name, String city, String telephoneNum, String email) {
		super();
		this.sid = sid;
		this.name = name;
		this.city = city;
		this.telephoneNum = telephoneNum;
		this.email = email;
	}
	
	
}
