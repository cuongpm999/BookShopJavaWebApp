package vn.ptit.model.person;

import java.util.Date;

public class Staff extends Person{
	private String position;
	private Date dateStart;
	
	public Staff() {
		// TODO Auto-generated constructor stub
	}

	public Staff(String mobile, String sex, Date dateOfBirth, FullName fullName, Address address, Account account,
			String email, boolean status, String position, Date dateStart) {
		super(mobile, sex, dateOfBirth, fullName, address, account, email, status);
		this.position = position;
		this.dateStart = dateStart;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

}
