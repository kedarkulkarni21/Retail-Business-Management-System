package com.DAO.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.DAO.suppliesDAO;
import com.model.purchases;
import com.model.supplies;

import oracle.jdbc.OracleTypes;

public class suppliesDAOImpl implements suppliesDAO{

	@Override
	public void insertIntoSupplies(Connection conn) throws SQLException, ClassNotFoundException {
		int select,qty,purNum;
		String eid,pid,cid,ptime,sid, sdate, telephone, name, username,operation, op_time;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the values for the following attributes");
		System.out.println("Enter Product ID (pid):");
		pid = sc.next();
		System.out.println("Enter Supply ID (sid):");
		sid = sc.next();
		System.out.println("Enter Supply date (sdate), use format (dd-MON-yy):");
		sdate = sc.next();
		System.out.println("Enter Quantity (qty):");
		qty = sc.nextInt();
		
		PreparedStatement pst = conn.prepareStatement("insert into supplies values(?,?,?,to_date(?,'DD-MON-YYYY'),?)");
		String sql = "select supplies_seq.NEXTVAL from dual";
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		int supNum = rs.getInt(1);
		
		pst.setInt(1, supNum);
		pst.setString(2, pid);
		pst.setString(3, sid);
		pst.setString(4, sdate);
		pst.setInt(5, qty);
		
		int i = pst.executeUpdate();
		 if(i!=0){
		        System.out.println("Record has been inserted");
		        showSupplies(conn);
		      }
		      else{
		        System.out.println("failed to insert the data");
		      }
		
	
	
		
	}

	@Override
	public List<supplies> retrieveData() throws ClassNotFoundException, SQLException {
		String query = "select * from supplies order by sup#";
		Connection conn=null;
		conn = dbconnect.getDbConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		List<supplies> list = new ArrayList<supplies>();
		while(rs.next()) {
			int supNum = rs.getInt(1);
			String pid = rs.getString(2);
			String sid = rs.getString(3);
			String sdate = rs.getString(4);
			int qty = rs.getInt(5);
			
			supplies s= new supplies(supNum,pid,sid,sdate,qty);
			list.add(s);
		}
		return list;
	}

	@Override
	public void showSupplies(Connection conn) throws SQLException {
		CallableStatement cs = conn.prepareCall("begin projj.show_supplies(?); end;");
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		while(rs.next()) {
			Date sdate = rs.getDate(4);
			
			DateFormat dateformat=new SimpleDateFormat("dd-MMM-yy");
			String dateString=dateformat.format(sdate);
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+dateString+"\t"+rs.getInt(5));
		}
		
		
	}

}
