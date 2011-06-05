package cn.edu.ujn.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.edu.ujn.bean.GroupBean;
import cn.edu.ujn.bean.UserBean;
import cn.edu.ujn.db.DBAccess;
import cn.edu.ujn.db.DBConnectionManager;

public class MysqlGroupDAO implements GroupDAO {
	static public Logger log = Logger.getLogger(MysqlGroupDAO.class);
	private DBConnectionManager dbcm = DBConnectionManager.getInstance();
	Connection connection = null;
	DBAccess dba = null;
	public GroupBean execute(int group_id) {
	    try{
	    	log.info("GET A Group");
	        connection = dbcm.getConnection();
	        dba = new DBAccess(connection);
	        String sql = "SELECT group_name, group_type, group_moderator, group_desc FROM USO_GROUPS WHERE group_id = "+group_id+"";

	        ResultSet rs = dba.query(sql);

	        if(!rs.next()){
	          rs.close();
	          dba.closeSelect();
	          dbcm.freeConnection(connection);
	          return null;
	        }{
	        	rs.beforeFirst();
		        rs.next();
		        GroupBean g = new GroupBean(group_id, rs.getString("group_name"), 
		        		  rs.getInt("group_type"),rs.getInt("group_moderator"), 
		        		  rs.getString("group_desc"));
		        dbcm.freeConnection(connection);
		        return g;
	        }
	      }catch(Exception ex){
	        return new GroupBean();
	      }
	}

	public ArrayList listAll() {
	    ArrayList gps = new ArrayList();
	    try{
	    	log.info("LIST ALL Groups");
	      connection = dbcm.getConnection();
	      dba = new DBAccess(connection);
	      String sql = "SELECT group_id, group_name, group_type, group_moderator, group_desc FROM USO_GROUPS";
	      ResultSet rs = dba.query(sql);
	      if(!rs.next()){
	        rs.close();
	        dba.closeSelect();
	      }else{
		      rs.beforeFirst();
		      while(rs.next())
		      {
		        gps.add(new GroupBean(rs.getInt("group_id"), rs.getString("group_name"),
		        		rs.getInt("group_type"),rs.getInt("group_moderator"), 
		        		rs.getString("group_desc")));
		      }
	      }
	      dbcm.freeConnection(connection);
	      return gps;
	    }catch(Exception ex){
	    	return null;
	    }finally{
	    	try{
	    		dbcm.freeConnection(connection);
	    	}catch(Exception ex){
	    		
	    	}
	    }
	}
	public boolean removeUser(int group_id, int user_id){
		return true;
	}
	public boolean update(GroupBean gp){
		return true;
	}
	public ArrayList getUsers(int group_id){
		ArrayList users = new ArrayList();
		try{
			log.info("LIST ALL USERS IN *THIS* Group");
	        connection = dbcm.getConnection();
	        dba = new DBAccess(connection);
	        String sql = "SELECT * FROM uso_users WHERE user_group = " + group_id;
	        ResultSet rs = dba.query(sql);
		    if(!rs.next()){
		    	rs.close();
			    dba.closeSelect();
			}else{
				rs.beforeFirst();
				while(rs.next())
				{				
					users.add(new UserBean(rs.getInt("uid"),
							rs.getInt("user_level"),
							rs.getString("user_name"),
							rs.getString("user_fullname"),
							rs.getInt("user_group"),
							rs.getInt("user_no_type"),
							rs.getString("user_no_value")));
				}
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
	public boolean addUser(int group_id, int user_id){
		return false;
	}

}
