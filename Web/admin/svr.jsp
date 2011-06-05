<%@ page import="org.phprpc.*" %>
<%@ page import="cn.edu.ujn.admin.*" %>
<% 
PHPRPCServer phprpc_server = new PHPRPCServer(request, response, session);
adminAuth a = new adminAuth();
a.setSession(session);
phprpc_server.add("chgPwd", a);
phprpc_server.add("editUsr",a);
phprpc_server.start();
%>