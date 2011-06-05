package cn.edu.ujn.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.sql.ResultSet;

import org.apache.log4j.Logger;
import cn.edu.ujn.bean.*;
import cn.edu.ujn.bean.AuthBean;
import cn.edu.ujn.db.DBAccess;
import cn.edu.ujn.db.DBConnectionManager;

public class MysqlAuthDAO implements AuthDAO {

	static public Logger log = Logger.getLogger(MysqlAuthDAO.class);
	private DBConnectionManager dbcm = DBConnectionManager.getInstance();
	Connection connection = null;
	DBAccess dba = null;
	public AuthBean execute(UserBean user, GroupBean group) {
		try{
	        boolean view = false;
	        boolean add  = false;
	        boolean del  = false;
	        boolean edit = false;
	        connection = dbcm.getConnection();
	        int src = user.getGroup();
	        int target = group.getGroupId();
	        dba = new DBAccess(connection);
	        String sql = "SELECT * FROM uso_groups WHERE group_moderator = "+user.getUid();
	        ResultSet rs = dba.query(sql);

	        if(!rs.next()){
		    	rs.close();
		    	dba.closeSelect();
		    }else{
		    	dbcm.freeConnection(connection);
		    	return new AuthBean(true, true, true, true);
		    }
	        
	        sql = "SELECT auth_view, auth_add, auth_del, auth_edit FROM uso_group_auth " +
    		"WHERE group_id = " + src+" AND target_group_id = " + target +
    		" UNION " + "" + 
    		"SELECT auth_view, auth_add, auth_del, auth_edit FROM uso_user_auth " +
    		"WHERE uid = "+user.getUid() +" AND target_group_id = " + target;

	        rs = dba.query(sql);
	        
	        if(!rs.next()){
	          rs.close();
	          dba.closeSelect();
	        }else{
		        rs.beforeFirst();
		        while(rs.next())
		        {
		        	view = (rs.getInt("auth_view") == 1 ? true : false)||view;
		        	add  = (rs.getInt("auth_add")  == 1 ? true : false)||add;
		        	edit = (rs.getInt("auth_edit") == 1 ? true : false)||edit;
		        	del  = (rs.getInt("auth_del")  == 1 ? true : false)||del;
		        }
	        }
	        dbcm.freeConnection(connection);
	        return new AuthBean(view, add, del, edit);
	      }catch(Exception ex){
	        return new AuthBean();
	      }finally{
	    	  try{
	    		  dbcm.freeConnection(connection);
	    	  }catch(Exception ex){
	    		  
	    	  }
	      }
	}
	public ArrayList execute(UserBean user){
		ArrayList groups = new ArrayList();
        DAOFactory daof = DAOFactory.getDAOFactory();
        GroupDAO gdao = daof.getGroupDAO();
        int src = user.getGroup();
        try{
	        connection = dbcm.getConnection();
	        String sql = "SELECT target_group_id,auth_add,auth_edit FROM uso_group_auth " +
    		"WHERE group_id = " + src +
    		" UNION " + "" + 
    		"SELECT target_group_id,auth_add,auth_edit FROM uso_user_auth " +
    		"WHERE uid = "+user.getUid();
	        dba = new DBAccess(connection);
	        ResultSet rs = dba.query(sql);
	        if(!rs.next()) {
		          rs.close();
		          dba.closeSelect();
	        }else{
	        	rs.beforeFirst();
	        	while(rs.next())
	        	{
	        		if(rs.getInt("auth_add") == 1 || rs.getInt("auth_edit") == 1){
	        			groups.add(gdao.execute(rs.getInt("target_group_id")));
	        		}
	        	}
	        }
	        sql = "SELECT group_id FROM uso_groups WHERE group_moderator = "+user.getUid();
	        rs = dba.query(sql);
	        if(!rs.next()) {
		          rs.close();
		          dba.closeSelect();
	        }else{
	        	rs.beforeFirst();
	        	while(rs.next())
	        	{
	        		groups.add(gdao.execute(rs.getInt("group_id")));
	        	}
	        }
	        dbcm.freeConnection(connection);
		}catch(Exception ex){
			log.info(ex.toString());
		}finally{
			try{
				dbcm.freeConnection(connection);
			}catch(Exception ex){
				
			}
		}
		return groups;
	}
}
