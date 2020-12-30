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


public class json_source extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Connection conn = null;
		PreparedStatement preparedStmt =null ;
		ResultSet rs =null;
		
		System.out.println("inside json_source");
	    JsonArray data_json=new JsonArray();
	    String department=request.getParameter("department");
		String sem=request.getParameter("sem");
	
		System.out.println(" department "+department);
		System.out.println(" sem "+sem);
		
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
			 // loads driver
	    	 conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/myusers", "root", "root"); // gets a new connection
			 String qry ="select registrationNo, name from student where sem=? and department=?";
			 preparedStmt = conn.prepareStatement(qry);
			 preparedStmt.setString(1,sem);
		     preparedStmt.setString(2,department);
		      rs = preparedStmt.executeQuery();
		     
		     JsonObject json_response=new JsonObject();
		     while(rs.next())
		        {   
		            JsonObject json=new JsonObject();
		            json.addProperty("value", rs.getString(1));
		            json.addProperty("text", rs.getString(2));
		            data_json.add(json);
		        }
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

            try {if(rs != null) {
            	rs.close();
            }if(preparedStmt != null) {
            			preparedStmt.close();
            		}if(conn != null) {
            			conn.close();
            		}
             }catch(Exception sqlException) {
                sqlException.printStackTrace();

            }
		}
	

	
	}
}
