<%@include file="../common.jsp"%> 
<%
UserBean    uinfo = null;
GroupBean   group = null;
adminAuth adminauth  =  new adminAuth();
GroupDAO gdao = null;
UserDAO  udao= null;
GroupProxy gproxy = null;
boolean result = false;
if(a != null && a.getUid() != -1)
{ 
  DAOFactory daof = DAOFactory.getDAOFactory();
  udao = daof.getUserDAO();
  gdao = daof.getGroupDAO();
  uinfo  = udao.execute(a.getUid());
  group = gdao.execute(uinfo.getGroup());
  result = adminauth.checkGroupAuth(uinfo);}
if(!result || !gproxy.canEdit()){
  response.sendRedirect("overview.jsp");
}

%>

edit user heere