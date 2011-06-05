<%@ page language="java" import="java.util.*, cn.edu.ujn.*, cn.edu.ujn.bean.*,cn.edu.ujn.util.*, cn.edu.ujn.dao.*"%>
<%
  DAOFactory daof = DAOFactory.getDAOFactory();
  AppDAO adio = daof.getAppDAO();
  AppBean app = adio.execute(1);
  out.println(app.getUri());
%>