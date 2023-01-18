package vn.ptit.model.order;

public class Cash extends Payment {
	private double cashTendered;

	public Cash() {
		// TODO Auto-generated constructor stub
	}

	public Cash(double totalMoney, double cashTendered) {
		super(totalMoney);
		this.cashTendered = cashTendered;
	}

	public double getCashTendered() {
		return cashTendered;
	}

	public void setCashTendered(double cashTendered) {
		this.cashTendered = cashTendered;
	}

}
