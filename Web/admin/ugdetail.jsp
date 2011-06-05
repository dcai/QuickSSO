<%@include file="../common.jsp"%> 
<%
UserBean    uinfo = null;
GroupBean   group = null;
AuthDAO authdao = null;
adminAuth adminauth  =  new adminAuth();
GroupDAO gdao = null;
boolean result = false;
String str_gid = request.getParameter("gid");
int gid = -1;
if(str_gid != null || !str_gid.equals("")){
  gid = Integer.parseInt(str_gid);
}else{
  gid = -1; 
}
if(a != null && a.getUid() != -1)
{ 
  DAOFactory daof = DAOFactory.getDAOFactory();
  UserDAO  udao = daof.getUserDAO();
  gdao = daof.getGroupDAO();
  authdao = daof.getAuthDAO();
  uinfo  = udao.execute(a.getUid());
  group = gdao.execute(uinfo.getGroup());
  result = adminauth.checkGroupAuth(uinfo);
}
GroupBean targetGroup = gdao.execute(gid);
GroupProxy gproxy = new GroupProxy(gdao, uinfo, targetGroup);
if(!result){
  response.sendRedirect("overview.jsp"); 
}
ArrayList augroups = authdao.execute(uinfo);

%><html><head><title></title><style>
@import '../style.css';
</style>
<style>
td p{text-align:right}
#div_auth{
  position:absolute;
  top:120px;
  right:0;
  width:150px;
  background:gray;
  color: white;
}
.enable{
  color:green;
}
.disable{
  color:red;
}
#div_newuser{
  margin:0;
  padding:0;
  position:absolute;
  background: #E0FFFF;
  float:right;
  left:150px;
  top:150px;
  display:none;
}
</style>
<script src="../phprpc_client.js"></script>
<script src="../jquery.js"></script>
<script src="groupmgr.js"></script>
</head>
<body>
<h1 style="margin-top:80px">User Group details</h1>
<div id="div_newuser">
<form action="editusr.jsp" METHOD="post">
<table>
<tr><td colspan="2"><input type="hidden" id="u_id" value=""/></td></tr>
<tr><th>new password:</th><td>
<input size="35" type="password" id="pwd1" /><td></td>
<tr><th>confirm password:</th><td>
<input size="35" type="password" id="pwd2" /><td></td>
<tr><th>login name: </th><td>
<input size="35" type="text" id="user_name" /></td></tr>
<tr><th>fullname: </th><td>
<input size="35" type="text" id="full_name" /></td></tr>
<tr><th>usersgroup:  </th><td>
<SELECT NAME="Cats" id="user_group" SIZE="1"><%
Iterator it = augroups.iterator();
while(it.hasNext())
{
  GroupBean groupbean = (GroupBean)it.next();
  out.println("<OPTION VALUE='"+groupbean.getGroupId()+"'>"+groupbean.getGroupName()+"</OPTION>"); 
}%></SELECT></td></tr>
<tr><th>no type:  </th><td>
<input size="35" type="text" id="no_type" /></td></tr>
<tr><th>no value:  </th><td>
<input size="35" type="text" id="no_value" /></td></tr>
<tr align="right"><td colspan="2">
<input type="button" value="cancel" onclick="closediv('#div_newuser')" />
<input type="button" value="submit" id="submit"/></td></tr>
</table>
</form>
</div>
<div id ="div_auth">
<table>
<tr><td>Delete</td><td><%if(gproxy.canDel())out.print("<span class='enable'>enable</span>");else out.print("<span class='disable'>disable</span>"); %></td></tr>
<tr><td>View</td><td><%if(gproxy.canRead())out.print("<span class='enable'>enable</span>");else out.print("<span class='disable'>disable</span>"); %></td></tr>
<tr><td>Add</td><td><%if(gproxy.canAdd())out.print("<span class='enable'>enable</span>");else out.print("<span class='disable'>disable</span>"); %></td></tr>
<tr><td>Edit</td><td><%if(gproxy.canEdit())out.print("<span class='enable'>enable</span>");else out.print("<span class='disable'>disable</span>"); %></td></tr>
</table>
</div>
<%
if(gproxy.canAdd()){
%>
  <input type="button" value="Add a new user" onclick="addNewuser()"/>
<%
} 
if(gproxy.canRead())
{
	ArrayList users = gproxy.getUsers(gid);
%>
<table width="80%">
<tr>
  <th>User ID</th>
  <th>User name</th>
  <th>Full name</th>
  <th>user group</th>
  <th>User level</th>
  <th>user_no_type</th>
  <th>user_no_value</th>
  <th colspan="2">Operation</th>
</tr>
<%
if(users!=null){
  it = users.iterator();
  while(it.hasNext()){
    UserBean user = (UserBean)it.next();
    int id = user.getUid();
%>

<tr>
  <td><%=id %></td>
  <td id="u_<%=id %>"><%=user.getName() %></td>
  <td id="f_<%=id %>"><%=user.getFullname() %></td>
  <td id="g_<%=id %>"><%=user.getGroup() %></td>
  <td id="t_<%=id %>"><%=user.getNoType() %></td>
  <td id="v_<%=id %>"><%=user.getNoValue() %></td>
  <td><%if(gproxy.canDel()){ %><a href="delusr.jsp?uid=<%=id %>" onclick="return confirm('You do! Do you?');">Delete</a><%} %></td>
  <td><%if(gproxy.canEdit()){ %><a href="###"  onclick="editUser(<%=user.getUid() %>)">Edit</a><%} %></td>
</tr>
<%
  }
  
}



%>
</table>
<%
}
%>
</body>
</html>