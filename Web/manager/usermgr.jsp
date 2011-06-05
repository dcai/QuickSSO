<%@include file="common.jsp"%><%
int total = 0;
UserBean user = null;
ArrayList users = null;
UserDAO udao = null;
if(a != null && a.getUid() != -1)
{
  DAOFactory daof = DAOFactory.getDAOFactory();
  udao = daof.getUserDAO();
  AppDAO adao = daof.getAppDAO();
  total = udao.getTotal();
} 
int offset = 0;
String str_page = null;
str_page = request.getParameter("p");
if(str_page != null )
{
	offset = Integer.parseInt(str_page); 
}
if(a != null && a.getUid() != -1)
  users = udao.getUserList(offset);
int TPages = (int)Math.ceil((double)total/udao.getMaxLine());
int CPages = (int)Math.floor((double)offset/udao.getMaxLine()+1); 
%><html>
<head>
<style>
@import '../style.css';
</style>
<style>
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
<script src="../jquery.js"></script>
<script>
// TODO  add web service
function addNewuser(){
  $("#div_newuser").fadeIn("fast", function(){ 
    $("#user_name").val("");
    $("#user_fname").val("");
    $("#user_level").val("");
    $("#no_type").val("");
    $("#no_value").val("");
  });
}
function closediv(str){
  $(str).fadeOut("fast", function(){});
}
function delUser(id)
{
  alert("Do you want to remove app "+ id + "?");
}
function editUser(id)
{

  $("#div_newuser").fadeIn("fast", function(){
    $("#user_name").val($("#u_"+id).html());
    $("#user_fname").val($("#f_"+id).html());
    $("#user_level").val($("#l_"+id).html());
    $("#no_type").val($("#t_"+id).html());
    $("#no_value").val($("#v_"+id).html());
  });
}
</script>
</head>
<body>
<h1 style="margin-top:80px">Users list</h1>
<input type="button" onclick="addNewuser()" value="add new user" />
<div id="div_newuser">
<table>
<tr><td colspan="2">&nbsp;</td></tr>
<tr><th>new password:</th><td><input size="35" type="password" id="pwd1" /><td></td>
<tr><th>confirm password:</th><td><input size="35" type="password" id="pwd2" /><td></td>
<tr><th>fullname: </th><td><input size="35" type="text" id="user_name" /></td></tr>
<tr><th>usersgroup:  </th><td><input size="35" type="text" id="user_fname" /></td></tr>
<tr><th>userlevel:  </th><td><input size="35" type="text" id="user_level" /></td></tr>
<tr><th>no type:  </th><td><input size="35" type="text" id="no_type" /></td></tr>
<tr><th>no value:  </th><td><input size="35" type="text" id="no_value" /></td></tr>
<tr align="right"><td colspan="2">
<input type="button" value="cancel" onclick="closediv('#div_newuser')" />
<input type="button" value="submit" /></td></tr>
</table>
</div>
<p>Total users:<%=total %></p>
<div>
<%
for(int i = 1;i<=TPages;i++){
  if(i!=CPages)
    out.print("[<a href=\"usermgr.jsp?p="+(i-1)*udao.getMaxLine()+"\">"+i+"</a>] ");
  else
    out.print("["+i+"] ");
}
%>
</div>
<table>
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
Iterator it = users.iterator();

while(it.hasNext())
{ 
  user = (UserBean)it.next();
  int id = user.getUid();
%>
<tr>
  <td><%=id %></td>
  <td id="u_<%=id %>"><%=user.getName() %></td>
  <td id="f_<%=id %>"><%=user.getFullname() %></td>
  <td id="g_<%=id %>"><%=user.getGroup() %></td>
  <td id="l_<%=id %>"><%=user.getUserlevel() %></td>
  <td id="t_<%=id %>"><%=user.getNoType() %></td>
  <td id="v_<%=id %>"><%=user.getNoValue() %></td>
  <td><a href="###" onclick="delUser(<%=user.getUid() %>)">delete</a></td>
  <td><a href="###" onclick="editUser(<%=user.getUid() %>)">modify</a></td>
</tr>
<%
}
%>
</table>
</body>
</html>