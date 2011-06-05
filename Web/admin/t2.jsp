<%
out.println(session.getAttribute("login"));
out.println(session.getId());
%>

<a href="logout.jsp">Logout</a>