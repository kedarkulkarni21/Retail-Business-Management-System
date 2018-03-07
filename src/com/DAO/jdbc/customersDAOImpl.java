package com.DAO.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.DAO.customersDAO;
import com.model.customers;

import oracle.jdbc.OracleTypes;

public class customersDAOImpl implements customersDAO{
	int select,qty,purNum;
	String eid,pid,cid,ptime,sid, sdate, telephone, name, username,operation, op_time;
	Scanner sc = new Scanner(System.in);
	/*@Override
	public List<customers> retrieveData() throws ClassNotFoundException, SQLException {
	
		String query = "select * from customers";
		Connection conn=null;
		conn = dbconnect.getDbConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		List<customers> list = new ArrayList<customers>();
		while(rs.next()) {
			String cid = rs.getString(1);
			String name = rs.getString(2);
			String telephoneNum = rs.getString(3);
			int visits_made = rs.getInt(4);
			String last_visit_date = rs.getString(4);
			customers e= new customers(cid,name,telephoneNum,visits_made,last_visit_date);
			list.add(e);
		}
		return list;
}*/

	@Override
	public void addCustomers(Connection conn) {
		
		System.out.println("Enter Customer ID (cid):");
		cid = sc.next();
		System.out.println("Enter Customer name (name)");
		name = sc.next();
		System.out.println("Enter Telephone Number (telephone#)");
	    telephone = sc.next();
		CallableStatement cs;
		try {
			cs = conn.prepareCall("{Call projj.add_customers(?,?,?)}");
			cs.setString(1, cid);
			cs.setString(2, name);
			cs.setString(3, telephone);
			cs.execute();
			showCustomers(conn);
		} catch (SQLException e) {
			System.out.println("Customer ID already exists. Please enter a unique Customer ID");
		}
		
	}

	@Override
	public void showCustomers(Connection conn) throws SQLException {
		CallableStatement cs = conn.prepareCall("begin projj.show_customers(?); end;");
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		System.out.println("CID \t \t NAME \t \t TELEPHONE \t \t VISITS_MADE \t \t LAST_VISIT_DATE");
		while(rs.next()) {
			Date lastVisitDate = rs.getDate(5);
			
			DateFormat dateformat=new SimpleDateFormat("dd-MMM-yy");
			String dateString=dateformat.format(lastVisitDate);
			System.out.println(rs.getString(1)+" \t \t "+rs.getString(2)+" \t \t "+rs.getString(3)+" \t \t "+rs.getInt(4)+" \t \t "+dateString);
		}
		
	}
}