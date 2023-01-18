package vn.ptit.business.persondao;

import java.util.List;

import vn.ptit.model.order.Order;
import vn.ptit.model.person.Customer;
import vn.ptit.model.person.CustomerStat;

public interface CustomerDAO {
	public boolean insert(Customer customer);
	public List<Customer> findAll();
	public int delete(int id);
	public boolean update(Customer customer);
	public Customer findById(int id);
	public Customer findByUsername(String username);
	public Customer findByEmail(String email);
	public Customer checkLogin(String username, String password);
	public Customer findByOrder(Order order);
}
