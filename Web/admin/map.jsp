<%@include file="../common.jsp"%><% 
ArrayList apps = null;
AppBean app = null;
MapDAO mdao = null;

if(a != null && a.getUid() != -1)
{
  DAOFactory daof = DAOFactory.getDAOFactory();
  AppDAO adao = daof.getAppDAO();
  mdao = daof.getMapDAO();
  apps  = adao.listAll();
}
%><html>
<head>
<style>
@import '../style.css';
</style>
</head>
<body>
<h1 style="margin-top:80px">Application list</h1>
<table>
<tr>
  <th>Application ID</th>
  <th>Application Name</th>
  <th>Application URI</th>
  <th>User Status</th>
  <th colspan="2">Operation</th>
</tr>
<%
Iterator it = apps.iterator();
while(it.hasNext())
{
  //out.println(it.next());
  app = (AppBean)it.next();
  MapBean map = mdao.execute(app.getId(), a.getUid());
%>
<tr>
  <td><%=app.getId()%></td>
  <td><%=app.getName()%></td>
  <td><%=app.getUri()%></td>
  <td><%
  if(map.getStatus()==200)
  {
   out.println("<span class=\"color\">Mapped</span>"); 
  }else{
   out.println("<span class=\"cr_red\">no map</span>"); 
  }
	  
    %></td>
  <td><% 
  if(map.getStatus()==200){
    //out.println("<a href=\"###\">Edit</a> <a href=\"###\">Delete</a>");
  } else {
   out.println("<a href=\"###\">Add</a>"); 
  }%></td>
</tr>
<%
}
%>
</table>
</body>
</html>