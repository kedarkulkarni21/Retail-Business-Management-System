package com.DAO.jdbc;

import java.sql.*;

import java.util.*;
import com.DAO.productsDAO;
import com.DAO.jdbc.*;
import com.model.*;

import oracle.jdbc.OracleTypes;

public class productsDAOImpl implements productsDAO {

/*	@Override
	public List<products> retrieveData() throws ClassNotFoundException, SQLException {
		String query = "select * from products";
		Connection conn=null;
		conn = dbconnect.getDbConnection();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		List<products> list = new ArrayList<products>();
		while(rs.next()) {
			String pid=rs.getString(1);
			String name = rs.getString(2);
			int qoh = rs.getInt(3);
			int qoh_threshold = rs.getInt(4);
			int original_price = rs.getInt(5);
			int discnt_category = rs.getInt(6);
			
			products p= new products(pid,name,qoh,qoh_threshold,original_price,discnt_category);
			list.add(p);
			
		}
		
		return list;
	}*/

	public void showProducts(Connection conn) throws SQLException {
		CallableStatement cs = conn.prepareCall("begin projj.show_products(?); end;");
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		System.out.println("PID \t \t NAME \t \t QOH \t \t QOH_THRESHOLD \t \t ORIGINAL_PRICE \t \t DISCNT_CATEGORY");
		while(rs.next()) {
			System.out.println(rs.getString(1)+" \t \t "+rs.getString(2)+" \t \t "+rs.getInt(3)+" \t \t "+rs.getInt(4)+" \t \t "+rs.getInt(5)+" \t \t "+rs.getInt(6));
		}
	
		
	}

	
}
