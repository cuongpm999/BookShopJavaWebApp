package vn.ptit.model.order;

public class DigitalWallet extends Payment {
	private String name;

	public DigitalWallet() {
		// TODO Auto-generated constructor stub
	}

	public DigitalWallet(double totalMoney, String name) {
		super(totalMoney);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
