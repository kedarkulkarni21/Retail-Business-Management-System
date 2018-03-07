package com.model;

public class logs {
	int logNum;
	String username;
	String operation;
	String op_time;
	String table_name;
	String tuple_pkey;
	public logs(int logNum, String username, String operation, String op_time, String table_name, String tuple_pkey) {
		super();
		this.logNum = logNum;
		this.username = username;
		this.operation = operation;
		this.op_time = op_time;
		this.table_name = table_name;
		this.tuple_pkey = tuple_pkey;
	}
	public int getLogNum() {
		return logNum;
	}
	public void setLogNum(int logNum) {
		this.logNum = logNum;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getOp_time() {
		return op_time;
	}
	public void setOp_time(String op_time) {
		this.op_time = op_time;
	}
	public String getTable_name() {
		return table_name;
	}
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	public String getTuple_pkey() {
		return tuple_pkey;
	}
	public void setTuple_pkey(String tuple_pkey) {
		this.tuple_pkey = tuple_pkey;
	}
	
	

}
