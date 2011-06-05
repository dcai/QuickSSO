<%@ page contentType="text/html;charset=gb2312" %>
<%@ page import="org.phprpc.*" %>
<% 
PHPRPCClient phprpc_client = new PHPRPCClient();
phprpc_client.useService("http://127.0.0.1:8080/uso/admin/svr.jsp", true);
out.println(phprpc_client.invoke("auth", new Object[] { new String("admin"), new String("admin") }));
%>