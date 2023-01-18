package vn.ptit.model.order;

public class ShipmentStat extends Shipment{
	private int quantityShipped;
	private double revenue;
	
	public ShipmentStat() {
		// TODO Auto-generated constructor stub
	}

	public ShipmentStat(String name, double price, String address, boolean status, int quantityShipped,
			double revenue) {
		super(name, price, address, status);
		this.quantityShipped = quantityShipped;
		this.revenue = revenue;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

	public int getQuantityShipped() {
		return quantityShipped;
	}

	public void setQuantityShipped(int quantityShipped) {
		this.quantityShipped = quantityShipped;
	}

}
