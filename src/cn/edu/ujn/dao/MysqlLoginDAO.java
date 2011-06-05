package cn.edu.ujn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import cn.edu.ujn.exception.*;
import cn.edu.ujn.db.DBAccess;
import cn.edu.ujn.db.DBConnectionManager;
import cn.edu.ujn.bean.Authorization;
import cn.edu.ujn.bean.DbAuthorization;
import org.apache.log4j.Logger;

public class MysqlLoginDAO implements LoginDAO
{
  static Logger log = Logger.getLogger(MysqlLoginDAO.class);
  private DBConnectionManager dbcm = DBConnectionManager.getInstance();
  Connection connection = null;
  public MysqlLoginDAO()
  {}
  public Authorization execute(String usr, String pwd)throws UnauthorizedException
  {

	ResultSet rs = null;
    if (usr == null || pwd == null)
      throw new UnauthorizedException(1);
    try{
      connection = dbcm.getConnection();
      if(connection == null)
      {
    	  return null;
      }
      //DbAuthorization auth = new DbAuthorization();
      DBAccess dba = new DBAccess(connection);
      
      String sql = "SELECT `uid`, `user_fullname`, " +
      		"`user_group`, `user_password`, `user_regdate`, `user_level`, " +
      		"`user_no_type`, `user_no_value`, `user_desc` FROM uso_users" + 
            " WHERE `user_name` = '"+usr+"'";
      rs = dba.query(sql);

      if(!rs.next()){
        rs.close();
        dba.closeSelect();
        return null;
      }else{
	      rs.beforeFirst();
	      while(rs.next())
	      {
	        if(rs.getString("user_password").equals(pwd))
	        {
	          dbcm.freeConnection(connection);
	          return new DbAuthorization(rs.getInt("uid"), rs.getInt("user_level"), usr, "", "login succeed");
	        }
	        else{
	          dbcm.freeConnection(connection);
	          return new DbAuthorization("You input the wrong password. ");
	        }
	      }
      }
      dbcm.freeConnection(connection);
      return new DbAuthorization("Main database doesn't have this user.");
    }catch(Exception ex){
      throw new UnauthorizedException(2);
    }finally{
      try{
    	  dbcm.freeConnection(connection);
      }catch(Exception ex)
      {
      }
    }
  }
}