package cn.edu.ujn.db;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
/**
 * Database access
 * @version 0.3.5
 * @author Xiaobo Liu
 */
public class DBAccess {
  static Logger log = Logger.getLogger(DBAccess.class);
  protected Statement statement;
  protected Connection connection;
  /**
   * Constructor, Create a new DBAccess object.
   * @param connection a certain connection.
   */
  public DBAccess(Connection connection) {
    this.connection=connection;
  }
  /**
   * Close select
   * @throws SQLException this method
   */
  public void closeSelect() throws SQLException {
    if (statement != null)
      statement.close();
  }
  /**
  * open select
  * @param sql sql string
  * @return ResultSet
  * @throws SQLException this method
  */
  public ResultSet query(String sql) throws SQLException {
    statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    ResultSet rs=statement.executeQuery(sql);
    return rs;
  }
  /**
  * Run a sql string
  * @param sql sql string
  * @return int
  * @throws SQLException this method
  */
  public int update(String sql) throws SQLException {
    statement = connection.createStatement();
    int result=statement.executeUpdate(sql);
    statement.close();
    return result;
  }
}