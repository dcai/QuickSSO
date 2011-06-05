package cn.edu.ujn.dao;
import cn.edu.ujn.exception.*;
import java.sql.Connection;
import java.sql.ResultSet;
import cn.edu.ujn.db.DBAccess;
import cn.edu.ujn.db.DBConnectionManager;
import cn.edu.ujn.bean.MapBean;
import org.apache.log4j.Logger;
public class MysqlMapDAO implements MapDAO
{
  static Logger log = Logger.getLogger(MysqlMapDAO.class);
  private DBConnectionManager dbcm = DBConnectionManager.getInstance();
  Connection connection = null;
  DBAccess dba = null;
  public MysqlMapDAO()
  {}
  public int     edit(int app_id, int uid, String username, String password){
	  return 1;
  }
  public int     delete(int app_id, int uid){
	  return 1;
  }
  public MapBean execute(int app_id, int uid)throws Exception
  {
    try{
      connection = dbcm.getConnection();
      dba = new DBAccess(connection);
      String sql = "SELECT app_usr, app_pwd FROM uso_mapping WHERE app_id = "+app_id+"&& uid = " + uid;

      ResultSet rs = dba.query(sql);

      if(!rs.next()){
        rs.close();
        dba.closeSelect();
        return new MapBean();
      }
      rs.beforeFirst();
      while(rs.next())
      {
        MapBean m = new MapBean(rs.getString("app_usr"),rs.getString("app_pwd"));
        return m;
      }
      return new MapBean();
    }catch(Exception ex){
      throw new UnauthorizedException(2);
    }finally{
    	try{
    		dbcm.freeConnection(connection);
    	}catch(Exception ex){
    		
    	}
    }
  }

}