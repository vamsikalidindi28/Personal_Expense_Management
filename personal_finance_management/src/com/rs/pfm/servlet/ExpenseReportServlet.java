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

@WebServlet("/expenseReportServlet")
public class ExpenseReportServlet extends HttpServlet {

	PFMService pfmService = null;

	@Override
	public void init() throws ServletException {
		pfmService = new PFMServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Header
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		// getting username and userid from session
		int userId = Integer.parseInt(session.getAttribute("UserId").toString());

		// pass the input to the service for business logic execution
		List<Expense> expenseReport = pfmService.expenseReport(userId);

		LayoutUtil.displayHeaderAndLeftFrame(request, response, out, session);

		if (expenseReport.isEmpty()) {
			out.println("Expense Report not found...!");
		} else {
			// Body

			out.println("<table border='2' align='center'>");
			out.println("<tr>");
			out.println("<td colspan='7' align='center'>Expense Report</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td> <input type='text' name='type' value= 'Expense Type'</td>");

			out.println("<td> <input type='text' name='price' value= 'Price'</td>");
			out.println("<td> <input type='text' name='NoOfItems' value= 'No.Of Items'</td>");
			out.println("<td> <input type='text' name='total' value= 'Total'</td>");
			// out.println("<td> <input type='text' name='byWhom' value= 'By Whom'</td>");

			out.println("</tr>");

			for (Expense expense : expenseReport) {

				out.println("<tr>");

				out.println("<td><input type=text name = 'type' value = '" + expense.getType() + "'disabled='true'>");
				out.println("</td>");

				out.println("<td><input type=text name = 'price' value = '" + expense.getPrice() + "'disabled='true'>");
				out.println("</td>");
				out.println("<td><input type=text name = 'NoOfItems' value = '" + expense.getNoofItems()
						+ "'disabled='true'>");
				out.println("</td>");

				out.println("<td><input type=text name = 'total' value = '" + expense.getTotal() + "'disabled='true'>");
				out.println("</td>");
				out.println("</tr>");

			}

			out.println("</table>");

		}
		// footer
		LayoutUtil.displayFooter(request, response);
	}

	@Override
	public void destroy() {
		pfmService = null;
	}

}
