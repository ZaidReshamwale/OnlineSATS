package com.candid;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ConsolidatedReportController extends HttpServlet{
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Connection conn = null;
		CallableStatement stmt =null ;
		JsonArray data_json=new JsonArray();
		String department=request.getParameter("department");
		String sem=request.getParameter("sem");
		
		 System.out.println("ConsolidatedReportController "+department +" "+sem);
		String sql = "{call calculatePercnt(?,?)}";
		
		
		 try {
		    	Class.forName("com.mysql.jdbc.Driver");
				 // loads driver
				 conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/myusers", "root", "root"); // gets a new connection
				 stmt=conn.prepareCall(sql); 
			         
			         //Set IN parameter
			         stmt.setString(1, sem);  
			         stmt.setString(2, department);  
			        
			       
			       
			         //Execute stored procedure
			         ResultSet rs = stmt.executeQuery();
			         System.out.println("rs "+rs.getFetchSize());
			         JsonObject json_response=new JsonObject();
			         while (rs.next()) {
			        	 JsonObject json=new JsonObject();
				            String Text =   rs.getString(1)+"*" +rs.getString(2).trim()+"*" +rs.getString(3).trim();
				            json.addProperty("text", Text);
				            json.addProperty("value", rs.getDouble(4));
				            data_json.add(json);
			         }
			         
			         json_response.add("aaData", data_json);

				        response.setContentType("application/Json");

				        response.getWriter().write(json_response.toString());
				        System.out.println("json_response \n"+json_response);

			     
			     
		 }catch (  SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(Exception e){
				e.printStackTrace();
			}finally {

	            try {
	            		if(stmt != null) {
	                	stmt.close();
	            		}if(conn != null) {
	            			conn.close();
	            		}
	             }catch(Exception sqlException) {
	                sqlException.printStackTrace();

	            }
			}
		
	}

}
