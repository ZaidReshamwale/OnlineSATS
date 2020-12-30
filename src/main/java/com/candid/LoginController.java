package com.candid;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement preparedStmt =null ;
		ResultSet rs =null;
		String un=request.getParameter("username");
		String pw=request.getParameter("password");
		
		System.out.println("name "+un +"  pass "+pw );
		
		// Connect to mysql and verify username password
		
	try {
			Class.forName("com.mysql.jdbc.Driver");
		 // loads driver
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/myusers", "root", "root"); // gets a new connection
 
		preparedStmt = conn.prepareStatement("select userid,username,password from users where username=? and password=?");
		preparedStmt.setString(1, un);
		preparedStmt.setString(2, pw);
 
		rs = preparedStmt.executeQuery();
		int id =0 ;
		String user =null;
		String page =null;
		
		while (rs.next()) {
			 id= rs.getInt(1);
			 user= rs.getString(2);
		}
			System.out.println("id = "+id);
			if(user !=null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			Cookie userName = new Cookie("user", un);
			response.addCookie(userName);
			response.sendRedirect("success.jsp");
			//page="header.jsp";

		}else{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			PrintWriter out= response.getWriter();
			out.println("<font color=red>Either user name or password is wrong.</font>");
			rd.include(request, response);
		}
	
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

            try {
            		if(rs != null) {
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
		
		//response.sendRedirect("success.html");
	}

}