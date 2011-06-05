<%@include file="common.jsp"%><%
UserBean   uinfo = null;
GroupBean   group = null;
if(a != null && a.getUid() != -1)
{
  DAOFactory daof = DAOFactory.getDAOFactory();
  UserDAO udao = daof.getUserDAO();
  GroupDAO gdao = daof.getGroupDAO();

  uinfo  = udao.execute(a.getUid()); 
  group = gdao.execute(uinfo.getGroup());
}

%><html>
<head>
<style>
@import '../style.css';
</style>
</head>
<body>
<h1 style="margin-top:80px">manager info</h1>
<table width="30%">
<tr><td colspan="2"></td></tr>
<tr><td><p>login name: </p></td><td><%=a.getName()%>&nbsp;</td></tr>
<tr><td><p>full name: </p></td><td><%=uinfo.getFullname()%>&nbsp;</td></tr>
<tr><td><p>department: </p></td><td><%=group.getGroupName() %></td></tr>
<tr><td><p>certificate type: </p></td><td><%=uinfo.getNoType() %></td></tr>
<tr><td><p>certificate NO.: </p></td><td><%=uinfo.getNoValue() %></td></tr>
<tr><td><p>User Level:</p></td><td><%=uinfo.getUserlevel() %></td></tr>
</table>
</body>
</html>