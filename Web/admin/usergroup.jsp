<%@include file="../common.jsp"%> 
<%
UserBean    uinfo = null;
GroupBean   group = null;
adminAuth adminauth  =  new adminAuth(); 
boolean result = false;
if(a != null && a.getUid() != -1)
{ 
  DAOFactory daof = DAOFactory.getDAOFactory();
  UserDAO  udao = daof.getUserDAO();
  GroupDAO gdao = daof.getGroupDAO();
  uinfo  = udao.execute(a.getUid());
  group = gdao.execute(uinfo.getGroup());
  result = adminauth.checkGroupAuth(uinfo);
}
if(!result){
	response.sendRedirect("overview.jsp"); 
}

%><html><head><title></title><style>
@import '../style.css';
</style>
<style>
td p{text-align:right}
</style>
</head>
<body>
<h1 style="margin-top:80px">User Groups Information</h1>

<table width="60%">
<tr><th>ID</th><th>Name</th><th>Group Moderator</th><th>Group Info</th><th>Details</th></tr>

<% 

if(result){
  ArrayList groups = adminauth.getGroupList(uinfo);
  if(groups != null){
    Iterator it = groups.iterator();
    while(it.hasNext()){
     GroupBean gs = (GroupBean)it.next();
%>

<tr><td><%=gs.getGroupId() %></td>
<td><%=gs.getGroupName()%></td>
<td><%=gs.getGroupModerator() %></td>
<td><%=gs.getGroupDesc() %></td>
<td><a href="ugdetail.jsp?gid=<%=gs.getGroupId()%>">Enter</a></td>
</tr>
<%
    }
  }

}
else
  out.println("fail");

%>
</table>
</body>
</html>