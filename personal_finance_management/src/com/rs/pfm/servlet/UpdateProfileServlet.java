package com.rs.pfm.servlet; 

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.pfm.bean.User;
import com.rs.pfm.service.PFMService;
import com.rs.pfm.service.impl.PFMServiceImpl;
import com.rs.pfm.util.LayoutUtil;

@WebServlet("/updateProfile") 
public class UpdateProfileServlet extends HttpServlet {
	PFMService pfmService = null;


	@Override
	public void init() throws ServletException {
		pfmService = new PFMServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Header and leftFrame
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session);

		// 1.Get the object  which is present in the session object
		User user = (User) session.getAttribute("user");
		
		// 2.call the service to execute the business logic to update the user object
		
		boolean isUserUpdate = pfmService.updateUser(user);
		
		//Display the status
		
		if(isUserUpdate)
		{
			out.println("User Profile Updated Successfully.. ");
		}
		else
		{
			out.println("User Profile update is Failed...");
		}

		// footer
		LayoutUtil.displayFooter(request, response);
		session.removeAttribute("user");
	}
	@Override
	public void destroy() {
		pfmService = null;
	}

}    