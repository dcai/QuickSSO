package cn.edu.ujn.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import cn.edu.ujn.db.DBAccess;
import cn.edu.ujn.db.DBConnectionManager;
import cn.edu.ujn.bean.GuidBean;

public class MysqlGuidDAO implements GuidDAO
{
  private DBConnectionManager dbcm = DBConnectionManager.getInstance();
  Connection connection = null;
  DBAccess dba = null;
  private String sid = null;
  public MysqlGuidDAO()
  {}
  public void delete()
  {
    try{
      connection = dbcm.getConnection();
      dba = new DBAccess(connection);
      String sql = "DELETE FROM TMP WHERE SID='"+this.sid+"'";
      dba.update(sql);
    }catch(Exception ex){
    }
  }
  public GuidBean execute(String sid)
  {
    this.sid = sid;
    try{
      connection = dbcm.getConnection();
      dba = new DBAccess(connection);
      String sql = "SELECT time FROM TMP WHERE SID ='"+sid+"'";

      ResultSet rs = dba.query(sql);

      if(!rs.next()){
        rs.close();
        dba.closeSelect();
        return null;
      }
      rs.beforeFirst();
      while(rs.next())
      {
        //System.out.println("password right");
        //dba.runSql("DELETE FROM TMP WHERE SID='"+ sid +"'");
        GuidBean ss = new GuidBean(200);
        return ss;
      }
      return new GuidBean();
    }catch(Exception ex){
    }finally{
    	try{
    		dbcm.freeConnection(connection);
    	}catch(Exception ex){
    		
    	}
    }
	return null;
  }

}