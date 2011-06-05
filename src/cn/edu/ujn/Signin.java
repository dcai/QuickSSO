package cn.edu.ujn;

import org.apache.log4j.Logger;
import org.phprpc.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import cn.edu.ujn.AuthorizationFactory; 


public class Signin extends HttpServlet 
{
  private static final long serialVersionUID = 1L;
  static Logger log = Logger.getLogger(Signin.class);
  cn.edu.ujn.AuthorizationFactory factory = null;
  public void init(){
    log.info(">> SERVLET INIT");
    //ServletConfig config = getServletConfig();
    //String appname = config.getInitParameter("app");
    this.factory = AuthorizationFactory.getInstance();
  }
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {
    HttpSession session = request.getSession(true);
    this.factory.setSession(session);
    this.factory.setRequest(request);
    PHPRPCServer phprpc_server = new PHPRPCServer(request, response, session);
    phprpc_server.add("getAuthorization", this.factory);
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
