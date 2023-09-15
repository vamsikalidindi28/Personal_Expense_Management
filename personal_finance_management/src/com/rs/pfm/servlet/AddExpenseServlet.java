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

import com.rs.pfm.bean.Expense;
import com.rs.pfm.service.PFMService;
import com.rs.pfm.service.impl.PFMServiceImpl;
import com.rs.pfm.util.LayoutUtil;

@WebServlet("/addExpense")
public class AddExpenseServlet extends HttpServlet {
	PFMService pfmService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {

		pfmService = new PFMServiceImpl();
	}
 
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
   
		// 1.Get the input from UI

		Expense expense = new Expense();

		expense.setType(request.getParameter("type"));
		expense.setDate(request.getParameter("date"));
		expense.setPrice(Float.parseFloat(request.getParameter("price")));
		expense.setNoofItems(Integer.parseInt(request.getParameter("Number of items")));
		expense.setTotal(Float.parseFloat(request.getParameter("total")));
		//expense.setBywhom(request.getParameter("By whom"));
		
		HttpSession session = request.getSession();
		int UserId = Integer.parseInt(session.getAttribute("UserId").toString() );
		expense.setUserid(UserId);

		// 2. Call the service business logic execution
		boolean isAddExpense = pfmService.addExpense(expense);

		// 3. Display the status
		PrintWriter out = response.getWriter();
		//Header and leftFrame
				
				LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session);
				
		if (isAddExpense) {
			out.println("Expense added successful.");
		} else {
			out.println("Expense add is failed");

		}
		//footer
		LayoutUtil.displayFooter(request, response);
	}

	@Override
	public void destroy() {
		pfmService = null;
	}

}