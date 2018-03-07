package com.DAO;

import java.util.List;

import com.model.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface productsDAO {

//	public List<products> retrieveData() throws ClassNotFoundException, SQLException;
	public void showProducts(Connection conn) throws SQLException;
	
}
