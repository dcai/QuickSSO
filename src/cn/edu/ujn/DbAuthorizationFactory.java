
package cn.edu.ujn; 

import org.apache.log4j.Logger;
import org.phprpc.PHPRPCClient;
import org.phprpc.PHPRPCError;
 
import cn.edu.ujn.bean.AppBean;
import cn.edu.ujn.bean.Authorization;
import cn.edu.ujn.bean.DbAuthorization;
import cn.edu.ujn.bean.MapBean;
import cn.edu.ujn.dao.AppDAO;
import cn.edu.ujn.dao.DAOFactory;
import cn.edu.ujn.dao.LoginDAO;
import cn.edu.ujn.dao.MapDAO;
import cn.edu.ujn.util.MD5;
import cn.edu.ujn.util.RandomGUID;
import cn.edu.ujn.util.StringUtil;
 
public class DbAuthorizationFactory extends AuthorizationFactory
{ 
  static Logger log = Logger.getLogger(DbAuthorizationFactory.class);
  DAOFactory daof = DAOFactory.getDAOFactory();
  AppDAO adao     = daof.getAppDAO();
  MapDAO mdao     = daof.getMapDAO();
  LoginDAO login  = daof.getLoginDAO();
  PHPRPCClient client = new PHPRPCClient();
  String url = null;
  public DbAuthorizationFactory()
  {
  }
  public Authorization getAuthorization(int app_id)throws Exception{
    Authorization d = (Authorization)session.getAttribute("usr_info");
    AppBean    app  = adao.execute(app_id);
    int msg = 0;
    if(d != null)
    {
      if(d.getUid() != -1)
      {
        MapBean m   = mdao.execute(app_id, d.getUid());
        String guid        = new RandomGUID().toString();
        if(m.getStatus() == 200)
        {
          log.info(app.getWS());
          client.useService(app.getWS(), false);
          msg = Integer.parseInt(client.invoke("auth", new Object[] { m.getUsr(), m.getPwd(), guid}).toString());
          if(msg == 200)
          {
            d.setGuid(guid);
            d.setWS(app.getWS());
            d.setUrl(app.getUri());
            return d;
          }else{
            return new DbAuthorization("app1 not allow this user logging in.");
          }
        }else{
          return new DbAuthorization("can not find mapping");
        }
      }
    }

    return new DbAuthorization("just for testing");
  }
  public Authorization getAuthorization(String usr, String pwd, int app_id, boolean remember)
    throws Exception
  {
    usr = StringUtil.escapeSQLTags(usr);
    pwd = StringUtil.escapeSQLTags(pwd);
    if (StringUtil.nullOrBlank(usr)||StringUtil.nullOrBlank(pwd)){
      return new DbAuthorization("Username and password should not be null.");
    }
    String guid = null;
    pwd = MD5.compute(pwd);
    // check if user have the right to access uso system
    // if user have that right, userid != -1
    Authorization d = login.execute(usr, pwd);
    int msg = 0;
    if(d.getUid() != -1) // found user info in uso main database
    {
      AppBean    app  = adao.execute(app_id);
      session.setAttribute("usr_info",d);  
      //if(remember == true)
        //d.setPwd(pwd);
      if(app_id == 0){      // user want to sign in admin panel
        d.setUrl("admin");
        return d;
      } else {                 //  user want to signin app X
        MapBean m   = mdao.execute(app_id, d.getUid());
        guid        = new RandomGUID().toString();
        //if(remember == true)
        //{d.setPwd(pwd);}
        if(m.getStatus() == 200)
        {
          client.useService(app.getWS(), false);
          Object result = null;
          try{
        	  result = client.invoke("auth", new Object[] { m.getUsr(), m.getPwd(), guid});
          }catch(Exception ex){ 
        	  log.info("Remote web service is not available.");
        	  return new DbAuthorization("Remote web service is not available.");
          }
          if(result instanceof PHPRPCError)
          {
        	  log.info("Remote web service is not available.");
        	  return new DbAuthorization("Remote web service is not available.");
          }
          msg = Integer.parseInt(result.toString());
          if(msg == 200)
          {
            log.info("Remote webservice authorizated. ");
            d.setGuid(guid);
            d.setWS(app.getWS());
            d.setUrl(app.getUri());
            return d;
          }else{
            log.info("App1 forbid");
            return new DbAuthorization("app1 not allow this user logging in.");
          }
        }else{
          return new DbAuthorization("There is no mapping in United-Sign-On system. ");
        }
      }
    }else{
      return new DbAuthorization("Wrong password or username. ");
    }
  }
}