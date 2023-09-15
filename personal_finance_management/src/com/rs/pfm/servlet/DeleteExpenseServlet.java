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

@WebServlet("/deleteExpense")
public class DeleteExpenseServlet extends HttpServlet {
	PFMService pfmService = null;

	@Override
	public void init() throws ServletException {

		pfmService = new PFMServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 1.Get the input from UI
 
		Expense expense = new Expense();
		
		HttpSession session = request.getSession();
		
		int expenseid = Integer.parseInt(request.getParameter("expenseid")); 
		expense.setUserid(expenseid);

		// 2. Call the service business logic execution
		boolean isDeleteExpense = pfmService.deleteExpense(expenseid);

		// 3. Display the status
		PrintWriter out = response.getWriter();
		//Header and leftFrame
  				
				LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session);
				
		if (isDeleteExpense) {
			out.println("Expense Deleted successfully...");
		} else {
			out.println("Expense Delete is failed");

		}
		//footer
		LayoutUtil.displayFooter(request, response);
	}

	@Override
	public void destroy() {
		pfmService = null;
	}

}