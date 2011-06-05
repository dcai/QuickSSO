package cn.edu.ujn;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.phprpc.PHPRPCClient;
import org.phprpc.PHPRPCServer;

import cn.edu.ujn.dao.DAOFactory;
import cn.edu.ujn.dao.LoginDAO;
import cn.edu.ujn.util.MD5;
import cn.edu.ujn.bean.Authorization;

public class MgrAuth extends HttpServlet {


  private static final long serialVersionUID = 1L;
  static Logger log = Logger.getLogger(Signin.class);
  public HttpServletRequest request = null;
  public HttpSession session = null;
  cn.edu.ujn.dao.DAOFactory daof = DAOFactory.getDAOFactory();
  LoginDAO login  = daof.getLoginDAO();
  PHPRPCClient client = new PHPRPCClient();
  public void init(){
    log.info(">> MgrAuth INIT");
  }
  public int getMgr(String usr, String pwd)throws Exception{
    log.info(usr+" - "+pwd);
    pwd = MD5.compute(pwd);
    Authorization d = login.execute(usr, pwd);
    if(d.getUid()!=-1)
    {
      if(d.getLevel()==127)
      {
        this.session.setAttribute("MgrAuth", d);
        log.info(session.getId());
        return 1;
      }
    }
    return 0;  
  }
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    this.request = request;
    this.session = request.getSession(true);
    
    PHPRPCServer phprpc_server = new PHPRPCServer(request, response, session);
    phprpc_server.add("getMgr", this);
    log.info("getMgr");
    phprpc_server.start();
  }
  public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    log.info(">> DO POST");
    doGet(request, response);
  }
  public void destroy()
  {
  }
  public static void main(String[] args) 
  {
    System.out.println("");
  }
}