package com.DAO.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.DAO.discountsDAO;

import oracle.jdbc.OracleTypes;

public class discountsDAOImpl implements discountsDAO{

	@Override
	public void showDiscounts(Connection conn) throws SQLException {
		CallableStatement cs = conn.prepareCall("begin projj.show_discounts(?); end;");
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		System.out.println("DISCNT_CTRY \t \t DISCNT_RATE");
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" \t \t \t "+rs.getDouble(2));
		}
		
		
		
	}

}
