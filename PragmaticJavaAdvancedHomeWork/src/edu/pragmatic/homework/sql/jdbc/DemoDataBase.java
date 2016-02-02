package edu.pragmatic.homework.sql.jdbc;

import java.sql.SQLException;

public class DemoDataBase {

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		WorkWithDataBase data = new WorkWithDataBase("localhost", "3306",
				"employees", "user", "Password");

		data.showAllEmployees();

		// data.showAllEmployeesOrderedByFirstName();

		// data.showAllEmployeesAndDepart();

		// data.showAllEmployeesAndManager();

	}

}
