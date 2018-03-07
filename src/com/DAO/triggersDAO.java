package com.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface triggersDAO {
	public void implementTriggers(Connection conn) throws SQLException;

}
