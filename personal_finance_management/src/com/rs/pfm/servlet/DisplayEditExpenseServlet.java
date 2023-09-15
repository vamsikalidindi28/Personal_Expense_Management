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

@WebServlet("/displayEditExpense")
public class DisplayEditExpenseServlet extends HttpServlet {
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
		int expenseid = Integer.parseInt(request.getParameter("expenseid").toString()); 
	  session.setAttribute("expenseid", expenseid);
		// 2.call the service
		Expense expense = pfmService.getExpense(expenseid);
		
		//Display
		out.println("<table border='2' align='center'>");
		out.println("<tr>");
		out.println("<td colspan='2' align='center'>");
		out.println(" Edit Expense");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
			out.println("<td>Expense Type</td>");
			out.println("<td><input type='text' name='type' value = '"+expense.getType()+"'>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Date</td>");
			out.println("<td><input type='text' name='date' value = '"+expense.getDate()+"'>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Price</td>");
			out.println("<td><input type='text' name='price' value = '"+expense.getPrice()+"'>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Number of Items</td>");
			out.println("<td><input type='text' name='Number Of items' value = '"+expense.getNoofItems()+"'>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td>Total</td>");
			out.println("<td><input type='text' name='total' value = '"+expense.getTotal()+"'>");
			out.println("</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<td colspan='2' align='center'>");
		
			out.println(" <input type = 'submit' value = 'Edit Expense' onclick=\"javascript: submitForm('editExpense')\">");
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