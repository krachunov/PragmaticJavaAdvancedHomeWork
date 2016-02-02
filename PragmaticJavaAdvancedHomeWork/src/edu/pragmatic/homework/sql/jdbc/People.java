package edu.pragmatic.homework.sql.jdbc;

public abstract class People {
	private String birth_date;
	private String first_name;
	private String last_name;
	private String gender;

	public People() {
	}

	public People(String birth_date, String first_name, String last_name,
			String gender) {
		this.birth_date = birth_date;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
	}

	public String getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(String birth_date) {
		this.birth_date = birth_date;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
