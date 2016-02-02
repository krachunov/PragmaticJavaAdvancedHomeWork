package edu.pragmatic.homework.sql.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorkWithDataBase {
	private String host;
	private String port;
	private String sid;
	private String userName;
	private String password;

	/**
	 * 
	 * @param host
	 *            - String
	 * @param port
	 *            - String
	 * @param sid
	 *            - String
	 * @param userName
	 *            - String
	 * @param password
	 *            - String
	 */
	public WorkWithDataBase(String host, String port, String sid,
			String userName, String password) {
		this.host = host;
		this.port = port;
		this.sid = sid;
		this.userName = userName;
		this.password = password;
	}

	private Connection getConnection() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = String.format("jdbc:mysql://%s:%s/%s", getHost(),
				getPort(), getSid());

		return DriverManager.getConnection(url, getUserName(), getPassword());
	}

	/**
	 * SELECT * FROM employees
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void showAllEmployees() throws SQLException, ClassNotFoundException {
		String selectAllEmp = "SELECT * FROM employees";
		Connection conn = getConnection();

		PreparedStatement preStatement = conn.prepareStatement(selectAllEmp);

		ResultSet result = preStatement.executeQuery();

		printAllEmploees(result);

	}

	/**
	 * SELECT * FROM employees ORDER BY first_name ASC
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void showAllEmployeesOrderedByFirstName() throws SQLException,
			ClassNotFoundException {
		String selectAllEmpOrderByFirstName = "SELECT * FROM employees ORDER BY first_name ASC";
		Connection conn = getConnection();

		PreparedStatement preStatement = conn
				.prepareStatement(selectAllEmpOrderByFirstName);

		ResultSet result = preStatement.executeQuery();

		printAllEmploees(result);

	}

	private void printAllEmploees(ResultSet result) throws SQLException {
		while (result.next()) {
			final int empNumber = result.getInt("emp_no");
			final String firstName = result.getString("first_name");
			final String lastName = result.getString("last_name");
			final String birthDay = result.getString("birth_date");
			final String gender = result.getString("gender");
			final String hireDate = result.getString("hire_date");

			final Employees employee = new Employees();
			employee.setEmp_no(empNumber);
			employee.setFirst_name(firstName);
			employee.setLast_name(lastName);
			employee.setBirth_date(birthDay);
			employee.setGender(gender);
			employee.setHire_date(hireDate);
			System.out.printf("%d\t%10s\t%10s\t%10s\t%10s\t%10s\n",
					employee.getEmp_no(), employee.getFirst_name(),
					employee.getLast_name(), employee.getBirth_date(),
					employee.getGender(), employee.getHire_date());
		}
	}

	/**
	 * SELECT e.first_name,e.last_name,d.dept_name FROM employees e join
	 * dept_emp de join departments d where d.dept_no=de.dept_no and
	 * e.emp_no=de.emp_no
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void showAllEmployeesAndDepart() throws SQLException,
			ClassNotFoundException {
		String selectAllEmpAndDepart = "SELECT e.first_name,e.last_name,d.dept_name FROM employees e join dept_emp de join departments d where  d.dept_no=de.dept_no and e.emp_no=de.emp_no";
		Connection conn = getConnection();

		PreparedStatement preStatement = conn
				.prepareStatement(selectAllEmpAndDepart);

		ResultSet result = preStatement.executeQuery();

		printAllEmpAndDepart(result);

	}

	private void printAllEmpAndDepart(ResultSet result) throws SQLException {
		while (result.next()) {
			final String firstName = result.getString("first_name");
			final String lastName = result.getString("last_name");
			final String deptName = result.getString("dept_name");
			final Employees employee = new Employees();
			employee.setFirst_name(firstName);
			employee.setLast_name(lastName);
			employee.setDepartment(new Department(deptName));
			System.out.printf("%10s\t%10s\t%10s\n", employee.getFirst_name(),
					employee.getLast_name(), employee.getDepartment()
							.getDepName());
		}
	}

	/**
	 * SELECT e.emp_no,e.first_name,e.last_name,(select e1.first_name from
	 * employees e1 where e1.emp_no=dm.emp_no ) manager_name FROM employees e,
	 * dept_emp de, dept_manager dm where dm.dept_no=de.dept_no and
	 * e.emp_no=de.emp_no ORDER BY manager_name ASC
	 * 
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void showAllEmployeesAndManager() throws SQLException,
			ClassNotFoundException {
		String selectAllEmpAndDepart = "SELECT e.emp_no,e.first_name,e.last_name,(select e1.first_name from employees e1 where e1.emp_no=dm.emp_no ) manager_name FROM employees e, dept_emp de, dept_manager dm where  dm.dept_no=de.dept_no  and e.emp_no=de.emp_no ORDER BY manager_name ASC";
		Connection conn = getConnection();

		PreparedStatement preStatement = conn
				.prepareStatement(selectAllEmpAndDepart);

		ResultSet result = preStatement.executeQuery();

		printAllEmpAndManager(result);

	}

	private void printAllEmpAndManager(ResultSet result) throws SQLException {
		while (result.next()) {
			final String firstName = result.getString("first_name");
			final String lastName = result.getString("last_name");
			final String managerName = result.getString("manager_name");
			final Employees employee = new Employees();
			employee.setFirst_name(firstName);
			employee.setLast_name(lastName);
			employee.setManager(new Manager(managerName));
			System.out.printf("Employee:%10s\t%10s\tManager%10s\n", employee
					.getFirst_name(), employee.getLast_name(), employee
					.getManager().getFirst_name());
		}

	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getSid() {
		return sid;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

}
