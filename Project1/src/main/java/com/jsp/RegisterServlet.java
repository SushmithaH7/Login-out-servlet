package com.jsp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	 @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		String name=req.getParameter("username");
		String email=req.getParameter("email");
		String password =req.getParameter("userpass");
		int age=Integer.parseInt(req.getParameter("userage"));
		String country=req.getParameter("country");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/details","root","root");
			PreparedStatement ps=con.prepareStatement("insert into page values(?,?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			ps.setInt(4, age);
			ps.setString(5, country);
			
			int i=ps.executeUpdate();
			if(i>0) {
				RequestDispatcher rd = req.getRequestDispatcher("LoginPage.html");
				rd.forward(req, resp);
			}
		
		} 	
		catch (ClassNotFoundException | SQLException e) {
			out.println("there was an error please try again !!!");
			RequestDispatcher rd= req.getRequestDispatcher("RegistionPage.html");
			rd.include(req, resp);
			e.printStackTrace();
		}	
		
	}
}
