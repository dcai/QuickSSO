<%@ page language="java" import="java.util.*,java.sql.*,cn.edu.ujn.dao.*,cn.edu.ujn.bean.*,cn.edu.ujn.*,cn.edu.ujn.util.*"%><%
String version= "0.9 beta"; 
response.setHeader("Pragma", "No-cache");
response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
response.setDateHeader("Expires", 0);
Authorization a = (Authorization)session.getAttribute("MgrAuth");
boolean logged = false;
if(a != null && a.getUid() != -1)
  logged = true;
else
  logged = false;
%>