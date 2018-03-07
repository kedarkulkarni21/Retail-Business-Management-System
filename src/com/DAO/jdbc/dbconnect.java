package com.DAO.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class dbconnect {
	public static Connection getDbConnection () throws ClassNotFoundException, SQLException {
		Scanner sc = new Scanner(System.in);
		
			System.out.println("Enter username");
			String username = sc.next();
			System.out.println("Enter password");
			String password = sc.next();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn;
			
				conn = DriverManager.getConnection("jdbc:oracle:thin:@castor.cc.binghamton.edu:1521:acad111",username,password);
			
//			Statement st=conn.createStatement();
//			String query = "select * from products";
//			ResultSet rs = st.executeQuery(query);
//			while(rs.next()) {
//				System.out.println(rs.getString(1));
//				
//			}
//			conn.close();
			if(conn!=null){
				System.out.println("Database Connected");
			}
			else{
				System.out.println("Error: Incorrect username and password");
			}
			
			
		
		
		return conn;
	}

}
