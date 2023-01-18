package vn.ptit.model.order;

public class LineBookItem {
	private int id;
	private int quantity;
	
	public LineBookItem() {
		// TODO Auto-generated constructor stub
	}

	public LineBookItem(int id, int quantity) {
		super();
		this.id = id;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
