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
  
<title>Attendance Report</title>

<script type="text/javascript">
{
$(document).ready(function() 
{ 
	var sem = $('#sem').val();

	var department = $('#department').val();
	
$("#sem").change(function() {
	
    alert("Fetching data");
    $.ajax({
    	url : 'json_source',
		data : {
			sem : $('#sem').val(),
			department : $('#department').val(),
			
		},
		dataType: 'json',
        success: function (data) {
        	$("ch_user1").empty();
        	$.each(data.aaData,function(i,data)
                    {
                    
                     var div_data="<option value="+data.value+">"+data.text+"</option>";
                   
                    $(div_data).appendTo('#ch_user1'); 
                    });  

        	
        },
        error: function() {
            console.log('Cannot retrieve data.');
        }

  });
      });
      
  });
    
    
function getJsonData()
{

  var table="<html><table border=\"1px\">" ;

  table=table+"<th> Date</th><th>Attendance</th>";

  $.ajax({

        type: "GET",
                    url: 'GetAttendanceDetailsController',
                    data : {
                    	ch_user1 : $('#ch_user1').val(),
                    	sub_code : $('#sub_code').val(),
                    },
                    dataType: 'json',
                    success : function(json) {

                        var info1=json.aaData;

                      $.each(info1, function(index, element) {
                          //var info = element.JsonData;

                          table=table+"<tr>";

                              //$.each(info, function(index, element) {

                                  table=table+"<td>" + element.value +"</td>"
                                  +"<td>" + element.text +"</td><td>" ;
                                //});
                              table=table+"</tr>";

                      });
                      table=table+="</table></html>";
                      $("tbl").empty();
                      $("#tbl").html(table);
                    },
                    async: false,
                    global: false,
                    error: function () {
                       alert("No data found");
                    }
                });

}
}
</script>
</head>

<body>

<%@include file="header.jsp" %>
<div class="container">

 <form class="form-inline" action="" method="post">
 
 <p align="center"> <h3>Attendance Report</h3> </p>
 <br>
 <br>
 <div class="form-group">
    	<label for="department" class="col-sm-5 control-label">Department</label>
    	<div class="col-sm-5">
      <select class="form-control" id="department" name="department">
       <option value="select">select</option>
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
      <select class="form-control" id="sem" name="sem" >
      <option value="select">select</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
      </select>
   	</div>
   	</div>
   	
   	<div class="form-group">
    	<label for=sub_code class="col-sm-4 control-label">Subject</label>
    	<div class="col-sm-4">
      <select class="form-control" id="sub_code" name="sub_code" >
      <option value="select">select</option>
        <option value="SUB001">SUB001</option>
        <option value="SUB002">SUB002</option>
        <option value="SUB003">SUB003</option>
        <option value="SUB004">SUB004</option>
        <option value="SUB005">SUB005</option>
     </select>
   	</div>
   	</div>
   	
   	<div id="div_source1"  class="form-group">
   	<label for="ch_user1" class="col-sm-5 control-label">Student</label>
    <select class="form-control" id="ch_user1" name="ch_user1"  onchange="getJsonData(this);">
    <div class="col-sm-5">
        <option value="select">select</option>
    </select>
</div>
</div>

<br>
<br>
<br>


	

<div id="tbl"  width="100%" align="center">

<a href="/sample/success.jsp">Back</a>
</div>
   	
   	</div>
</form>
</body>
</html>