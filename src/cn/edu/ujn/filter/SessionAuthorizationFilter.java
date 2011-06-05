package cn.edu.ujn.filter;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionAuthorizationFilter implements Filter {
  protected FilterConfig filterConfig;
  public void init(FilterConfig config) {
    this.filterConfig = config;
  }
  public void destroy() {
    this.filterConfig = null;
  }


  private boolean passed(ServletRequest request, ServletResponse response)
      throws IOException, ServletException {
    boolean result=false;
    String session_name=this.filterConfig.getInitParameter("session_name");
    HttpServletRequest req = (HttpServletRequest)request;
    HttpSession session =req.getSession(false);
    if(session!=null){
      Object object=session.getAttribute(session_name);
      if(object!=null)
        result= true;
    }
      return result;
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
      throws IOException, ServletException{    
    if (passed(request,response))
        filterChain.doFilter(request, response);
    else{
      HttpServletResponse res = (HttpServletResponse)response;
      String errorPage=this.filterConfig.getInitParameter("ERROR_PAGE");
      res.sendRedirect(errorPage);
    }
    
  }
}