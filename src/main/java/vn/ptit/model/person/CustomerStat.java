package vn.ptit.model.person;

import java.util.Date;

public class CustomerStat extends Customer{
	private double revenue;
	
	public CustomerStat() {
		// TODO Auto-generated constructor stub
	}

	public CustomerStat(String mobile, String sex, Date dateOfBirth, FullName fullName, Address address,
			Account account, String email, boolean status, double point, String note, double revenue) {
		super(mobile, sex, dateOfBirth, fullName, address, account, email, status, point, note);
		this.revenue = revenue;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}
	
}
