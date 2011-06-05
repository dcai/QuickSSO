package cn.edu.ujn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import cn.edu.ujn.db.DBAccess;
import cn.edu.ujn.db.DBConnectionManager;
import cn.edu.ujn.bean.*;

import org.apache.log4j.Logger;
import java.util.ArrayList;

public class MysqlUserDAO implements UserDAO
{
  static Logger log = Logger.getLogger(MysqlLoginDAO.class);
  private DBConnectionManager dbcm = DBConnectionManager.getInstance();
  Connection connection = null;
  public int MaxLine = 4;
  public int getMaxLine(){
	  return this.MaxLine;
  }
  public MysqlUserDAO()
  {}
  public ArrayList getUserList(int offset){
	  //int TotalPages = (int)Math.ceil((double)this.getTotal()/this.MaxLine);
	  //int CurrPages = (int)Math.floor((double)offset/this.MaxLine+1);
	  ArrayList users = new ArrayList();
	  try{		  
		  connection = dbcm.getConnection();
		  DBAccess dba = new DBAccess(connection);
		  String sql = "SELECT * FROM USO_USERS LIMIT "+offset+ "," + this.MaxLine;
		  ResultSet rs = dba.query(sql);
	      if(!rs.next()){
	          rs.close();
	          dba.closeSelect();
	          return null;
	        }
	        rs.beforeFirst();
	        while(rs.next())
	        {
	          users.add(new UserBean(
	        		  rs.getInt("uid"), 
	        		  rs.getInt("user_level"),
	        		  rs.getString("user_name"), 
	        		  rs.getString("user_fullname"), 
	        		  rs.getInt("user_group"), 
	        		  rs.getInt("user_no_type"), 
	        		  rs.getString("user_no_value")));
	        }
	        return users;
	  }catch(Exception ex){
		  return null;	  
	  }finally{
	    	try{
	    		dbcm.freeConnection(connection);
	    	}catch(Exception ex){
	    		
	    	}
	    }
  }
  public int getTotal(){
	  try{
		  connection = dbcm.getConnection();
		  DBAccess dba = new DBAccess(connection);
		  String sql = "SELECT COUNT(*) FROM uso_users";
		  ResultSet rs = dba.query(sql);
		  int total = 0;
		  if (rs.next())
			  total = rs.getInt(1);		  
		  return total;
	  }catch(Exception ex){
		  return 0;
	  }finally{
	    	try{
	    		dbcm.freeConnection(connection);
	    	}catch(Exception ex){
	    		
	    	}
	    }
  }
  public UserBean execute(int uid)
  {
    try{
      connection = dbcm.getConnection();
      log.info("Get a user");
      //DbAuthorization auth = new DbAuthorization();
      DBAccess dba = new DBAccess(connection);
      String sql = "SELECT uid, user_name, user_fullname, " +
      		"user_group, user_password, user_regdate, user_level, " +
      		"user_no_type, user_no_value,user_desc FROM uso_users" + 
            " WHERE uid="+uid+"";

      ResultSet rs = dba.query(sql);

      if(!rs.next()){
        rs.close();
        dba.closeSelect();
        return null;
      }
      rs.beforeFirst();
      while(rs.next())
      {
        return new UserBean(rs.getInt("uid"), rs.getInt("user_level"), 
          rs.getString("user_name"), rs.getString("user_fullname"), 
            rs.getInt("user_group"), rs.getInt("user_no_type"), 
              rs.getString("user_no_value"));
      }
      return new UserBean();
    }catch(Exception ex){
    	return null;
    }finally{
    	try{
    		dbcm.freeConnection(connection);
    	}catch(Exception ex){
    		
    	}
    }
  }  
  public void delete(int uid)throws Exception
  {
	    try {
			connection = dbcm.getConnection();
			log.info("Delete a user");
			DBAccess dba = new DBAccess(connection);
			String sql = "DELETE FROM uso_users WHERE uid = " + uid;

			dba.update(sql);
		} catch (Exception ex) {
			throw new Exception();
		} finally {
			try {
				dbcm.freeConnection(connection);
			} catch (Exception ex) {
			}
		}
  }
  public void     editUser(UserBean usr){
	  if(usr.getUid()==-1){
		  //add new user
		  try{
			  connection = dbcm.getConnection();
			  DBAccess dba = new DBAccess(connection);
			  String sql = "INSERT INTO uso_users(`user_name`, `user_password`, " +
			  		"`user_fullname`, `user_no_type`, `user_no_value`, `user_group`) "+
			  		" VALUES('"+usr.getName()+"', '"+usr.getPwd()+"', '"+usr.getFullname()+
			  		"', "+usr.getNoType()+", '"+usr.getNoValue()+"', "+usr.getGroup()+
			  		")";
			  log.info(sql);
			  dba.update(sql);
		  }catch(Exception ex){
			  
		  }finally{
				try {
					dbcm.freeConnection(connection);
				} catch (Exception ex) {
				}			  
		  }
	  }else{
		    try {
				log.info("edit user");
				DBAccess dba = new DBAccess(connection);
				String sql = "";
				if(usr.getPwd().equals("")){
					sql = "UPDATE uso_users SET `user_fullname` = '"+ usr.getFullname() +"', "+
					"`user_group`="+usr.getGroup()+", "+
					"`user_no_type`="+usr.getNoType()+", "+
					"`user_no_value`= '"+usr.getNoValue()+
					"' WHERE uid = " + usr.getUid();					
				}else{
					sql = "UPDATE uso_users SET `user_fullname` = '"+ usr.getFullname() +"', "+
							"`user_password`= '"+usr.getPwd()+"', "+
							"`user_group`="+usr.getGroup()+", "+
							"`user_no_type`="+usr.getNoType()+", "+
							"`user_no_value`= '"+usr.getNoValue()+
							"' WHERE uid = " + usr.getUid();
				}
				log.info("update infomation");
				log.info(sql);
				dba.update(sql);
			} catch (Exception ex) {
			} finally {
				try {
					dbcm.freeConnection(connection);
				} catch (Exception ex) {
				}
			} 
	  }
  }
}