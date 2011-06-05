<%@include file="../common.jsp"%>
<html>
<head> 
<style>
@import '../style.css';
</style>
<style>
#list{
  width:100%;
  padding:0;
  margin:0;
  margin-top:80px;
  line-height: 1.5em;
	min-height:25em;
	height:auto!important;
	height:25em;
}
#list li{
  list-style: url(arrow.gif) inside circle;
} 
</style>
</head>
<body>
<ul id="list">
<li><a href="overview.jsp" target="content">Admin panel home</a></li>
<%if(a.getLevel()>10){
%>
<li><a href="usergroup.jsp" target="content">manage your usrgroup</a></li>
<%} %>
<li><a href="profile.jsp" target="content">Profile</a></li>
<li><a href="chg.jsp" target="content">Change password</a></li>
<li><a href="map.jsp" target="content">Manage your mapping</a></li>
<li><a href="record.jsp" target="content">Login records</a></li>
<li><a href="../" target="_parent">Return to login page</a></li>
<li><a href="logout.jsp" target="_parent">Logout</a></li>
</ul>
</body>
</html>