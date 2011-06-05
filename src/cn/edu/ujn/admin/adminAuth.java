package cn.edu.ujn.admin;
import java.sql.Connection;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.ujn.bean.Authorization;
import cn.edu.ujn.dao.DAOFactory;
import cn.edu.ujn.dao.LoginDAO;
import cn.edu.ujn.db.DBAccess;
import cn.edu.ujn.db.DBConnectionManager;
import cn.edu.ujn.util.MD5;
import cn.edu.ujn.dao.*;
import java.sql.ResultSet;
import java.util.*;
import cn.edu.ujn.bean.*;


public class adminAuth
{
  static Logger log = Logger.getLogger(adminAuth.class);
  Connection connection = null;
  private HttpSession        session = null;
  private DBConnectionManager dbcm = DBConnectionManager.getInstance();
  DBAccess dba = null;
  public adminAuth(){
  }
  public void setSession(HttpSession session)
  {
    this.session = session;
  }
  public ArrayList getGroupList(UserBean user)
  {
	  ArrayList groups = new ArrayList();
	  ResultSet rs = null;
	  try{
		  connection = dbcm.getConnection();
		  dba = new DBAccess(connection);
		  String sql = "SELECT * FROM uso_groups WHERE group_id IN "+
		  	"(SELECT target_group_id FROM uso_user_auth WHERE uid = "+user.getUid()+") "+
		  	"UNION "+
		  	"SELECT * FROM uso_groups WHERE group_moderator = "+ user.getUid()+ 
		  	" UNION "+
		  	"SELECT * FROM uso_groups WHERE group_id in "+
		  	"(SELECT target_group_id FROM uso_group_auth WHERE group_id=1)";
		  rs = dba.query(sql);
		  if(!rs.next()){
			  rs.close();
			  dba.closeSelect();
			  dbcm.freeConnection(connection);
		  }
		  rs.beforeFirst();
		  while(rs.next())
		  {
			  groups.add(new GroupBean(rs.getInt("group_id"),
					  rs.getString("group_name"),
					  rs.getInt("group_type"),
					  rs.getInt("group_moderator"),
					  rs.getString("group_desc")));
		  }
		  dbcm.freeConnection(connection);
		  return groups;
	  }catch(Exception ex){
		  return null;
	  }finally{
	      try{
	    	  dbcm.freeConnection(connection);
	      }catch(Exception ex)
	      {
	      }
	    }
  }
  public boolean checkGroupAuth(UserBean user)
  {
	  boolean result = false;
	  ResultSet rs = null;
	  try{
		  log.info("Check group auth");
		  connection = dbcm.getConnection();
		  dba = new DBAccess(connection);
		  String sql = "SELECT * FROM uso_groups WHERE group_moderator = " + user.getUid();
		  rs = dba.query(sql);
		  if(!rs.next()){
			  rs.close();
			  dba.closeSelect();
			  result = false;
		  }else{
			  dbcm.freeConnection(connection);
			  return true;
		  }

		  sql = "SELECT auth_view,auth_add,auth_del,auth_edit FROM uso_user_auth " +
		  		"where uid = " + user.getUid() +
		  		" UNION "+
		  		"SELECT auth_view,auth_add,auth_del,auth_edit FROM uso_group_auth " +
		  		"where group_id = " + user.getGroup();
		  rs = dba.query(sql);
		  if(!rs.next()){
			  rs.close();
			  dba.closeSelect();
			  result = false;
		  }else{
			  dbcm.freeConnection(connection);
			  return true;
		  }
		  return result;
	  }catch(Exception ex){
		  return false;
	  }finally{
	  }
  }
  public int chgPwd(String old, String newone)throws Exception{
    log.info("change password");
    connection = dbcm.getConnection();
    dba = new DBAccess(connection);
    DAOFactory daof = DAOFactory.getDAOFactory();
    LoginDAO login= daof.getLoginDAO();
    Authorization a = (Authorization)session.getAttribute("usr_info");
    log.info("get session");
    String name = null;
    int uid = -1;
    if(a != null)
    {
      name = a.getName();
      uid = a.getUid();
    }else{
      return 404;
    }
    Authorization d = login.execute(name, MD5.compute(old));
    if(d.getUid() != -1)
    {
      newone = MD5.compute(newone);
      String sql = "update `uso_users` set user_password='"+newone+"' where `uid` = '"+uid+"'";
      dba.update(sql);
      session.removeAttribute("usr_info");
      return 200;
    }else{
      return 404;
    }    
  }
  public int editUsr(int uid, String name, String pwd, String fullname,
		  int group_id, int no_type, String no_value)
  {
	  log.info("user: "+fullname);
	  log.info("password: "+pwd);
	  Authorization a = (Authorization)session.getAttribute("usr_info");
	  if(a == null)
		  return 403;
	  DAOFactory daof = DAOFactory.getDAOFactory();
	  UserDAO udao    = daof.getUserDAO();
	  GroupDAO gdao   = daof.getGroupDAO();
	  AuthDAO authdao = daof.getAuthDAO();
	  UserBean tmpu  = udao.execute(a.getUid());
	  GroupBean tmpg = gdao.execute(group_id);
	  if(tmpu==null||tmpg==null)
		  return 404;
	  AuthBean authbean = authdao.execute(tmpu, tmpg);
	  UserBean uinfo = new UserBean();
	  uinfo.setUid(uid);
	  if(uid == -1){
		  uinfo.setName(name);
	  }
	  if(pwd.equals("")){
		  uinfo.setPwd("");
		  log.info("keep orignal password");
	  }else{
		  log.info("change pwd");
		  uinfo.setPwd(MD5.compute(pwd));
	  }
	  uinfo.setFullname(fullname);
	  uinfo.setGroup(group_id);
	  uinfo.setNoType(no_type);
	  uinfo.setNoValue(no_value);
	  if(authbean.canEdit()){
		  udao.editUser(uinfo);
		  return 200;
	  }else{
		  return 403;
	  }

  }
}