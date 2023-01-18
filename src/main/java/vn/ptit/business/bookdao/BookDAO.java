package vn.ptit.business.bookdao;

import java.util.List;

import vn.ptit.model.book.Book;

public interface BookDAO {
	public boolean insert(Book book);
	public List<Book> findAll();
	public boolean delete(int id);
	public boolean update(Book book);
	public Book findById(int id);

}
