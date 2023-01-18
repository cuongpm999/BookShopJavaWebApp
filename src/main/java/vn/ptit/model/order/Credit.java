package vn.ptit.model.order;

import java.util.Date;

public class Credit extends Payment {
	private String number;
	private String type;
	private String date;

	public Credit() {
		// TODO Auto-generated constructor stub
	}

	public Credit(double totalMoney, String number, String type, String date) {
		super(totalMoney);
		this.number = number;
		this.type = type;
		this.date = date;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
