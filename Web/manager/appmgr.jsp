<%@include file="common.jsp"%><% 
ArrayList apps = null;
AppBean app = null;
if(a != null && a.getUid() != -1)
{
  DAOFactory daof = DAOFactory.getDAOFactory();
  AppDAO adao = daof.getAppDAO();
  apps  = adao.listAll();
}

%><html>
<head>
<style>
@import '../style.css';
</style>
<style>
#div_newapp{
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
function addNewapp(){
  $("#div_newapp").fadeIn("fast", function(){  
    $("#app_name").val("");
    $("#app_uri").val("");
    $("#app_ws").val("");  
  });
}
function closediv(str){
  $(str).fadeOut("fast", function(){});
}
function delApp(id)
{
  alert("Do you want to remove app "+ id + "?");
}
function editApp(id, name, ws, uri)
{
  $("#div_newapp").fadeIn("fast", function(){
    $("#app_name").val(name);
    $("#app_uri").val(uri);
    $("#app_ws").val(ws);  
  });
}
</script>
</head>
<body>
<h1 style="margin-top:80px">Application list</h1>
<div><input type="button" value="Add a new application" onclick="addNewapp()"/></div>
<br/>
<div id="div_newapp">
<table>
<tr><td colspan="2">&nbsp;</td></tr>
<tr><td colspan="2">You should give name your application.</td></tr>
<tr><th>Application Name: </th><td><input size="35" type="text" id="app_name" /></td></tr>
<tr><td colspan="2">This is home page of this application.</td></tr>
<tr><th>Application URI:  </th><td><input size="35" type="text" id="app_uri" /></td></tr>
<tr><td colspan="2">Web service used to auth user.</td></tr>
<tr><th>WEB Service URI:  </th><td><input size="35" type="text" id="app_ws" /></td></tr>
<tr align="right"><td colspan="2">
<input type="button" value="cancel" onclick="closediv('#div_newapp')" />
<input type="button" value="submit" /></td></tr>
</table>
</div>
<table>
<tr>
  <th>Application ID</th>
  <th>WebService URI</th>
  <th>Application URI</th>
  <th colspan="2">Operation</th>
</tr>
<%
Iterator it = apps.iterator();
while(it.hasNext())
{
  //out.println(it.next());
  app = (AppBean)it.next();

%>
<tr>
  <td><%=app.getId()%></td>
  <td><%=app.getWS()%></td>
  <td><%=app.getUri()%></td>
  <td><a href="###" onclick="delApp(<%=app.getId()%>)">delete</a></td>
  <td><a href="###" onclick="editApp(<%=app.getId()%>,'<%=app.getName() %>','<%=app.getWS()%>','<%=app.getUri()%>')">modify</a></td>
</tr>
<%
}
%>
</table>
</body>
</html>