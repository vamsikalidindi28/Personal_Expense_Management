package com.rs.pfm.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.pfm.util.LayoutUtil;

@WebServlet("/displayChangePassword") 
public class DisplayChangePasswordServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Header
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session);
		 
		//Body
		
		
		request.getRequestDispatcher("ChangePassword.html").include(request, response);
		
		//footer
		LayoutUtil.displayFooter(request, response);
	}
}
