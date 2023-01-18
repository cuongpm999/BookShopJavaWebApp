package vn.ptit.model.person;

import java.util.Date;

public class Person {
	private int id;
	private String mobile;
	private String sex;
	private Date dateOfBirth;
	private FullName fullName;
	private Address address;
	private Account account;
	private String email;
	private boolean status;
	
	public Person() {
		// TODO Auto-generated constructor stub
	}

	public Person(String mobile, String sex, Date dateOfBirth, FullName fullName, Address address,
			Account account, String email, boolean status) {
		super();
		this.mobile = mobile;
		this.sex = sex;
		this.dateOfBirth = dateOfBirth;
		this.fullName = fullName;
		this.address = address;
		this.account = account;
		this.email = email;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public FullName getFullName() {
		return fullName;
	}

	public void setFullName(FullName fullName) {
		this.fullName = fullName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
