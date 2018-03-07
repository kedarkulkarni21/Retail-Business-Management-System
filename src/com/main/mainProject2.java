package com.main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.DAO.*;
import com.DAO.jdbc.*;
import com.model.*;


public class mainProject2 {

	public static void main(String[] args) throws ClassNotFoundException {
		int select,qty,purNum;
		
		String eid,pid,cid,ptime,sid, sdate, telephone, name, username,operation, op_time;
		try {
		Connection conn = dbconnect.getDbConnection();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your choice");
		System.out.println("1. Query1 \t 2. Query2 \t 3. Query3 \t 4. Query4 \t 5. Query5 \t 6. Query6 \t 7. Query7 \t 8. Query8 \t 9. Exit ");
		int choice = sc.nextInt();
		switch(choice) {
		case 1:	System.out.println("Select a table in which you want to insert");
				System.out.println("1. Purchases \t 2. Supplies \t 3. Logs");
				select = sc.nextInt();
				switch(select) {
				case 1:	
						purchasesDAO pr = new purchasesDAOImpl();
						pr.insertIntoPurchases(conn);
						
						break;
				case 2: suppliesDAO sup = new suppliesDAOImpl();
						sup.insertIntoSupplies(conn);
						break;
				case 3: 
						logsDAO l = new logsDAOImpl();
						l.insertIntoLogs(conn);
						break;
				}
				break;
		case 2:	System.out.println("Select the table to be displayed");
				System.out.println("1. Employees \t 2. Customers \t 3. Products \t 4. Supplies \t 5. Suppliers \t 6. Purchases \t 7. Discounts \t 8. Logs \t 9. Exit");
				int sub_choice = sc.nextInt();
					switch(sub_choice) {
					case 1: employeesDAO e = new employeesDAOImpl();
							e.showEmployees(conn);
							break;
					case 2: customersDAO c = new customersDAOImpl();
							c.showCustomers(conn);
							break;
					case 3: productsDAO p = new productsDAOImpl();
							p.showProducts(conn);
							break;
					case 4: suppliesDAO s = new suppliesDAOImpl();
							s.showSupplies(conn);
							break;
					case 5: suppliersDAO sup = new suppliersDAOImpl();
							sup.showSuppliers(conn);
							break;
					case 6: purchasesDAO pur = new purchasesDAOImpl();
							pur.showPurchases(conn);
							break;
					case 7:	discountsDAO d = new discountsDAOImpl();
							d.showDiscounts(conn);
							break;
					case 8: logsDAO l = new logsDAOImpl();
							l.showLogs(conn);
							break;
					case 9: System.exit(0);
					}
				break;
				
		case 3:	
				purchasesDAO p = new purchasesDAOImpl();
				p.reportTotalSaving(conn);
				break;
		case 4:	
				employeesDAO emp = new employeesDAOImpl();
				emp.monthlySaleActivities(conn);
				break;
		case 5: 
				customersDAO c = new customersDAOImpl();
				c.addCustomers(conn);
				break;
		case 6:	triggersDAO t = new triggersDAOImpl();
				t.implementTriggers(conn);
				System.out.println("Triggers executed successfully");
				break;
		case 7: purchasesDAO pr = new purchasesDAOImpl();
				
				pr.addPurchase(conn);
				break;
		case 8: 
				purchasesDAO pur = new purchasesDAOImpl();
				pur.deletePurchase(conn);
				break;
		case 9: System.exit(0);
		default:System.out.println("Invalid choice");
				System.exit(0);
				
		}
		}catch(SQLException e) {
			System.out.println("Username password entered is incorrect");
		}
		
		}
	/*public static void initialize(Connection conn) throws SQLException {
		String query1="create sequence purchases_seq start with 100000 increment by 1";
		String query2 = "create sequence logs_seq start with 10000 increment by 1";
		String query3 = "create sequence supplies_seq start with 1000 increment by 1";
		
		String query4 = "CREATE OR REPLACE trigger trg_cust_add\r\n" + 
				"after insert on customers\r\n" + 
				"for each row\r\n" + 
				"declare \r\n" + 
				"	myuser varchar2(11);\r\n" + 
				"begin \r\n" + 
				"	select user into myuser from dual;\r\n" + 
				"    	insert into logs values (logs_seq.nextval,myuser,'insert',sysdate,'customers',:new.cid);\r\n" + 
				"end;";
		String query5 = "CREATE OR REPLACE trigger trg_cust_upd\r\n" + 
				"after update on customers\r\n" + 
				"for each row\r\n" + 
				"declare \r\n" + 
				"	myuser varchar2(11);\r\n" + 
				"begin \r\n" + 
				"	select user into myuser from dual;\r\n" + 
				"    	insert into logs values (logs_seq.nextval,myuser,'update',sysdate,'customers',:new.cid);\r\n" + 
				"end;";
		String query6 = "CREATE OR REPLACE trigger trg_pur_add\r\n" + 
				"after insert on purchases\r\n" + 
				"for each row\r\n" + 
				"declare \r\n" + 
				"	myuser varchar2(11);\r\n" + 
				"begin \r\n" + 
				"	select user into myuser from dual;\r\n" + 
				"    	insert into logs values (logs_seq.nextval,myuser,'insert',sysdate,'purchases',:new.pur#);\r\n" + 
				"end;";
		String query7 = "CREATE OR REPLACE trigger trg_pro_upd\r\n" + 
				"after update on products\r\n" + 
				"for each row\r\n" + 
				"declare \r\n" + 
				"	myuser varchar2(11);\r\n" + 
				"begin \r\n" + 
				"	select user into myuser from dual;\r\n" + 
				"    	insert into logs values (logs_seq.nextval,myuser,'update',sysdate,'products',:new.pid);\r\n" + 
				"end;";
		String query8 = "CREATE OR REPLACE trigger trg_sup_add\r\n" + 
				"after insert on supplies\r\n" + 
				"for each row\r\n" + 
				"declare \r\n" + 
				"	myuser varchar2(11);\r\n" + 
				"begin \r\n" + 
				"	select user into myuser from dual;\r\n" + 
				"    	insert into logs values (logs_seq.nextval,myuser,'insert',sysdate,'supplies',:new.sup#);\r\n" + 
				"end;";
		String query9 = "CREATE OR REPLACE trigger trg_purbefr_ins\r\n" + 
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
				"END;";
		String query10 = "\r\n" + 
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
		String query11 = "CREATE OR REPLACE trigger trg_pur_del\r\n" + 
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
		Statement st = conn.createStatement();
		st.executeQuery(query1);
		st.executeQuery(query2);
		st.executeQuery(query3);
		st.executeQuery(query4);
		st.executeQuery(query5);
		st.executeQuery(query6);
		st.executeQuery(query7);
		st.executeQuery(query8);
		st.executeQuery(query9);
		st.executeQuery(query10);
		st.executeQuery(query11);
		
	}*/
	
	
}
