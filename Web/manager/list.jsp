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
<li><a href="main.jsp" target="content">Manage panel home</a></li>
<li><a href="appmgr.jsp" target="content">manage application</a></li>
<li><a href="usermgr.jsp" target="content">manage user</a></li>
<li><a href="groupmgr.jsp" target="content">manage uesrgroup</a></li>
<li><a href="filter.jsp" target="content">manage filter</a></li>
<li><a href="logout.jsp" target="_parent">Logout</a></li>
</ul>
<%@include file="../footer.jsp"%>
</body>
</html>