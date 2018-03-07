package com.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.model.supplies;

public interface suppliesDAO {
	public void insertIntoSupplies(Connection conn) throws SQLException, ClassNotFoundException;
	public List<supplies> retrieveData() throws ClassNotFoundException, SQLException;
	public void showSupplies(Connection conn) throws SQLException;
}
