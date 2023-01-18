package vn.ptit.model.book;

import java.util.List;

public class Book {
	private int id;
	private String title;
	private String category;
	private String summary;
	private int pages;
	private String language;
	private List<Author> authors;
	private Publisher publisher;
	private boolean status;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Book(String title, String category, String summary, int pages, String language, List<Author> authors,
			Publisher publisher, boolean status) {
		super();
		this.title = title;
		this.category = category;
		this.summary = summary;
		this.pages = pages;
		this.language = language;
		this.authors = authors;
		this.publisher = publisher;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
}
