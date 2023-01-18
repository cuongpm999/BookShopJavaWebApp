package vn.ptit.model.book;

public class Publisher {
	private int id;
	private String name;
	private String address;
	private boolean status;
	
	public Publisher() {
		// TODO Auto-generated constructor stub
	}

	public Publisher(String name, String address, boolean status) {
		super();
		this.name = name;
		this.address = address;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
