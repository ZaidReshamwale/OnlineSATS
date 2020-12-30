<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Attendance Tracking System</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("user") == null){
	response.sendRedirect("index.html");
}else { user = (String) session.getAttribute("user");

%>

 <div class="panel panel-default" style="height: 15%">
    	<div class="panel-heading">
    	    <h4 class="text-left">Welcome  <%=user %> </h4>
    	    <h4 class="text-right"><a href="<%=request.getContextPath()%>/LogoutServletController">Logout</a></h4></div>
    </div>


<% } %>

</body>
</html>