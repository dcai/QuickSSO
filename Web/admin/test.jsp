<%@include file="../common.jsp"%>
<html> 
<head>
<title><%out.print(session.getAttribute("login"));out.print(session.getId());%></title>
<style>
@import '../style.css';
</style>
</head>
<body>
<%@include file="../header.jsp"%>
<ul style="line-height: 2em;">
<li><a href="logout.jsp">Logout</a></li>
<li><a href="../">Return login page without logout</a></li>
</ul>
<%@include file="../footer.jsp"%>
</body>
</html>