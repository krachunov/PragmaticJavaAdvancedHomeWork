package edu.pragmatic.homework.sql.jdbc;

import java.util.List;

public class Department {
	private String depName;
	private String depNumber;
	private Manager manager;
	private List<Employees> employees;

	public Department(String depName) {
		this.depName = depName;
	}

	public String getDepName() {
		return depName;
	}

	public String getDepNumber() {
		return depNumber;
	}

	public void setDepNumber(String depNumber) {
		this.depNumber = depNumber;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public List<Employees> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employees> employees) {
		this.employees = employees;
	}
	

}
