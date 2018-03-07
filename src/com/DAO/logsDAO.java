package com.DAO;

import java.sql.Connection;
import java.sql.SQLException;

public interface logsDAO {
	public void showLogs(Connection conn) throws SQLException;
	public void insertIntoLogs(Connection conn);
}
