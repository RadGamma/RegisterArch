package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class SqlConnect {
  private static Connection connect = null;
  private static Statement statement = null;
  private static PreparedStatement preparedStatement = null;
  private static ResultSet resultSet = null;
  
  static String doc_key = null;

  
  public static String pullDocLink(int id) throws Exception {
	  
	  System.out.println("entering pullDocLink" + id);
	  docLinkStmt(id);
	  
      return doc_key;
  }

  public static void docLinkStmt(int id) throws Exception {

	  System.out.println("entering docLinkStmt" + id);
	  
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://192.168.0.7:3306/test");
             // + "user=sqluser&password=sqluserpw");

      // Statements allow to issue SQL queries to the database
      
      String sql = "SELECT Doc_Link_Loc FROM Doc_Link_Table WHERE Link_Id = ?";
      preparedStatement = connect.prepareStatement(sql);
      
      
      // PreparedStatements can use variables and are more efficient
      //String sqlStatement = "SELECT Doc_Link_Loc FROM Doc_Link_Table WHERE Link_Id = ?";
 
      
      preparedStatement.setInt(1,id);
      
      ResultSet rs = preparedStatement.executeQuery();

		while (rs.next()) {
			String docu_key = rs.getString("Doc_Link_Loc");
			doc_key = docu_key;
			System.out.println("doc key" + docu_key );
		}
      
	  System.out.println("leaving docLinkStmt" + doc_key );
      
      close();
      
  }
  
  public void setupPersistance() throws Exception {
	  try {
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://192.168.0.7:3306/test");
             // + "user=root&password=8500Nuthat");

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();
      
      String dropTableSql = "DROP TABLE JRegister ";
      // Result set get the result of the SQL query
      
      String createTableSql = "CREATE TABLE JRegister"
        		+ "(Register_Id INT AUTO_INCREMENT NOT NULL,"
        		+ "Register_Date VARCHAR(10) NOT NULL,"
        		+ "Register_Detail VARCHAR(255) NOT NULL,"
        		+ "Register_Pos_Value DECIMAL(11,2) NOT NULL,"
        		+ "Register_Neg_Value DECIMAL(11,2) NOT NULL,"
        		+ "Register_Balance DECIMAL(11,2) NOT NULL,"
        		+ "Purchase_Code VARCHAR(255) NOT NULL,"
        		+ "Date_Created TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"
        		+ "PRIMARY KEY (Register_Id))";
      
      // resultSet = statement
      //    .executeQuery("SELECT * from JRegister");
      // writeResultSet(resultSet);

      // drops the table 
      statement.executeUpdate(dropTableSql);
      System.out.println("Table dropped ");
      // create table 
      statement.executeUpdate(createTableSql);
      System.out.println("Table Created ");	  
      
  	} catch (Exception e) {
      throw e;
    } finally {
      close();
    }
  }
  public void readDataBase(String[] csvInput) throws Exception {
    try {
        
      double pos; 
      double neg; 
      double bal; 	
    	
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      connect = DriverManager
          .getConnection("jdbc:mysql://192.168.0.7:3306/test");
             // + "user=sqluser&password=sqluserpw");

      // Statements allow to issue SQL queries to the database
      statement = connect.createStatement();

      // PreparedStatements can use variables and are more efficient
      preparedStatement = connect
          .prepareStatement("insert into JRegister (Register_Date,Register_Detail,Register_Pos_Value,"
          		+ "Register_Neg_Value,Register_Balance,Purchase_Code) values (?,?,?,?,?,?);");
      
      if (csvInput[5].isEmpty()){
    	  pos = 0.00;
      } else {
    	  pos = Double.parseDouble(csvInput[5]);
      };
      if (csvInput[4].isEmpty()){
    	  neg = 0.00;
      }  else {
    	  neg = Double.parseDouble(csvInput[4]);
      };
      if (csvInput[6].isEmpty()){
    	  bal = 0.00;
      }  else {
    	  bal = Double.parseDouble(csvInput[6]);
      };
      
      // Parameters start with 1
      preparedStatement.setString(1, csvInput[0]);
      preparedStatement.setString(2, csvInput[2]);
      preparedStatement.setDouble(3, pos);
      preparedStatement.setDouble(4, neg);
      preparedStatement.setDouble(5, bal);
      preparedStatement.setString(6, csvInput[1]);
      preparedStatement.executeUpdate();
      


      //write out in single 
      System.out.println("Insert made " + csvInput[0] + ", " + csvInput[2] + ", " + pos + ", " + neg + ", " + bal);
      
      //preparedStatement = connect
      //    .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
      //resultSet = preparedStatement.executeQuery();
      //writeResultSet(resultSet);

      // Remove again the insert comment
     // preparedStatement = connect
     // .prepareStatement("delete from feedback.comments where myuser= ? ; ");
     // preparedStatement.setString(1, "Test");
     // preparedStatement.executeUpdate();
      
     // resultSet = statement
     // .executeQuery("select * from feedback.comments");
     // writeRow(resultSet);
     // writeMetaData(resultSet);
      
    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }

  }

  public void insertMortgage(String[] csvInput) throws Exception {
	    try {
	        
	    	
	      // This will load the MySQL driver, each DB has its own driver
	      Class.forName("com.mysql.jdbc.Driver");
	      // Setup the connection with the DB
	      connect = DriverManager
	          .getConnection("jdbc:mysql://192.168.0.7:3306/test");
	             // + "user=sqluser&password=sqluserpw");

	      // Statements allow to issue SQL queries to the database
	      statement = connect.createStatement();

	      // PreparedStatements can use variables and are more efficient
	      preparedStatement = connect
	          .prepareStatement("insert into Mortgage_Bill_Table (Mortgage_Trans_Description,Mortgage_Owed,Mortgage_Payed,"
	          		+ "Mortgage_Bal_Owed) values (?,?,?,?);");
	      
	      
	      // Parameters start with 1
	      preparedStatement.setString(1, csvInput[0] + " Payment");
	      preparedStatement.setString(2, "0.00");
	      preparedStatement.setString(3, csvInput[4]);
	      preparedStatement.setString(4, "99.99");
	      //preparedStatement.setDouble(5, bal);
	      //preparedStatement.setString(6, csvInput[1]);
	      preparedStatement.executeUpdate();
	      


	      //write out in single 
	      //System.out.println("Insert made " + csvInput[0] + ", " + csvInput[2] + ", " + pos + ", " + neg + ", " + bal);
	      
	      //preparedStatement = connect
	      //    .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
	      //resultSet = preparedStatement.executeQuery();
	      //writeResultSet(resultSet);

	      // Remove again the insert comment
	     // preparedStatement = connect
	     // .prepareStatement("delete from feedback.comments where myuser= ? ; ");
	     // preparedStatement.setString(1, "Test");
	     // preparedStatement.executeUpdate();
	      
	     // resultSet = statement
	     // .executeQuery("select * from feedback.comments");
	     // writeRow(resultSet);
	     // writeMetaData(resultSet);
	      
	    } catch (Exception e) {
	      throw e;
	    } finally {
	      close();
	    }

	  }
  
  private void writeRow(ResultSet resultSet) throws SQLException {
	  // Now get some metadata from the database
	  // Result set get the result of the SQL query
	    
	  //System.out.println("The columns in the table are: ");	    
	  //System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
	  while  (resultSet.next()){
	    System.out.println("Row  "+ resultSet.getString("Register_Balance"));
	    System.out.println("Row  "+ resultSet.getString("Register_Date"));
	  }
	}
  
  private void writeMetaData(ResultSet resultSet) throws SQLException {
    //   Now get some metadata from the database
    // Result set get the result of the SQL query
    
    System.out.println("The columns in the table are: ");
    
    System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
    for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
      System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
    }
  }

  private void writeResultSet(ResultSet resultSet) throws SQLException {
    // ResultSet is initially before the first data set
    while (resultSet.next()) {
      // It is possible to get the columns via name
      // also possible to get the columns via the column number
      // which starts at 1
      // e.g. resultSet.getSTring(2);
     // String user = resultSet.getString("myuser");
     // String website = resultSet.getString("webpage");
    //  String summary = resultSet.getString("summary");
    //  Date date = resultSet.getDate("datum");
    //  String comment = resultSet.getString("comments");
    //  System.out.println("User: " + user);
    //  System.out.println("Website: " + website);
    //  System.out.println("summary: " + summary);
    //  System.out.println("Date: " + date);
    //  System.out.println("Comment: " + comment);
    }
  }

  // You need to close the resultSet
  private static void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

} 