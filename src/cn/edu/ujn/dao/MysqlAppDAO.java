package cn.edu.ujn.dao;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import cn.edu.ujn.db.DBAccess;
import cn.edu.ujn.db.DBConnectionManager;
import cn.edu.ujn.bean.AppBean;
import org.apache.log4j.Logger;

public class MysqlAppDAO implements AppDAO
{
  static public Logger log = Logger.getLogger(MysqlAppDAO.class);
  private DBConnectionManager dbcm = DBConnectionManager.getInstance();
  Connection connection = null;
  DBAccess dba = null;
  public MysqlAppDAO()
  {}
  public ArrayList listAll()
  {
    ArrayList apps = new ArrayList();
    try{
      connection = dbcm.getConnection();
      dba = new DBAccess(connection);
      String sql = "SELECT app_id, app_uri, app_ws, app_name, app_desc FROM USO_APPS";
      ResultSet rs = dba.query(sql);
      if(!rs.next()){
        rs.close();
        dba.closeSelect();
        return null;
      }
      rs.beforeFirst();
      while(rs.next())
      {
        apps.add(new AppBean(rs.getInt("app_id"), rs.getString("app_uri"), 
          rs.getString("app_ws"), rs.getString("app_name")));
      }
      dbcm.freeConnection(connection);
      return apps;
    }catch(Exception ex){
    }finally{
    	try{
    		dbcm.freeConnection(connection);
    	}catch(Exception ex){
    		
    	}
    }
    return null;
  }
  public AppBean execute(int app_id)
  {
    try{
      connection = dbcm.getConnection();
      dba = new DBAccess(connection);
      String sql = "SELECT app_uri, app_ws, app_name, app_desc FROM USO_APPS WHERE app_id = "+app_id+"";

      ResultSet rs = dba.query(sql);

      if(!rs.next()){
        rs.close();
        dba.closeSelect();
        return null;
      }{
	      rs.beforeFirst();
	      rs.next();
	      AppBean a = new AppBean(app_id, rs.getString("app_uri"),rs.getString("app_ws"),rs.getString("app_name"));
	      dbcm.freeConnection(connection);
	      return a;
      }
    }catch(Exception ex){
      return new AppBean();
    }finally{
    	try{
    		
    		dbcm.freeConnection(connection);
    	}catch(Exception ex){
    		
    	}
    }
  }

}