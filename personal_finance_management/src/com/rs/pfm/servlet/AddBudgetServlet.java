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

import com.rs.pfm.bean.Budget;
import com.rs.pfm.bean.Expense;
import com.rs.pfm.service.PFMService;
import com.rs.pfm.service.impl.PFMServiceImpl;
import com.rs.pfm.util.LayoutUtil;

@WebServlet("/addBudget")
public class AddBudgetServlet extends HttpServlet{

	PFMService pfmService = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {

		pfmService = new PFMServiceImpl();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
   
		// 1.Get the input from UI

		Budget budget = new Budget();

		budget.setBudget(Double.parseDouble(request.getParameter("budget")));
		
		HttpSession session = request.getSession();
		int UserId = Integer.parseInt(session.getAttribute("UserId").toString() );
		budget.setUserid(UserId);

		// 2. Call the service business logic execution
		boolean isAddBudget = pfmService.addBudget(budget);

		// 3. Display the status
		PrintWriter out = response.getWriter();
		//Header and leftFrame
				
				LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session);
				
		if (isAddBudget) {
			out.println("Budget added successful.");
		} else {
			out.println("Budget add is failed");

		}
		//footer
		LayoutUtil.displayFooter(request, response);
	}

	@Override
	public void destroy() {
		pfmService = null;
	}
}
