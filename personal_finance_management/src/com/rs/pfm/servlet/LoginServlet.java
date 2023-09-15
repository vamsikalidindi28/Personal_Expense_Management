package com.rs.pfm.servlet;  

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.pfm.service.PFMService;
import com.rs.pfm.service.impl.PFMServiceImpl;
import com.rs.pfm.util.LayoutUtil;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	PFMService pfmService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {

		pfmService = new PFMServiceImpl();
	}
 	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1. Get the input from ui
		String username = request.getParameter("user name");
		String password = request.getParameter("password");

		// 2.call the service

		int UserId = pfmService.login(username, password);

		// 3 display
 
		PrintWriter out = response.getWriter();
		if (UserId > 0) {
			HttpSession session = request.getSession();
			session.setAttribute("UserId", UserId);
			session.setAttribute("username", username);
			//Header And LeftFrame
			LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session);

			// Body
			out.println("Welcome to User: " + username);
			//Footer 
			LayoutUtil.displayFooter(request, response); 

		} else {
			out.println("Login Failed...!");
			request.getRequestDispatcher("Login.html").include(request, response);
		}

	}

	@Override
	public void destroy() {
		pfmService = null;
	}

}