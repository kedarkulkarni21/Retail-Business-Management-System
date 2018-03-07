package com.DAO.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.DAO.triggersDAO;

public class triggersDAOImpl implements triggersDAO{

	@Override
	public void implementTriggers(Connection conn) throws SQLException {
		String query ="CREATE OR REPLACE trigger trg_cust_add\r\n" + 
				"after insert on customers\r\n" + 
				"for each row\r\n" + 
				"declare \r\n" + 
				"	myuser varchar2(11);\r\n" + 
				"begin \r\n" + 
				"	select user into myuser from dual;\r\n" + 
				"    	insert into logs values (logs_seq.nextval,myuser,'insert',sysdate,'customers',:new.cid);\r\n" + 
				"end;\r\n" + 
				"/\r\n" + 
				"\r\n" + 
				"CREATE OR REPLACE trigger trg_cust_upd\r\n" + 
				"after update on customers\r\n" + 
				"for each row\r\n" + 
				"declare \r\n" + 
				"	myuser varchar2(11);\r\n" + 
				"begin \r\n" + 
				"	select user into myuser from dual;\r\n" + 
				"    	insert into logs values (logs_seq.nextval,myuser,'update',sysdate,'customers',:new.cid);\r\n" + 
				"end;\r\n" + 
				"/\r\n" + 
				"\r\n" + 
				"CREATE OR REPLACE trigger trg_pur_add\r\n" + 
				"after insert on purchases\r\n" + 
				"for each row\r\n" + 
				"declare \r\n" + 
				"	myuser varchar2(11);\r\n" + 
				"begin \r\n" + 
				"	select user into myuser from dual;\r\n" + 
				"    	insert into logs values (logs_seq.nextval,myuser,'insert',sysdate,'purchases',:new.pur#);\r\n" + 
				"end;\r\n" + 
				"/\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"CREATE OR REPLACE trigger trg_pro_upd\r\n" + 
				"after update on products\r\n" + 
				"for each row\r\n" + 
				"declare \r\n" + 
				"	myuser varchar2(11);\r\n" + 
				"begin \r\n" + 
				"	select user into myuser from dual;\r\n" + 
				"    	insert into logs values (logs_seq.nextval,myuser,'update',sysdate,'products',:new.pid);\r\n" + 
				"end;\r\n" + 
				"/\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"CREATE OR REPLACE trigger trg_sup_add\r\n" + 
				"after insert on supplies\r\n" + 
				"for each row\r\n" + 
				"declare \r\n" + 
				"	myuser varchar2(11);\r\n" + 
				"begin \r\n" + 
				"	select user into myuser from dual;\r\n" + 
				"    	insert into logs values (logs_seq.nextval,myuser,'insert',sysdate,'supplies',:new.sup#);\r\n" + 
				"end;\r\n" + 
				"/";
		Statement st = conn.createStatement();
		st.execute(query);
		
	}

}
