package com.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface suppliersDAO {
	public void showSuppliers(Connection conn) throws SQLException;

}
