package sample;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel{
  Connection connection;
  
  public LoginModel() {
    connection = dbConnection.getConnection();
    if (connection == null) {
      System.exit(1);
    }
  }
  
  public boolean isDatabaseConnected()  {
    return connection != null;
  }
  
  public boolean isLogin(String user, String pass)throws SQLException  {
    PreparedStatement pr = null;
    ResultSet rs = null;
    
    String sql = "SELECT * FROM login where username = ? and password = ? ";
    try
    {
      pr = connection.prepareStatement(sql);
      pr.setString(1, user);
      pr.setString(2, pass);

      rs = pr.executeQuery();
      return rs.next();
    }
    catch (SQLException e){
      return false;
    }
    finally{
      assert pr != null;
      pr.close();
      assert rs != null;
      rs.close();
    }
  }
}
