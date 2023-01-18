package vn.ptit.business.orderdao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.ptit.model.book.BookItem;
import vn.ptit.model.order.Order;
import vn.ptit.model.person.Customer;

public interface OrderDAO {
	public boolean insert(Customer customer, Order order);
	public List<Order> getOrderByCustomer(Customer customer);
	public List<Order> findAll();
	public Order findById(int id);
}
