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
<title>Add Student Details</title>
<script type="text/javascript">
{
	$(document).ready(function() 
	{ 

		alert("here");
	$("#frm").click(function() {
		boolean validte =true;
		
		var name = $('#name').val();

		var registrationNo = $('#registrationNo').val();
		
		var mobile = $('#mobile').val();
		
		alert(name " " registrationNo " "mobile);
		
		if(name= '' || registrationNo ='' || mobile =''){
		alert("Either Name or registrationNo or  mobile is empty");
		validate = false;
		}
	     if(validate()){
	        $(this).closest("form").submit();
	    }
	   });
	 });
}
}
</script>
</head>
<body>

<%@include file="header.jsp" %>

<div class="container">

 <form class="form-inline" id="frm"  action="AddStudentController" method="post">
 
 <p align="center" > <h3>Add student Details</h3> </p>
 <br>
 <br>
    <div class="form-group">
    	<label class="col-sm-5 control-label">Student Name</label>
      	<div class="col-sm-4">
        	<input class="form-control" id="name" name="name" type="text" value="" required>
      	</div>
   	</div>  
   	
   	 	<div class="form-group">
    	<label for="department" class="col-sm-5 control-label">Department</label>
    	<div class="col-sm-5">
      <select class="form-control" id="department" name="department" required>
        <option>CS</option>
        <option>IS</option>
        <option>E&C</option>
        <option>E&E</option>
      </select>
      </div>
      </div>
   	
   	
   	<br><br>
   	<div class="form-group">
    	<label class="col-sm-5 control-label">Registration No</label>
      	<div class="col-sm-5">
        	<input class="form-control" id="registrationNo" name="registrationNo" type="text" value="" required>
      	</div>
   	</div>
  
  <div class="form-group">
    	<label for="sem" class="col-sm-5 control-label">Semester</label>
    	<div class="col-sm-5">
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
  
  <br>
  <br>
   	<div class="form-group">
    	<label class="col-sm-5 control-label">Mobile Number</label>
      	<div class="col-sm-5">
        	<input class="form-control" id="mobile" name="mobile" type="text" maxlength="10"
        	onkeypress="if ( isNaN( String.fromCharCode(event.keyCode) )) return false;" />
      	</div>
   	</div>
   	
   	<div class="form-group">
    	<label class="col-sm-4 control-label"> Email </label>
      	<div class="col-sm-3">
        	<input class="form-control" id="email" name="email" type="text" value="">
      	</div>
   	</div>
   	<br><br>
   	
   	
  
   	
   	
   	<br>
   	<br>
   	
   	<input type="submit" value="Submit" align="center" id="submitButton">
   	<a href="/sample/success.jsp">Back</a>

</div>
</body>
</html>