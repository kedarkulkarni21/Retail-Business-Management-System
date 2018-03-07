package com.DAO.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.DAO.*;

import oracle.jdbc.OracleTypes;

public class suppliersDAOImpl implements suppliersDAO{

	@Override
	public void showSuppliers(Connection conn) throws SQLException {
		CallableStatement cs = conn.prepareCall("begin projj.show_suppliers(?); end;");
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		System.out.println("SID \t \t NAME \t \t CITY \t \t TELEPHONE# \t \t EMAIL");
		while(rs.next()) {
			System.out.println(rs.getString(1)+" \t \t "+rs.getString(2)+" \t \t "+rs.getString(3)+" \t \t "+rs.getString(4)+" \t \t "+rs.getString(5));
		}
		
	}
	
}
