<%@include file="common.jsp"%><html>
<head>
<title>Unified-Sign-On System</title>
<style>
  @import 'style.css';
</style>
<script src="jquery.js"></script>
<script src="phprpc_client.js"></script>
<script src="login.js"></script>
</head>
<body>
<div id="header"></div>
<div id="nav"><%
if(logged == true){
  out.println("Welcome, " + a.getName());
%> | <a href="admin/panel.jsp">AdminPanel</a> | <a href="admin/logout.jsp">Logout</a>&nbsp;
<%
}
%>
</div>
<% out.println(session.getId());%>
<table width="100%" id="layout">
<tr>
<td width="45%" valign="top">
<div id="left" style="margin-right: 3em;text-align:right">
<span style="color:gray"><%out.println(version);%></span><img src="img/logo.gif" alt="Unified-Sign-On System" />
<p>ujn.edu.cn authorized</p>
<img src="img/external.png" /> <a href="howto.jsp" target="_blank">HOW TO</a>
<p class="intro">Powered by Unified-Sign-On System</p>
<p class="intro"><a href="http://www.phprpc.org/">PHPRPC</a> Inside</p>
<p>
</div>
</td>
<td>
<%
boolean tmp = false;
int app_id = 0;
if(request.getParameter("app_id") != null)
{
  tmp = true;
  app_id = Integer.parseInt(request.getParameter("app_id"));
}
String app_uri = null;
AppBean app = null;
DAOFactory daof = null;
AppDAO adio = null;
if( tmp && app_id != 0 ){
  daof = DAOFactory.getDAOFactory();
  adio = daof.getAppDAO();
  app = adio.execute(app_id);
  if(app!=null)
  {
    app_uri = app.getUri();
  }
}
if (logged)
{
%>
<div id="right">
<table>
<tr><td class="td_title">status:   </td><td><span class="color">online</span></td><tr>
<tr><td class="td_title">username: </td><td><%out.println(a.getName());%></td><tr>
<tr><td class="td_title">info:     </td><td><%out.println(a.getInfo());%></td><tr>
<tr><td colSpan="2">&nbsp;<input type="hidden" id="referer" value="<%=referer%>" /></td></tr>
<%
  if(app_uri!=null)
  {
%>
<tr><td><input type="button" value="Sign In" class="btn" id="goto" title="<%=app_uri%>"/></td><td><div id="redirect"></div></td></tr>
<%}%>
</table>
</div>
<%
}else{
%>
<div id="right">
  <form name="loginform" id="loginform" action="signin" method="post" onkeyup="onEnter(event)">
  <table>
  <tr>
  <td style="text-align:right">
      USER ID: 
  </td>
  <td>
      <input type="text" name="usr" id="username" value="" size="25" tabindex="1" />
  </td>
  </tr>
  <tr>
  <td style="text-align:right">
      Password: 
  </td>
  <td>
      <input type="password" name="pwd" id="password" value="" size="25" tabindex="2" />
  </td>
  </tr>
  <tr>
  <td></td>
  <td>
      <%
        if( tmp && app_id != 0 )
        {
          if(app!=null)
          {
      %>
      <script>app_id=<%=app_id%>;</script>
          <label><input type="radio" name="app" value="1" checked="true" tabindex="3" />Sign in to <%out.println(app.getUri());%></label><br/>
          <label><input type="radio" name="app" value="0" tabindex="4" />Sign in to account setting</label>
      <%
          }else{            
            out.println("<strong style=\"color:red;\">invalid application id or name</strong><br/>");
            out.println("<label><input type=\"radio\" name=\"app\" value=\"0\" checked=\"true\" tabindex=\"3\"/>Sign in to account setting</label>");
          }
      %>
      <%
        }else{
      %>
      <label><input type="radio" name="app" value="0" checked="true" tabindex="3"/>Sign in to account setting</label>
      <%
        }
      %>
  </td>
  </tr>
  <tr>
  <td></td>
  <td>
    <p><label><input id="remember" type="checkbox" value="forever" tabindex="6" /> remember me</label></p>
    <p class="submit">
      <input type="button" value="Sign In &raquo;" class="btn" tabindex="7" id="submit" />
      <input type="hidden" id="referer" value="<%=request.getHeader("referer")%>" />
    </p>
  </td>
  </tr>
  </table>
  </form>
</div>
<%
}
%>
</td>
</tr>
</table>
<%@include file="footer.jsp"%>
</body>
</html>