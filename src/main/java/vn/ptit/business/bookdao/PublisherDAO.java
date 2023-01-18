package vn.ptit.business.bookdao;

import java.util.List;

import vn.ptit.model.book.Publisher;

public interface PublisherDAO {
	public int insert(Publisher publisher);
	public List<Publisher> findAll();
	public int delete(int id);
	public int update(Publisher publisher);
	public Publisher findById(int id);
}
