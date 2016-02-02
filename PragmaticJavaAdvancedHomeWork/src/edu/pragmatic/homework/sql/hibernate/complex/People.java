package edu.pragmatic.homework.sql.hibernate.complex;

public abstract class People {
	private Long personId;
	private String birthDate;
	private String firstName;
	private String lastLame;
	private String gender;

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastLame() {
		return lastLame;
	}

	public void setLastLame(String lastLame) {
		this.lastLame = lastLame;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
