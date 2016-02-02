package edu.pragmatic.homework.sql.jdbc;

public class Employees extends People {

	private int emp_no;
	private String hire_date;
	private Department department;
	private Manager manager;

	public Employees() {
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getHire_date() {
		return hire_date;
	}

	public void setHire_date(String hire_date) {
		this.hire_date = hire_date;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	@Override
	public String toString() {
		String.format("%d\t,%s\t,%s\t,%s\t,%s\t,%s\t,%s\t,", getEmp_no(),
				getFirst_name(), getLast_name(), getBirth_date(), getGender(),
				getDepartment(), getHire_date());
		return null;

	}

}
