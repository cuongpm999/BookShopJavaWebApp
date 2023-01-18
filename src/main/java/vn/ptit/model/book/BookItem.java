package vn.ptit.model.book;

public class BookItem {
	private String barCode;
	private double price;
	private double discount;
	private String img;
	private Book book;
	private boolean status;
	
	public BookItem() {
		// TODO Auto-generated constructor stub
	}

	public BookItem(String barCode, double price, double discount, String img, Book book, boolean status) {
		super();
		this.barCode = barCode;
		this.price = price;
		this.discount = discount;
		this.img = img;
		this.book = book;
		this.status = status;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
