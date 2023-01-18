package vn.ptit.model.order;

public class IncomeStat {
	private String period;
	private int customer;
	private double revenue;
	
	public IncomeStat() {
		// TODO Auto-generated constructor stub
	}

	public IncomeStat(String period, int customer, double revenue) {
		super();
		this.period = period;
		this.customer = customer;
		this.revenue = revenue;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getCustomer() {
		return customer;
	}

	public void setCustomer(int customer) {
		this.customer = customer;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

}
