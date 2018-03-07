package com.model;

public class customers {
	String cid;
	String name;
	String telephoneNum;
	int visits_made;
	String last_visit_date;
	
	
	public customers(String cid, String name, String telephoneNum, int visits_made, String last_visit_date) {
		super();
		this.cid = cid;
		this.name = name;
		this.telephoneNum = telephoneNum;
		this.visits_made = visits_made;
		this.last_visit_date = last_visit_date;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
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
	public int getVisits_made() {
		return visits_made;
	}
	public void setVisits_made(int visits_made) {
		this.visits_made = visits_made;
	}
	public String getLast_visit_date() {
		return last_visit_date;
	}
	public void setLast_visit_date(String last_visit_date) {
		this.last_visit_date = last_visit_date;
	}
	
	

}
