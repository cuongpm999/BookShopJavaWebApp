package vn.ptit.model.order;

import java.util.Date;
import java.util.List;

public class Cart {
	private int id;
	private Date dateCreate;
	private double totalAmount;
	private int totalQuantity;
	private List<LineBookItem> lineBookItems;
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Cart(int id, Date dateCreate, double totalAmount, int totalQuantity, List<LineBookItem> lineBookItems) {
		super();
		this.id = id;
		this.dateCreate = dateCreate;
		this.totalAmount = totalAmount;
		this.totalQuantity = totalQuantity;
		this.lineBookItems = lineBookItems;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public List<LineBookItem> getLineBookItems() {
		return lineBookItems;
	}

	public void setLineBookItems(List<LineBookItem> lineBookItems) {
		this.lineBookItems = lineBookItems;
	}

}
