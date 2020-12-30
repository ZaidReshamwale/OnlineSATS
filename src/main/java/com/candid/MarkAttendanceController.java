package com.candid;

import javax.servlet.http.HttpServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class  MarkAttendanceController   extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = null;
		PreparedStatement preparedStmt1 =null ;
		ResultSet rs =null;
		
		String department=request.getParameter("department");
		String sem=request.getParameter("sem");
		String sub_code=request.getParameter("sub_code");
		String registrationNos=request.getParameter("registrationNo");
		List regValues = new ArrayList();
		
		System.out.println("department "+department +"  sem "+sem+" sub_code "+sub_code );
		
		System.out.println(" registrationNos "+registrationNos);
		
				
		try {
			
			StringTokenizer tok=new 
	                 StringTokenizer(registrationNos,",");
		     
		     while(tok.hasMoreTokens()){
		    	 regValues.add(tok.nextToken().toString());
	            
		     } 
		Class.forName("com.mysql.jdbc.Driver");
		 // loads driver
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/myusers", "root", "root"); // gets a new connection
		
		 conn.setAutoCommit(true);
		 Date date = new Date();
	     java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	     
	     if(!registrationNos.equals("")) {
		     String qry ="select registrationNo from student where sem=? and department=?";
		     preparedStmt1 = conn.prepareStatement(qry);
		     preparedStmt1.setString(1,sem);
		     preparedStmt1.setString(2,department);
		     rs = preparedStmt1.executeQuery();
		     
				while (rs.next()) {
					String query =("INSERT INTO markAttendace (registrationNo ,sem , department, sub_code,attendance,marked_on) VALUES (?,?,?, ?,?, ?)");
					PreparedStatement preparedStmt = conn.prepareStatement(query);
					String value = rs.getString(1);
			        preparedStmt.setString(1,value);
			        preparedStmt.setString(2,sem);
			        preparedStmt.setString(3,department);
			        preparedStmt.setString(4, sub_code); 
			        if(regValues.contains(value)) {
			        	preparedStmt.setString(5, "A");
			        }else {
			        preparedStmt.setString(5, "P");
			        }
			        preparedStmt.setDate(6, sqlDate);
			       
			        preparedStmt.execute();
			        
			        preparedStmt.close();
				}
		     
	     }
	     
	        conn.close();
	       
	        
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
		
		response.sendRedirect("mark.jsp");
	}

}
