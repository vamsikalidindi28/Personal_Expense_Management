package com.rs.pfm.service.impl;   

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rs.pfm.bean.Budget;
import com.rs.pfm.bean.Expense;
import com.rs.pfm.bean.User;
import com.rs.pfm.service.PFMService;
import com.rs.pfm.util.DBUtil;

public class PFMServiceImpl implements PFMService {

	@Override
	public boolean registration(User user) {
		boolean isRegister = false;

		PreparedStatement preparedstatement = null;
		Connection connection = null;
		// 1. Register the driver
		try {
			connection = DBUtil.getConnection();

			// 3.Create the statement
			String query = "INSERT INTO USER (FIRSTNAME,MIDDLENAME,LASTNAME,EMAIL,USERNAME,PASSWORD,MOBILE) VALUES(?,?,?,?,?,?,?)";
			preparedstatement = connection.prepareStatement(query);

			// 3.1 Set the parameter values into the prepared statement value

			preparedstatement.setString(1, user.getFirstname());
			preparedstatement.setString(2, user.getMiddlename());
			preparedstatement.setString(3, user.getLastname());
			preparedstatement.setString(4, user.getEmail());
			preparedstatement.setString(5, user.getUsername());
			preparedstatement.setString(6, user.getPassword());
			preparedstatement.setString(7, user.getMobile());

			// 4 execute the statement
			int numberOfRecAffected = preparedstatement.executeUpdate();
			if (numberOfRecAffected > 0) {
				isRegister = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}

		return isRegister;
	}

	// ...................................Login...............................................
	@Override
	public int login(String username, String password) {

		int UserId = 0;
		PreparedStatement preparedstatement = null;
		Connection connection = null;
		// 1. Register the driver
		try {
			connection = DBUtil.getConnection();

			// 3.Create the statement
			String query = "select * from user where username=? and password=?";
			preparedstatement = connection.prepareStatement(query);
			// 3.1
			preparedstatement.setString(1, username);

			preparedstatement.setString(2, password);
			// 4 execute the statement

			ResultSet resultset = preparedstatement.executeQuery();

			// 4.1 Processing the result set
			while (resultset.next()) {
				UserId = resultset.getInt(1);
			}
		}
		 catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}

		return UserId;
	}

	// ....................... AddExpense..................................
	@Override
	public boolean addExpense(Expense expense) {

		boolean isAddExpense = false;

		PreparedStatement preparedstatement = null;
		Connection connection = null;
		// 1. Register the driver
		try {
			connection = DBUtil.getConnection();
			// 3.Create the statement
			String query = "INSERT INTO Expense (type,date,price,noofitems,total,userid) VALUES(?,?,?,?,?,?)";
			preparedstatement = connection.prepareStatement(query);

			// 3.1 Set the parameter values into the prepared statement value

			preparedstatement.setString(1, expense.getType());
			preparedstatement.setString(2, expense.getDate());
			preparedstatement.setFloat(3, expense.getPrice());
			preparedstatement.setInt(4, expense.getNoofItems());
			preparedstatement.setFloat(5, expense.getTotal());
			//preparedstatement.setString(6, expense.getBywhom());
			preparedstatement.setInt(6, expense.getUserid());

			// 4 execute the statement
			int numberOfRecAffected = preparedstatement.executeUpdate();
			if (numberOfRecAffected > 0) {
				isAddExpense = true;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}

		return isAddExpense;
	}
	
	
	@Override
	public boolean addBudget(Budget budget) {
		boolean isAddBudget = false;

		PreparedStatement preparedstatement = null;
		Connection connection = null;
		// 1. Register the driver
		try {
			connection = DBUtil.getConnection();
			// 3.Create the statement
			String query = "INSERT INTO Budget (budget,userid) VALUES(?,?)";
			preparedstatement = connection.prepareStatement(query);

			// 3.1 Set the parameter values into the prepared statement value

			preparedstatement.setDouble(1, budget.getBudget());
			
			//preparedstatement.setString(6, expense.getBywhom());
			preparedstatement.setInt(2, budget.getUserid());

			// 4 execute the statement
			int numberOfRecAffected = preparedstatement.executeUpdate();
			if (numberOfRecAffected > 0) {
				isAddBudget = true;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}
		return false;
	}

	// ....................... EditExpense..................................

	@Override
	public boolean editExpense(Expense expense) {

		boolean isEditExpense = false;

		PreparedStatement preparedstatement = null;
		Connection connection = null;
		// 1. Register the driver
		try {
			connection = DBUtil.getConnection();

			// 3.Create the statement
			String query = "UPDATE EXPENSE SET TYPE=?,DATE=?,price =?,noofitems=?,total=? WHERE ID=?";
			preparedstatement = connection.prepareStatement(query);

			// 3.1 Set the parameter values into the prepared statement value

			preparedstatement.setString(1, expense.getType());
			preparedstatement.setString(2, expense.getDate());
			preparedstatement.setFloat(3, expense.getPrice());
			preparedstatement.setInt(4, expense.getNoofItems());
			preparedstatement.setFloat(5, expense.getTotal());
			//preparedstatement.setString(6, expense.getBywhom());
			preparedstatement.setInt(6, expense.getId());

			// 4 execute the statement1
			int numberOfRecAffected = preparedstatement.executeUpdate();
			if (numberOfRecAffected > 0) {
				isEditExpense = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);

		}

		return isEditExpense;
	}

	// ..............................DeleteExpense............................

	@Override
	public boolean deleteExpense(int expenseid) {
		boolean isDeleteExpense = false;

		PreparedStatement preparedstatement = null;
		Connection connection = null;
		// 1. Register the driver
		try {
			connection = DBUtil.getConnection();

			// 3.Create the statement
			String query = "delete from expense WHERE ID=?";
			preparedstatement = connection.prepareStatement(query);

			// 3.1 Set the parameter values into the prepared statement valu

			preparedstatement.setInt(1, expenseid);

			// 4 execute the statement1
			int numberOfRecAffected = preparedstatement.executeUpdate();
			if (numberOfRecAffected > 0) {
				isDeleteExpense = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}
		return isDeleteExpense;
	}

	//...............................ResetPassword.................................
	@Override
	public boolean resetPassword(int userid, String newPassword, String Currentpassword) {

		boolean isResetPassword = false;

		PreparedStatement preparedstatement = null;
		Connection connection = null;
		// 1. Register the driver
		try {
			connection = DBUtil.getConnection();

			// 3.Create the statement
			String query = "update user set password=? where id=? and password=?";
			preparedstatement = connection.prepareStatement(query);

			// 3.1 Set the parameter values into the prepared statement value

			preparedstatement.setString(1, newPassword);
			preparedstatement.setInt(2, userid);
			preparedstatement.setString(3, Currentpassword);

			// 4 execute the statement
			int numberOfRecAffected = preparedstatement.executeUpdate();
			if (numberOfRecAffected > 0) {
				isResetPassword = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}

		return isResetPassword;
	}
	

	// ..................GetExpenseOptions...................
	@Override
	public List<Expense> getExpenseOptions(int userid) {

		List<Expense> expenseOptions = new ArrayList<>();
		Expense expense = null;

		PreparedStatement preparedstatement = null;
		Connection connection = null;
		// 1. Register the driver
		try {
			connection = DBUtil.getConnection();

			// 3.Create the statement
			String query = "select * from expense where userid=?";
			preparedstatement = connection.prepareStatement(query);

			preparedstatement.setInt(1, userid);

			// 4 execute the statement

			ResultSet resultset = preparedstatement.executeQuery();

			// 4.1 Processing the result set
			while (resultset.next()) {

				int id = resultset.getInt(1);
				String type = resultset.getString(2);
				String date = resultset.getString(3);
				float price = resultset.getFloat(4);
				int noofitems = resultset.getInt(5);
				float total = resultset.getFloat(6);
				//String bywhom = resultset.getString(7);
				int uId = resultset.getInt(7);

				// Load the column data into expense object

				expense = new Expense();
				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNoofItems(noofitems);
				expense.setTotal(total);
				//expense.setBywhom(bywhom);
				expense.setUserid(uId);

				// Add the expense object to expenseOptions
				expenseOptions.add(expense);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}

		return expenseOptions;
	}

	// ..........................................GetExpense.........................

	@Override
	public Expense getExpense(int expenseId) {

		Expense expense = null;
		PreparedStatement preparedstatement = null;
		Connection connection = null;
		// 1. Register the driver
		try {
			connection = DBUtil.getConnection();

			// 3.Create the statement
			String query = "select * from expense where id=?";
			preparedstatement = connection.prepareStatement(query);
			preparedstatement.setInt(1, expenseId);

			// 4 execute the statement

			ResultSet resultset = preparedstatement.executeQuery();

			// 4.1 Processing the result set
			while (resultset.next()) {

				int id = resultset.getInt(1);
				String type = resultset.getString(2);
				String date = resultset.getString(3);
				float price = resultset.getFloat(4);
				int noofitems = resultset.getInt(5);
				float total = resultset.getFloat(6);
				//String bywhom = resultset.getString(7);
				int expenseid = resultset.getInt(7);

// Load the column data into expense object

				expense = new Expense();
				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNoofItems(noofitems);
				expense.setTotal(total);
				//expense.setBywhom(bywhom);
				expense.setUserid(expenseid);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);

		}

		return expense;
	}

	// ................ExpenseReport.......................
	@Override
	public List<Expense> expenseReport(int userid) {

		List<Expense> expenseReport = new ArrayList<>();
		Expense expense = null;

		PreparedStatement preparedstatement = null;
		Connection connection = null;
		// 1. Register the driver
		try {
			connection = DBUtil.getConnection();

			// 3.Create the statement
			String query = "select * from expense where userid=?";
			preparedstatement = connection.prepareStatement(query);

			preparedstatement.setInt(1, userid);

			// 4 execute the statement

			ResultSet resultset = preparedstatement.executeQuery();

			// 4.1 Processing the result set
			while (resultset.next()) {

				int id = resultset.getInt(1);
				String typee = resultset.getString(2);
				String date = resultset.getString(3);
				float price = resultset.getFloat(4);
				int noofitems = resultset.getInt(5);
				float total = resultset.getFloat(6);
				//String bywhom = resultset.getString(7);
				int uId = resultset.getInt(7);

				// Load the column data into expense object

				expense = new Expense();
				expense.setId(id);
				expense.setType(typee);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNoofItems(noofitems);
				expense.setTotal(total);
				//expense.setBywhom(bywhom);
				expense.setUserid(uId);

				// Add the expense object to expenseOptions
				expenseReport.add(expense);

			}

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}

		return expenseReport;

	}

	// ................GetUser...........................
	@Override
	public User getUser(int UserId) {
		User user = null;

		PreparedStatement preparedstatement = null;
		Connection connection = null;
		// 1. Register the driver
		try {
			connection = DBUtil.getConnection();

			// 3.Create the statement
			String query = "select * from user where id=?";
			preparedstatement = connection.prepareStatement(query);

			preparedstatement.setInt(1, UserId);
			// 4 execute the statement

			ResultSet resultset = preparedstatement.executeQuery();

			// 4.1 Processing the result set
			while (resultset.next()) {

				// a
				int id = resultset.getInt(1);
				String firstname = resultset.getString(2);
				String middlename = resultset.getString(3);
				String lastname = resultset.getString(4);
				String email = resultset.getString(5);
				String username = resultset.getString(6);
				String password = resultset.getString(7);
				String mobile = resultset.getString(8);

				

				// c.Load the user column data into User Object

				user = new User();

				user.setId(id);
				user.setFirstname(firstname);
				user.setMiddlename(middlename);
				user.setLastname(lastname);
				user.setEmail(email);
				user.setUsername(username);
				user.setPassword(password);

				user.setMobile(mobile);

				// d.load the address column data into address Objec

			}

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("exceptionOccured");
		} finally {

			DBUtil.closeConnection(connection);
		}

		return user;
	}

	// .............................updateUser...........................
	@Override
	public boolean updateUser(User user) {

		boolean isUpdateUser = false;

		PreparedStatement preparedstatement = null;
		Connection connection = null;
		try {
			// 1. Register the driver
			connection = DBUtil.getConnection();

			// 3.Create the statement
			String query = "update user set firstname=? , middlename=?,lastname = ?,email=?,mobile = ? where id=? ";
			preparedstatement = connection.prepareStatement(query);

			// 3.1 Set the parameter values into the prepared statement value

			preparedstatement.setString(1, user.getFirstname());
			preparedstatement.setString(2, user.getMiddlename());
			preparedstatement.setString(3, user.getLastname());
			preparedstatement.setString(4, user.getEmail());
			preparedstatement.setString(5, user.getMobile());
			preparedstatement.setInt(6, user.getId());

			// 4 execute the statement
			int numberOfRecAffected = preparedstatement.executeUpdate();
			if (numberOfRecAffected <= 0) {
				// System.out.println("User profile Update failed....");
			} else {
				System.out.println(numberOfRecAffected);
				isUpdateUser = true; 
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}

		return isUpdateUser;
	}

	
}
	