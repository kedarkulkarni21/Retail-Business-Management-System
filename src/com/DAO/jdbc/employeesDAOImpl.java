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

import com.DAO.employeesDAO;
import com.model.employees;

import oracle.jdbc.OracleTypes;

public class employeesDAOImpl implements employeesDAO{
	int select,qty,purNum;
	String eid,pid,cid,ptime,sid, sdate, telephone, name, username,operation, op_time;
	Scanner sc = new Scanner(System.in);
	@Override
	public List<employees> retrieveData() throws ClassNotFoundException, SQLException {
		String query = "select * from employees";
		Connection conn=null;
		conn = dbconnect.getDbConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		List<employees> list = new ArrayList<employees>();
		while(rs.next()) {
			String eid=rs.getString(1);
			String name = rs.getString(2);
			String telephoneNum = rs.getString(3);
			String email = rs.getString(4);
			employees e= new employees(eid,name,telephoneNum,email);
			list.add(e);
			
		}
		
		return list;
	
		
	}

	@Override
	public void monthlySaleActivities(Connection conn) throws SQLException {
		try {
			System.out.println("Enter Employee ID (eid):");
			eid = sc.next();
		CallableStatement cs = conn.prepareCall("begin projj.monthly_sale_activities(?,?); end;");
		cs.registerOutParameter(2, OracleTypes.CURSOR);
		cs.setString(1, eid);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(2);
		System.out.println("EID \t \t NAME \t \t MONTH \t \t YEAR \t \t SALE \t \t QUANTITY \t \t TOTALPRICE");
		while(rs.next()) {
			
			System.out.println(rs.getString(1)+" \t \t "+rs.getString(2)+" \t \t "+rs.getString(3)+" \t \t "+rs.getString(4)+" \t \t "+rs.getInt(5)+" \t \t "+rs.getDouble(6)+" \t \t \t"+rs.getDouble(7));
		}
	} catch (SQLException e) {
		System.out.println("Employee ID does not exist. Please enter a valid Employee ID");
	}
	}

	@Override
	public void showEmployees(Connection conn) throws SQLException {
		CallableStatement cs = conn.prepareCall("begin projj.show_employees(?); end;");
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		System.out.println("EID \t \t \t NAME \t \t \t TELEPHONE \t \t \t EMAIL");
		while(rs.next()) {
			
			System.out.println(rs.getString(1)+" \t \t \t "+rs.getString(2)+" \t \t \t "+rs.getString(3)+" \t \t \t "+rs.getString(4));
		}
		
		
	}

}
