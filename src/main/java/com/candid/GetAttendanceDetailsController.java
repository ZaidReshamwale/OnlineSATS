package com.candid;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.*;


public class GetAttendanceDetailsController extends HttpServlet { 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection conn = null;
		PreparedStatement preparedStmt1 =null ;
		ResultSet rs =null;
		
		System.out.println("inside json_source");
	    JsonArray data_json=new JsonArray();
	    String regNo=request.getParameter("ch_user1");
		String sub_code=request.getParameter("sub_code");
		;
		System.out.println(" regNo "+regNo);
		
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
			 // loads driver
			 conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/myusers", "root", "root"); // gets a new connection
			 String qry ="select marked_on, attendance  from markAttendace where registrationNo=? and sub_code=?";
		     preparedStmt1 = conn.prepareStatement(qry);
		     preparedStmt1.setString(1,regNo);
		     preparedStmt1.setString(2,sub_code);
		     rs = preparedStmt1.executeQuery();
		     
		     JsonObject json_response=new JsonObject();
		     
		     double totaldays=0;
		     double NoofDaysPresent=0;
		     double NoofDaysAbsent=0;
		     
		     while(rs.next())
		        {   
		            JsonObject json=new JsonObject();
		            json.addProperty("value", rs.getString(1));
		            String attend = rs.getString(2);
		            if(attend.equals("P")) {
		            	NoofDaysPresent++;
		            }else {
		            	NoofDaysAbsent++;
		            	
		            }
		            json.addProperty("text", attend);
		            data_json.add(json);
		        }
		     
		     // % of attendance calculation
		     	JsonObject json=new JsonObject();
		     	totaldays = NoofDaysPresent + NoofDaysAbsent;
		     	float percent =(float) ((NoofDaysPresent*100)/totaldays);
		     	json.addProperty("value", "Attendance percentage");
		     	json.addProperty("text", percent);
		     	
		     	data_json.add(json);
		     	
		        System.out.println(data_json);
		        json_response.add("aaData", data_json);

		        response.setContentType("application/Json");

		        response.getWriter().write(json_response.toString());

		        System.out.println(json_response);


		     
	    } catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally {

            try {
            	if(rs != null) {
                	rs.close();
            		}
            		if(preparedStmt1 != null) {
                	preparedStmt1.close();
            		}if(conn != null) {
            			conn.close();
            		}
             }catch(Exception sqlException) {
                sqlException.printStackTrace();

            }
		}

}
}
