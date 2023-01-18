package vn.ptit.business.bookdao;

import java.util.List;

import vn.ptit.model.book.Author;

public interface AuthorDAO {
	public int insert(Author author);
	public List<Author> findAll();
	public int delete(int id);
	public int update(Author author);
	public Author findById(int id);
}
