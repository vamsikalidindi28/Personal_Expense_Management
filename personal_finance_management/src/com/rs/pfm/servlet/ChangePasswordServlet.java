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

import com.rs.pfm.bean.User;
import com.rs.pfm.service.PFMService;
import com.rs.pfm.service.impl.PFMServiceImpl;

@WebServlet("/changePassword")
public class ChangePasswordServlet extends HttpServlet {
	PFMService pfmService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {

		pfmService = new PFMServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.Get the input from UI
		User user = new User();
		String Currentpassword =(request.getParameter("currentPassword"));
		String newPassword = (request.getParameter("newPassword"));
		
		
		HttpSession session = request.getSession();
		int UserId = Integer.parseInt(session.getAttribute("UserId").toString() );
		user.setId(UserId);

		// 2. Call the service business logic execution
		boolean isResetpasword = pfmService.resetPassword(UserId, newPassword, Currentpassword);
                                                                                                
		//3.Display
		PrintWriter out = response.getWriter();
		if (isResetpasword) {
			out.println("Reset Password successful,try Again with login details..");
			request.getRequestDispatcher("Login.html").include(request, response);
		} else {
			out.println("Reset Password is failed...");
			request.getRequestDispatcher("ChangePassword.html").include(request, response);
		}
		
	}

	@Override
	public void destroy() {
		pfmService = null;
	}

}