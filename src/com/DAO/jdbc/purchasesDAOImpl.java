package com.DAO.jdbc;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


import com.DAO.*;
import com.DAO.jdbc.*;
import com.main.mainProject2;
import com.model.*;

import oracle.jdbc.OracleTypes;
import com.DAO.jdbc.*;
public class purchasesDAOImpl extends mainProject2 implements purchasesDAO {
	int select,qty,purNum;
	String eid,pid,cid,ptime,sid, sdate, telephone, name, username,operation, op_time;
	Scanner sc = new Scanner(System.in);

	
	@Override
	public void insertIntoPurchases(Connection conn) throws ClassNotFoundException{
		try {
		
		
		System.out.println("Enter the values for the following attributes");
		System.out.println("Enter Employee ID:");
		eid = sc.next();
		System.out.println("Enter Product ID (pid):");
		pid = sc.next();
		System.out.println("Enter Customer ID (cid):");
	    cid = sc.next();
		System.out.println("Quantity (qty)");
		qty = sc.nextInt();
		System.out.println("Enter Purchase Time (ptime), use format (dd-MON-yy)::");
		ptime = sc.next();
		System.out.println("Total price (total_price)");
		double totalPrice = sc.nextDouble();
		PreparedStatement pst;
		
			pst = conn.prepareStatement("insert into purchases values(?,?,?,?,?,to_date(?,'DD-MON-YYYY'),?)");
		
		String sql = "select purchases_seq.NEXTVAL from dual";
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		rs.next();
		int purNum = rs.getInt(1);
		String query1 = "drop trigger trg_purbefr_ins";
		String query2 = "drop trigger trg_pur_ins";
		String query3 = "drop trigger trg_pur_add";
		st.executeUpdate(query1);
		st.executeUpdate(query2);
		
		pst.setInt(1, purNum);
		pst.setString(2, eid);
		pst.setString(3, pid);
		pst.setString(4, cid);
		pst.setInt(5, qty);
		pst.setString(6, ptime);
		pst.setDouble(7, totalPrice);
	//	System.out.println("1");
		int i = pst.executeUpdate();
	//	System.out.println("2");
		 if(i!=0){
		        System.out.println("Record has been inserted");
		        showPurchases(conn);
		      }
		      else{
		        System.out.println("failed to insert the data");
		      }
		}catch(InputMismatchException e) {
			System.out.println("InputMismatchException. Check the entered input");
		}
		
		catch (SQLException e) {
			if(e.getErrorCode() == 01403)
				System.out.println("The entered input does not exist in the table. Please check the input");
			else {
				System.out.println("SQLException occurred ");
				e.printStackTrace();
			}
		}
	
		
	}
/*
	@Override
	public List<purchases> retrieveData(Connection conn) throws ClassNotFoundException, SQLException {
		String query = "select * from purchases order by pur#";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		List<purchases> list = new ArrayList<purchases>();
		while(rs.next()) {
			int purNum = rs.getInt(1);
			String eid = rs.getString(2);
			String pid = rs.getString(3);
			String cid = rs.getString(4);
			int qty = rs.getInt(5);
			String ptime = rs.getString(6);
			Double totalPrice = rs.getDouble(7);
			
			purchases p= new purchases(purNum,eid,pid,cid,qty,ptime,totalPrice);
			list.add(p);
		}
		return list;
	}*/

	@Override
	public void reportTotalSaving( Connection conn){
		try {
		System.out.println("Enter Purchase Number (pur#)");
		purNum = sc.nextInt();
		Statement st;
		
			st = conn.createStatement();
		
		String query = "CREATE or REPLACE FUNCTION purchase_saving (purID in purchases.pur#%type) \r\n" + 
				"RETURN number is total_saving number;\r\n" + 
				"BEGIN \r\n" + 
				"DECLARE\r\n" + 
				"	CURSOR valid_pur IS\r\n" + 
				"      SELECT pur# FROM purchases WHERE pur# = purID;\r\n" + 
				"      ret_valid number;\r\n" + 
				"	  e1 EXCEPTION;\r\n" + 
				"	BEGIN\r\n" + 
				"           ret_valid := 0;\r\n" + 
				"           FOR purID in valid_pur LOOP\r\n" + 
				"           ret_valid:=1;\r\n" + 
				"           end loop;\r\n" + 
				"           \r\n" + 
				"		IF(ret_valid=0) THEN\r\n" + 
				"              RAISE e1;\r\n" + 
				"						 \r\n" + 
				"		ELSE		 \r\n" + 
				"			SELECT ((pro.original_price* pur.qty)-pur.total_price) INTO total_saving\r\n" + 
				"			FROM products pro join purchases pur on pro.pid = pur.pid\r\n" + 
				"			WHERE pur.pur# = purID;\r\n" + 
				"			RETURN total_saving;\r\n" + 
				"			dbms_output.put_line('Successfully reported the total_saving for the given purchase ID');\r\n" + 
				"		 END IF;\r\n" + 
				"		 \r\n" + 
				"	EXCEPTION\r\n" + 
				"          WHEN e1 THEN\r\n" + 
				"                  Raise_application_error(-20001,'The purchase ID : '|| purID ||' is not valid');\r\n" + 
				"  END;\r\n" + 
				"END;";
		ResultSet rs = st.executeQuery(query);
		CallableStatement cs = conn.prepareCall("{?= call purchase_saving(?)}");
		cs.registerOutParameter(1, Types.DOUBLE);
		cs.setInt(2, purNum);
		cs.execute();
		System.out.println("The total savings for Purchase Number "+purNum+" is "+cs.getDouble(1));
		}catch(InputMismatchException e) {
			System.out.println("InputMismatchException. Please check your input");
		}
		
		catch (SQLException e) {
			System.out.println("Purchase Number does not exist. Please enter a valid Purchase Number");
		
		}
	}

	@Override
	public void deletePurchase(Connection conn) {
		try {
		System.out.println("Enter Purchase Number (pur#):");
		purNum = sc.nextInt();
		Statement st;
		
			st = conn.createStatement();
		
		String query = "  \r\n" + 
				"CREATE OR REPLACE trigger trg_pur_del\r\n" + 
				"AFTER DELETE on purchases\r\n" + 
				"for each row\r\n" + 
				"DECLARE \r\n" + 
				"	myuser varchar2(11);\r\n" + 
				"BEGIN \r\n" + 
				"		SELECT USER into myuser FROM dual;\r\n" + 
				"    	INSERT into logs values (logs_seq.NEXTVAL,myuser,'delete',SYSDATE,'purchases',:OLD.pur#);\r\n" + 
				"		\r\n" + 
				"		UPDATE products SET qoh=(qoh + :OLD.qty) WHERE pid=:OLD.pid;\r\n" + 
				"		\r\n" + 
				"				UPDATE customers \r\n" + 
				"				SET visits_made = (visits_made + 1),\r\n" + 
				"				last_visit_date = SYSDATE \r\n" + 
				"				WHERE cid = :OLD.cid;\r\n" + 
				"END;";
		ResultSet i = st.executeQuery(query);
	//	System.out.println(i);
		CallableStatement cs = conn.prepareCall("{Call projj.delete_purchase(?)}");
	//	 cs.registerOutParameter(1, OracleTypes.CURSOR);
		 cs.setInt(1, purNum);
		 cs.execute();
		 System.out.println("Values deleted");
		 showPurchases(conn);
		} catch (SQLException e) {
 
			System.out.println("Purchase Number does not exist. Please enter a valid Purchase Number");
		}
		
	}

	@Override
	public void showPurchases(Connection conn) {
		CallableStatement cs;
		try {
			cs = conn.prepareCall("begin projj.show_purchases(?); end;");
		
		cs.registerOutParameter(1, OracleTypes.CURSOR);
		cs.execute();
		ResultSet rs = (ResultSet) cs.getObject(1);
		System.out.println("PUR# \t \t EID \t \t PID \t \t CID \t \t QTY \t \t PTIME \t \t TOTAL PRICE");
		while(rs.next()) {
			Date ptime = rs.getDate(6);	
			DateFormat dateformat=new SimpleDateFormat("dd-MMM-yy");
			String dateString=dateformat.format(ptime);
			System.out.println(rs.getInt(1)+" \t \t "+rs.getString(2)+" \t \t "+rs.getString(3)+" \t \t "+rs.getString(4)+" \t \t "+rs.getInt(5)+" \t \t "+dateString+" \t \t"+rs.getInt(7));
		}
		}catch(SQLDataException e) {
			System.out.println("Entered date format is incorrect.");
		}
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void addPurchase(Connection conn) {
		try {
		String query1 =  "CREATE OR REPLACE trigger trg_purbefr_ins\r\n" + 
				"BEFORE INSERT  on purchases\r\n" + 
				"for each row\r\n" + 
				"DECLARE \r\n" + 
				"	myuser varchar2(11);\r\n" + 
				"	ret_custdate purchases.ptime%TYPE;\r\n" + 
				"\r\n" + 
				"BEGIN \r\n" + 
				"		SELECT USER into myuser FROM dual;\r\n" + 
				"    	INSERT into logs VALUES (logs_seq.NEXTVAL,myuser,'insert',SYSDATE,'purchases',:NEW.pur#);\r\n" + 
				"\r\n" + 
				"		UPDATE products SET qoh=(qoh - :new.qty)\r\n" + 
				"		WHERE pid=:new.pid;\r\n" + 
				"\r\n" + 
				"		\r\n" + 
				"		SELECT last_visit_date INTO ret_custdate FROM customers WHERE cid = :NEW.cid;\r\n" + 
				"\r\n" + 
				"		IF(:NEW.ptime > ret_custdate) THEN\r\n" + 
				"				UPDATE customers \r\n" + 
				"				SET visits_made = (visits_made + 1),\r\n" + 
				"				last_visit_date = SYSDATE \r\n" + 
				"				WHERE cid = :NEW.cid;\r\n" + 
				"\r\n" + 
				"			END IF; \r\n" + 
				"	\r\n" + 
				"END;\r\n";
		String query2=
				"CREATE OR REPLACE trigger trg_pur_ins\r\n" + 
				"AFTER INSERT on purchases\r\n" + 
				"for each row\r\n" + 
				"DECLARE \r\n" + 
				"	myuser varchar2(20);\r\n" + 
				"	ret_sid VARCHAR2(2);\r\n" + 
				"	ret_prothrshld NUMBER;\r\n" + 
				"	ret_qoh number;\r\n" + 
				"	ret_quan number;\r\n" + 
				"BEGIN \r\n" + 
				"		SELECT USER into myuser FROM dual;\r\n" + 
				"    	INSERT into logs VALUES (logs_seq.NEXTVAL,myuser,'insert',SYSDATE,'purchases',:new.pur#);\r\n" + 
				"\r\n" + 
				"		SELECT qoh,qoh_threshold INTO ret_qoh,ret_prothrshld FROM products WHERE pid=:NEW.pid;\r\n" + 
				"\r\n" + 
				"		\r\n" + 
				"		IF(ret_qoh < ret_prothrshld) THEN \r\n" + 
				"		   dbms_output.put_line ('Current QOH is below the required threshold, new supply is needed');\r\n" + 
				"		   \r\n" + 
				"		   ret_quan := ((ret_prothrshld - ret_qoh) + 10 + ret_qoh);\r\n" + 
				"\r\n" + 
				"					SELECT sid into ret_sid FROM supplies \r\n" + 
				"					WHERE pid = :NEW.pid and ROWNUM =1\r\n" + 
				"					ORDER by :NEW.pid;\r\n" + 
				"\r\n" + 
				"					\r\n" + 
				"			INSERT INTO supplies VALUES (supplies_seq.NEXTVAL, :NEW.pid, ret_sid, SYSDATE,ret_quan);\r\n" + 
				"			UPDATE products SET qoh=(qoh + ret_quan)\r\n" + 
				"			WHERE pid=:NEW.pid;\r\n" + 
				"\r\n" + 
				"		END IF;\r\n" + 
				"				   \r\n" + 
				"END;";
			
		
				Statement st;
				st = conn.createStatement();
				st.executeQuery(query1);
				st.executeQuery(query2);
				System.out.println("Enter Employee ID (eid):");
				eid = sc.next();
				System.out.println("Enter Product ID (pid):");
				pid = sc.next();
				System.out.println("Enter Customer ID (cid):");
				cid = sc.next();
				System.out.println("Enter Quantity (qty):");
				qty = sc.nextInt();
				CallableStatement cst = conn.prepareCall("{call projj.add_purchase(?,?,?,?,?)}");
				cst.setString(1, eid);
				cst.setString(2, pid);
				cst.setString(3, cid);
				cst.setInt(4, qty);
				cst.registerOutParameter(5, OracleTypes.VARCHAR);
				cst.executeUpdate();
				System.out.println(cst.getString(5));
				System.out.println("TABLE : PURCHASE");
				System.out.println();
				showPurchases(conn);
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
				productsDAO p = new productsDAOImpl();
				System.out.println();
				System.out.println("TABLE : PRODUCTS");
				System.out.println();
				p.showProducts(conn);
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
				customersDAO c = new customersDAOImpl();
				System.out.println();
				System.out.println("TABLE : CUSTOMERS");
				System.out.println();
				c.showCustomers(conn);
				System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
				suppliesDAO s = new suppliesDAOImpl();
				System.out.println();
				System.out.println("TABLE : SUPPLIES");
				System.out.println();
				s.showSupplies(conn);
			}catch(InputMismatchException i) {
				System.out.println("InputMismatchException. Please enter correct input");
			}
			catch(SQLIntegrityConstraintViolationException s) {
				String errcode = "02291";
				long errcode1 = Long.parseLong(errcode);
				if(s.getErrorCode() == errcode1) {
					System.out.println("Integrity constraint voilated. Entered value does not exist in the table. Please enter valid input");
				}
			}
			catch(SQLException e) {
				if(e.getErrorCode() == 12899) {
					System.out.println("The value entered is too long. Please check the input entered");
				}
				
				e.printStackTrace();
			}
}
}
