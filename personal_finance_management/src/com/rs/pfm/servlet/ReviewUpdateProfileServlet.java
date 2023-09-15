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

@WebServlet("/reviewUpdateProfile") 
public class ReviewUpdateProfileServlet extends HttpServlet {
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

		// Display
		out.println("<table border='2' align='center'>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center'>");
		out.println(" Update Profile");
		out.println("</td>");
		out.println("</tr>");


		out.println("<tr>");
		out.println("<td>First Name</td>");
		out.println("<td><input type='text' name='firstname' value = '" + user.getFirstname() + "'disabled='true'>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>Middle Name</td>");
		out.println("<td><input type='text' name='middlename' value = '" + user.getMiddlename() + "'disabled='true'>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>Last Name</td>");
		out.println("<td><input type='text' name='lastname' value = '" + user.getLastname() + "'disabled='true'>");
		out.println("</td>");
		out.println("</tr>");   
		
		

		out.println("<tr>");
		out.println("<td>Email</td>");
		out.println("<td><input type='text' name='email' value = '" + user.getEmail() + "'disabled='true'>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>Mobilee</td>");
		out.println("<td><input type='text' name='mobile' value = '" + user.getMobile() + "'disabled='true'>");
		out.println("</td>");
		out.println("</tr>");
		

		
		
		out.println("<tr>");
		out.println("<td colspan='2' align='center'>");
		 
		out.println(
				" <input type = 'submit' value = 'Update' onclick=\"javascript: submitForm('updateProfile')\">");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>"); 

		// footer
		LayoutUtil.displayFooter(request, response);
	}

}