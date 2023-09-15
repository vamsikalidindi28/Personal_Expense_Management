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

@WebServlet("/displayNameInfo")
public class DisplayNameInfoServlet extends HttpServlet {
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

		// 1.Get the input
		int userId = Integer.parseInt(session.getAttribute("UserId").toString());
		// 2.call the service
		User user = pfmService.getUser(userId);
		session.setAttribute("user", user);
		// Display
		out.println("<table border='2' align='center'>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center'>");
		out.println(" Name Info");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>First Name</td>");
		out.println("<td><input type='text' name='firstname' value = '" + user.getFirstname() + "'>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>Middle Name</td>");
		out.println("<td><input type='text' name='middlename' value = '" + user.getMiddlename() + "'>");
		out.println("</td>");
		out.println("</tr>");

		out.println("<tr>");
		out.println("<td>Last Name</td>");
		out.println("<td><input type='text' name='lastname' value = '" + user.getLastname() + "'>");
		out.println("</td>");
		out.println("</tr>");

		
		out.println("<tr>");
		out.println("<td colspan='2' align='center'>");
		
		out.println(
				" <input type = 'submit' value = 'Next' onclick=\"javascript: submitForm('displayContactInfo')\">");
		out.println("</td>");
		out.println("</tr>");

		out.println("</table>"); 

		// footer
		LayoutUtil.displayFooter(request, response);
	}

	@Override
	public void destroy() {
		pfmService = null;
	}

}