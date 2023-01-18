package vn.ptit.model.person;

public class Address {
	private int id;
	private int number;
	private String street;
	private String district;
	private String city;
	
	public Address() {
		// TODO Auto-generated constructor stub
	}

	public Address(int number, String street, String district, String city) {
		super();
		this.number = number;
		this.street = street;
		this.district = district;
		this.city = city;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}	

}
