<%@ page import="cn.edu.ujn.bean.Authorization" %>
<%
  Authorization a = (Authorization)session.getAttribute("usr_info");
  if (a != null && a.getUid() != -1)
  { 
    response.sendRedirect("panel.jsp");
  }else{
    response.sendRedirect("../");
  }
%>