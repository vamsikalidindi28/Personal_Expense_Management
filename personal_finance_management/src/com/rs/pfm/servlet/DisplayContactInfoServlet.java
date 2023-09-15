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
import com.rs.pfm.util.LayoutUtil;

@WebServlet("/displayContactInfo")
public class DisplayContactInfoServlet extends HttpServlet {
	PFMService pfmService = null;



	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Header and leftFrame
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session);

		// 1.Get the input
		User user = (User) session.getAttribute("user");
		// 2.call the service
		user.setFirstname(request.getParameter("firstname"));
		user.setMiddlename(request.getParameter("middlename"));
		user.setLastname(request.getParameter("lastname"));
		 		
		// Display
		out.println("<table border='2' align='center'>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center'>");
		out.println(" Contact Info");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>Email</td>");
		out.println("<td><input type='text' name='email' value = '" + user.getEmail() + "'>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>Mobilee</td>");
		out.println("<td><input type='text' name='mobile' value = '" + user.getMobile() + "'>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center'>");
		
		out.println(
				" <input type = 'submit' value = 'Next' onclick=\"javascript: submitForm('reviewUpdateProfile')\">");
		out.println("</td>");
		out.println("</tr>");

		out.println("</table>"); 

		// footer
		LayoutUtil.displayFooter(request, response);
	}
}