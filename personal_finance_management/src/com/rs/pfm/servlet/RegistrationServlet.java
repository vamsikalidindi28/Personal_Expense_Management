package com.rs.pfm.servlet;  

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rs.pfm.bean.User;
import com.rs.pfm.service.PFMService;
import com.rs.pfm.service.impl.PFMServiceImpl;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet{   
	PFMService pfmService = null;       
	@Override
	public void init(ServletConfig config) throws ServletException { 
   
		pfmService = new PFMServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, 
	IOException {
		


		// To handle the request
		// 1.Get the input from UI 

		User user = new User();

		user.setFirstname(request.getParameter("first name"));  
		user.setMiddlename(request.getParameter("middle name"));
		user.setLastname(request.getParameter("last name"));
		user.setEmail(request.getParameter("email"));
		user.setUsername(request.getParameter("user name"));
		user.setPassword(request.getParameter("password")); 
		user.setMobile(request.getParameter("mobile"));
		// 2. Call the service business logic execution
		  
		boolean isRegister = pfmService.registration(user);

		// 3. Display the status
PrintWriter out = response.getWriter();
String path = null;  
		if (isRegister) {
			out.println("User Registeration is done Successful,try with login."); 
			path = "Login.html";
		} else {
			out.println("User registration is failed try again");
			path = "Registration.html";
		}
		request.getRequestDispatcher(path).include(request, response);
	
	}
	@Override
	public void destroy() {
		pfmService = null;
	}

}