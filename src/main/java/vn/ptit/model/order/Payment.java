package vn.ptit.model.order;

public class Payment {
	private int id;
	private double totalMoney;
	
	public Payment() {
		// TODO Auto-generated constructor stub
	}
	
	public Payment(double totalMoney) {
		super();
		this.totalMoney = totalMoney;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}
