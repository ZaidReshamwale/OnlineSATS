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

import java.sql.*;

public class AddStudentController extends HttpServlet{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement preparedStmt =null ;
		System.out.println("AddStudentController \n");
		String name=request.getParameter("name").trim();
		String registrationNo=request.getParameter("registrationNo").trim();
		String mobile=request.getParameter("mobile").trim();
		String email=request.getParameter("email").trim();
		String department=request.getParameter("department");
		String sem=request.getParameter("sem");
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		 // loads driver
		 conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/myusers", "root", "root"); // gets a new connection
		
		
		System.out.println("values "+name+" regNo  "+registrationNo+" mobile "+mobile+" department "+department+" sem "+sem);
		if(!name.equals("") && !registrationNo.equals("") && !mobile.equals(""))
		{	
		conn.setAutoCommit(true);

        String query =("INSERT INTO STUDENT (registrationNo, name ,sem , department, mobile,email) VALUES (?,?,?, ?, ?,?)");
        preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1,registrationNo);
        preparedStmt.setString(2,name);
        preparedStmt.setString(3,sem);
        preparedStmt.setString(4,department);
        preparedStmt.setString(5, mobile);
        preparedStmt.setString(6,email);
       
        preparedStmt.execute();
     
        conn.close();
		}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally {

            try {
            		if(preparedStmt != null) {
                	preparedStmt.close();
            		}if(conn != null) {
            			conn.close();
            		}
             }catch(Exception sqlException) {
                sqlException.printStackTrace();

            }
		}
	
		response.sendRedirect("add.jsp");
}
}
