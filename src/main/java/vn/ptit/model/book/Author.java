package vn.ptit.model.book;

public class Author {
	private int id;
	private String name;
	private String biography;
	private boolean status;
	
	public Author() {
		// TODO Auto-generated constructor stub
	}

	public Author(String name, String biography, boolean status) {
		super();
		this.name = name;
		this.biography = biography;
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

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
