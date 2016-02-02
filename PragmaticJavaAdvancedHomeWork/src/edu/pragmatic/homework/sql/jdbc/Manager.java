package edu.pragmatic.homework.sql.jdbc;

import java.util.Date;

public class Manager extends Employees {
	private String departNumber;
	private Date fromDate;
	private Date toDate;

	public Manager(String managerName) {
		setFirst_name(managerName);
	}

	public Manager(String dept_no, Date fromDate, Date toDate) {
		this.departNumber = dept_no;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public String getDept_no() {
		return departNumber;
	}

	public void setDept_no(String dept_no) {
		this.departNumber = dept_no;
	}

	public String getDepartNumber() {
		return departNumber;
	}

	public void setDepartNumber(String departNumber) {
		this.departNumber = departNumber;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}
