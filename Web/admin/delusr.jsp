<%@include file="../common.jsp"%> 
<%
UserBean    uinfo      = null;
GroupBean   group      = null;
adminAuth   adminauth  = new adminAuth();
GroupDAO    gdao       = null;
UserDAO     udao       = null;
boolean     result     = false;
String str_uid = request.getParameter("uid");
int uid = -1;
if(str_uid != null || !str_uid.equals("")){
  uid = Integer.parseInt(str_uid);
}else{
  uid = -1; 
}
GroupProxy gproxy = null;
if(a != null && a.getUid() != -1)
{ 
  DAOFactory daof = DAOFactory.getDAOFactory();
  udao   = daof.getUserDAO();
  gdao   = daof.getGroupDAO();
  uinfo  = udao.execute(a.getUid());
  group  = gdao.execute(uinfo.getGroup());
  result = adminauth.checkGroupAuth(uinfo);
  int targetUserGroup = udao.execute(uid).getGroup();
  gproxy = new GroupProxy(gdao, uinfo, gdao.execute(targetUserGroup));
}
if(!result || !gproxy.canDel()){
  response.sendRedirect("overview.jsp");
}else{
  udao.delete(uid);
	response.sendRedirect(referer);
}

%>
