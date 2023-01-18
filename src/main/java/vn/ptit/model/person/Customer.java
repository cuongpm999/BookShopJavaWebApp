package vn.ptit.model.person;

import java.util.Date;

public class Customer extends Person{
	private double point;
	private String note;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String mobile, String sex, Date dateOfBirth, FullName fullName, Address address, Account account,
			String email, boolean status, double point, String note) {
		super(mobile, sex, dateOfBirth, fullName, address, account, email, status);
		this.point = point;
		this.note = note;
	}

	public double getPoint() {
		return point;
	}

	public void setPoint(double point) {
		this.point = point;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}
