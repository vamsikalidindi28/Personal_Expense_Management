package com.rs.pfm.servlet;     

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

@WebServlet("/displayEditExpenseOptions")
public class DisplayEditExpenseOptionsServlet extends HttpServlet {
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

		// 1. Get the input from ui
		int userid = Integer.parseInt(session.getAttribute("UserId").toString());

		// 2.call the service 

		List<Expense> expenseOptions = pfmService.getExpenseOptions(userid);

		// 3 display

		if (expenseOptions.isEmpty()) {
			out.println("Expense Not found....!");
		} else {
			out.println("Expense Id");
			out.println("<select name='expenseid'>");
			out.println("<option value=''>Please select expense id</option>");

			int value = 0;
			String text = null;
			for (Expense expense : expenseOptions) {
				value = expense.getId();
				// To get expense types
				text = value + "--" + expense.getType() + "--" + expense.getDate() + "--" + expense.getTotal();
				out.println("<option value='" + value + "'>" + text + "</option>");  
			}
			out.println("</select>");
			out.println("&nbsp; &nbsp; &nbsp;");

			out.println(" <input type = 'submit' value = 'Next' onclick=\"javascript: submitForm('displayEditExpense')\">");

		}

		// footer
		LayoutUtil.displayFooter(request, response);
	}
  
	@Override
	public void destroy() {
		pfmService = null;
	}

}