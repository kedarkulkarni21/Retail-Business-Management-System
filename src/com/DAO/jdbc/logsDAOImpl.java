package com.DAO.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.DAO.logsDAO;

import oracle.jdbc.OracleTypes;

public class logsDAOImpl implements logsDAO{
	int select,qty,purNum;
	String eid,pid,cid,ptime,sid, sdate, telephone, name, username,operation, op_time, table, tuple_pkey;
	Scanner sc = new Scanner(System.in);
	@Override
	public void showLogs(Connection conn) {
		
		try {
			CallableStatement cs;
		
			cs = conn.prepareCall("begin projj.show_logs(?); end;");
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		System.out.println("LOG# \t \t USERNAME \t \t OPERATION \t \t OP_TIME \t \t TABLE_NAME \t \t TUPLE_PKEY");
		while(rs.next()) {
			Date op_time = rs.getDate(4);	
			DateFormat dateformat=new SimpleDateFormat("dd-MMM-yy");
			String dateString=dateformat.format(op_time);
			System.out.println(rs.getInt(1)+" \t \t "+rs.getString(2)+" \t \t "+rs.getString(3)+" \t \t "+dateString+" \t \t "+rs.getString(5)+" \t \t "+rs.getString(6));
		}
		
		
	
	} catch (SQLException e) {
		System.out.println("Table is empty");
		e.printStackTrace();
	}

}

	@Override
	public void insertIntoLogs(Connection conn) {
		System.out.println("Enter the values for the following attributes");
		System.out.println("Enter username");
		username = sc.next();
		System.out.println("Enter operation performed on the database");
		operation = sc.next();
		System.out.println("Enter operation time");
		op_time = sc.next();
		System.out.println("Enter table name");
		table = sc.next();
		System.out.println("Enter tuple's primary key");
		tuple_pkey = sc.next();
		PreparedStatement pst;
		try {
		pst = conn.prepareStatement("insert into logs values(?,?,?,to_date(?,'DD-MON-YYYY'),?,?)");
		
		String sql = "select logs_seq.NEXTVAL from dual";		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		int logsNum = rs.getInt(1);
		pst.setInt(1, logsNum);
		pst.setString(2, username);
		pst.setString(3, operation);
		pst.setString(4,op_time);
		pst.setString(5, table);
		pst.setString(6, tuple_pkey);
		pst.execute();
		showLogs(conn);
	} catch(SQLDataException e) {
		String errcode = "01858";
		long errcode1 = Long.parseLong(errcode);
		if(e.getErrorCode() == errcode1)
		System.out.println(" A non-numeric character was found where a numeric was expected");
	}
		catch (SQLException e) {
		e.printStackTrace();
	}
	}
}
