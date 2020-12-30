<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Mark Student Attendance</title>
</head>
<body>
<%@include file="header.jsp" %>

<div class="container">
 <p align="center"> <h3>Mark Attendance</h3> </p>
 <br>
  <br>
 
 <form class="form-inline" action="MarkAttendanceController" method="post">
 
 <div class="form-group">
    	<label for="department" class="col-sm-4 control-label">Department</label>
    	<div class="col-sm-4">
      <select class="form-control" id="department" name="department">
        <option>CS</option>
        <option>IS</option>
        <option>E&C</option>
        <option>E&E</option>
      </select>
      </div>
      </div>
   	
   	<div class="form-group">
    	<label for="sem" class="col-sm-4 control-label">Semester</label>
    	<div class="col-sm-4">
      <select class="form-control" id="sem" name="sem">
        <option>1</option>
        <option>2</option>
        <option>3</option>
        <option>4</option>
        <option>5</option>
        <option>6</option>
        <option>7</option>
        <option>8</option>
      </select>
   	</div>
   	</div>
   	
   	<div class="form-group">
    	<label class="col-sm-4 control-label">Subject </label>
      	<div class="col-sm-6">
        	<input class="form-control" id="sub_code" name="sub_code" type="text" value="">
      	</div>
   	</div>
   	
<div class="form-group">
    	<label class="col-sm-4 control-label">Registration No </label>
      	<div class="col-sm-6">
        	<input class="form-control" id="registrationNo" name="registrationNo" type="textarea" value="">
      	</div>
   	</div>
   	<br>
   	<br>
   	
   	<input type="submit" value="Submit">
   	<a href="/sample/success.jsp">Back</a>

 
  </form>
 
</div>
</body>
</html>