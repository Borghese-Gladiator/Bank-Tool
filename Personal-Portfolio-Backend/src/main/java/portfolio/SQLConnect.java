package portfolio;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLConnect {
	static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; 
	static final String connectionUrl =  "jdbc:sqlserver://localhost;"
       + "database=testProfiles2;"
       + "user=sa;"
       + "password=timmy;";
   	static final String USER = "sa";
   	static final String PASS = "timmy";
	public void sqlConnect(String sql) {
	   Connection conn = null;
	   Statement stmt = null;
	   try{
		  Class.forName(JDBC_DRIVER);
		  //STEP 3: Open a connection
	      System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(connectionUrl);
	      //STEP 4: Execute a query
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      stmt.execute(sql);
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	}
	//Current Balance, Modify Balance
	public int sqlGetBalance() {
		int balance = 0;
		Connection conn = null;
		   Statement stmt = null;
		   ResultSet rs = null;
		   try{
			  Class.forName(JDBC_DRIVER);
			  System.out.println("Connecting to database...");
		      conn = DriverManager.getConnection(connectionUrl);
		      System.out.println("Creating statement...");
		      stmt = conn.createStatement();
		      String sql = "SELECT BALANCE FROM tblAccount;";
		      rs = stmt.executeQuery(sql);
		      while(rs.next()) {
		    	  balance = rs.getInt("BALANCE");
		      }
		      stmt.close();
		      conn.close();
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      //finally block used to close resources
		      try{
		         if(stmt!=null)
		            stmt.close();
		      }catch(SQLException se2){
		      }// nothing we can do
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }//end finally try
		   }//end try
		   System.out.println("Goodbye!");
		   return balance;
	}
	public List<Transaction> sqlAllTransactions() {
	   List<Transaction> allTransactions = new ArrayList<Transaction>();
	   Connection conn = null;
	   Statement stmt = null;
	   ResultSet rs = null;
	   try{
		  Class.forName(JDBC_DRIVER);
		  System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(connectionUrl);
	      System.out.println("Creating statement...");
	      stmt = conn.createStatement();
	      String sql = "SELECT * FROM tblTransaction";
	      rs = stmt.executeQuery(sql);
	      int key = 0;
	      while(rs.next()) {
	    	  Transaction trans = new Transaction();
	    	  trans.setDateEntered(rs.getLong("DateEntered"));
	    	  trans.setTitle(rs.getString("Title"));
	    	  trans.setHowOften(rs.getShort("HowOften"));
	    	  trans.setDescription(rs.getString("Description"));
	    	  trans.setAmount(rs.getInt("Amount"));
	    	  allTransactions.add(trans);
	      }
	      stmt.close();
	      conn.close();
	   }catch(SQLException se){
	      se.printStackTrace();
	   }catch(Exception e){
	      e.printStackTrace();
	   }finally{
	      //finally block used to close resources
	      try{
	         if(stmt!=null)
	            stmt.close();
	      }catch(SQLException se2){
	      }// nothing we can do
	      try{
	         if(conn!=null)
	            conn.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }//end finally try
	   }//end try
	   System.out.println("Goodbye!");
	   return allTransactions;
	}
}