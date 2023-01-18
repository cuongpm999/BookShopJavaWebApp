package vn.ptit.business.orderdao;

import java.util.HashMap;

import vn.ptit.model.book.BookItem;
import vn.ptit.model.order.Cart;
import vn.ptit.model.person.Customer;

public interface CartDAO {
	public void addToCart(BookItem bookItem, Customer customer);
	public void editCart(BookItem bookItem, int quantity, Customer customer);
	public void deleteCart(BookItem bookItem, Customer customer);
	public Cart findCurrentCartByCustomer(Customer customer);
}
