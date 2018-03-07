package com.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface discountsDAO {
	public void showDiscounts(Connection conn) throws SQLException;

}
