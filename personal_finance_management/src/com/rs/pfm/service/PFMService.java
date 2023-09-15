package com.rs.pfm.service; 

import java.util.List;

import com.rs.pfm.bean.Budget;
import com.rs.pfm.bean.Expense;
import com.rs.pfm.bean.User;

public interface PFMService {

	boolean registration(User user);

	int login(String username, String password);

	boolean addExpense(Expense expense);
	
	boolean addBudget(Budget budget);

	boolean editExpense(Expense expense);

	boolean deleteExpense(int expenseid);

	boolean resetPassword(int userid, String newPassword, String Currentpassword );

	List<Expense> getExpenseOptions(int userid);

	Expense getExpense(int expenseId);

	List<Expense> expenseReport(int userid);

	User getUser(int Userid);

	boolean updateUser(User user);

}
