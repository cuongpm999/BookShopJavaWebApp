package vn.ptit.business.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.ptit.business.bookdao.BookItemDAO;
import vn.ptit.business.bookdao.BookItemDAOImpl;
import vn.ptit.business.orderdao.OrderDAO;
import vn.ptit.business.orderdao.OrderDAOImpl;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.order.LineBookItem;
import vn.ptit.model.order.Order;
import vn.ptit.model.person.Customer;

@WebServlet(urlPatterns = "/my-order")
public class MyOrderController extends HttpServlet {
	private OrderDAO orderDAO;
	private BookItemDAO bookItemDAO;

	@Override
	public void init() throws ServletException {
		orderDAO = new OrderDAOImpl();
		bookItemDAO = new BookItemDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Customer customer = new Customer();
		if (httpSession.getAttribute("customerLogin") != null) {
			customer = (Customer) httpSession.getAttribute("customerLogin");
		}
		HashMap<Order, HashMap<BookItem, Integer>> myorders = new HashMap<>();
		List<Order> orders = orderDAO.getOrderByCustomer(customer);
		for (Order order : orders) {
			HashMap<BookItem, Integer> mapLineItem = new HashMap<>();
			for (LineBookItem lineBookItem : order.getCart().getLineBookItems()) {
				BookItem bookItem = bookItemDAO.findByLineItem(lineBookItem);
				mapLineItem.put(bookItem, lineBookItem.getQuantity());
			}
			myorders.put(order, mapLineItem);
		}
		
		TreeMap<Order, HashMap<BookItem, Integer>> sorted = new TreeMap<>(new Comparator<Order>() {

			@Override
			public int compare(Order o1, Order o2) {
				return Long.compare(o2.getDateCreate().getTime(), o1.getDateCreate().getTime());
			}
		});
		
		sorted.putAll(myorders);
		
		req.setAttribute("myorders", sorted);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/myorder.jsp");
		requestDispatcher.forward(req, resp);
	}
}
