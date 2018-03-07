package com.DAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.model.purchases;

public interface purchasesDAO {
	public void insertIntoPurchases(Connection conn) throws SQLException, ClassNotFoundException;
	//public List<purchases> retrieveData(Connection conn) throws ClassNotFoundException, SQLException;
	public void reportTotalSaving(Connection conn) throws ClassNotFoundException, SQLException;
	public void deletePurchase(Connection conn) throws SQLException, ClassNotFoundException;
	public void showPurchases(Connection conn) throws SQLException;
	public void addPurchase(Connection conn);

}
