package vn.ptit.model.book;

public class BookItemStat extends BookItem{
	private int quantityBought;
	private double revenue;
	
	public BookItemStat() {
		// TODO Auto-generated constructor stub
	}

	public BookItemStat(String barCode, double price, double discount, String img, Book book, boolean status,
			int quantityBought, double revenue) {
		super(barCode, price, discount, img, book, status);
		this.quantityBought = quantityBought;
		this.revenue = revenue;
	}

	public int getQuantityBought() {
		return quantityBought;
	}

	public void setQuantityBought(int quantityBought) {
		this.quantityBought = quantityBought;
	}

	public double getRevenue() {
		return revenue;
	}

	public void setRevenue(double revenue) {
		this.revenue = revenue;
	}

}
