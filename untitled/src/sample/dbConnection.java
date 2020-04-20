package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection
{

  public static Connection getConnection(){
    try
    {
      Class.forName("org.sqlite.JDBC");

      return DriverManager.getConnection("jdbc:sqlite:database.db");
    }
    catch (ClassNotFoundException|SQLException ex)
    {
      ex.printStackTrace();
    }
    return null;
  }
}
