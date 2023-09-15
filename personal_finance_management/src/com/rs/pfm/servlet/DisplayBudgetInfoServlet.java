
package com.rs.pfm.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.pfm.bean.Expense;
import com.rs.pfm.service.PFMService;
import com.rs.pfm.service.impl.PFMServiceImpl;
import com.rs.pfm.util.LayoutUtil;

@WebServlet("/displayBudgetInfo")
public class DisplayBudgetInfoServlet extends HttpServlet {
 
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
		Expense expense = (Expense) session.getAttribute("expense");
		//Display
		out.println("<table border='2' align='center'>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center'>");
		out.println("Budget");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
			out.println("<td> Enter Budget </td>");
			out.println("<td><input type='text' name='budget'");
			out.println("</td>");
			out.println("</tr>");
			
			
			out.println("<tr>");
			out.println("<td colspan='2' align='center'>");
		
			out.println(" <input type = 'submit' value = 'Next' onclick=\"javascript: submitForm('addBudget')\">");
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
