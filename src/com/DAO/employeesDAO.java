package com.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.model.*;

public interface employeesDAO {
	public List<employees> retrieveData() throws ClassNotFoundException, SQLException;
	void monthlySaleActivities(Connection conn) throws SQLException;
	void showEmployees(Connection conn) throws SQLException;
}
