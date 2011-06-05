<%@ page language="java" import="java.util.*, cn.edu.ujn.*, cn.edu.ujn.admin.*, cn.edu.ujn.bean.*,cn.edu.ujn.util.*, cn.edu.ujn.dao.*"%><%
//response.setDateHeader("Expires", (new Date()).getTime());
//response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate, max-age=0");
Authorization a = (Authorization)session.getAttribute("usr_info");
String version= "0.9 beta";
boolean logged = false;

if(a != null && a.getUid() != -1)
{
  logged = true;
}
else{
  logged = false;
}

String referer = request.getHeader("referer");
//CookieWrap ck = CookieWrap.getInstance(request,response);
//ck_username = ck.getCookieValue( "USO_USR" );
//ck_password = ck.getCookieValue( "USO_PWD" );
//out.println( ck_password );
//out.println(session.getAttribute("login_a"));
%>