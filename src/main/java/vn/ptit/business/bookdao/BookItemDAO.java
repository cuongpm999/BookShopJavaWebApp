package vn.ptit.business.bookdao;

import java.util.List;
import java.util.Map;

import vn.ptit.model.book.BookItem;
import vn.ptit.model.book.BookItemStat;
import vn.ptit.model.order.LineBookItem;

public interface BookItemDAO {
	public int insert(BookItem bookItem);
	public List<BookItem> findAll();
	public int delete(String barCode);
	public int update(BookItem bookItem);
	public BookItem findByBarCode(String barCode);
	public List<BookItem> findByName(String name);
	public List<BookItem> findByCategory(String name);
	public List<BookItem> findAllWithFilter(Map<String, Object> map);
	public BookItem findByLineItem(LineBookItem lineBookItem);
}
