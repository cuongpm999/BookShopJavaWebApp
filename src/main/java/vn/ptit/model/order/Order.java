package vn.ptit.model.order;

import java.util.Date;

public class Order {
	private int id;
	private Date dateCreate;
	private String status;
	private Shipment shipment;
	private Cart cart;
	private Payment payment;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public Order(Date dateCreate, String status, Shipment shipment, Cart cart, Payment payment) {
		super();
		this.dateCreate = dateCreate;
		this.status = status;
		this.shipment = shipment;
		this.cart = cart;
		this.payment = payment;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

}
