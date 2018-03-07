package com.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.model.*;

public interface customersDAO {
//	public List<customers> retrieveData() throws ClassNotFoundException, SQLException;
	public void addCustomers(Connection conn) throws SQLException;
	public void showCustomers(Connection conn) throws SQLException;
}
