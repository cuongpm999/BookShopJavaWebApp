package vn.ptit.business.controllers.admin.order;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ptit.business.bookdao.AuthorDAO;
import vn.ptit.business.bookdao.AuthorDAOImpl;
import vn.ptit.business.bookdao.BookDAO;
import vn.ptit.business.bookdao.BookDAOImpl;
import vn.ptit.business.bookdao.BookItemDAO;
import vn.ptit.business.bookdao.BookItemDAOImpl;
import vn.ptit.business.bookdao.PublisherDAO;
import vn.ptit.business.bookdao.PublisherDAOImpl;
import vn.ptit.business.orderdao.OrderDAO;
import vn.ptit.business.orderdao.OrderDAOImpl;
import vn.ptit.business.persondao.CustomerDAO;
import vn.ptit.business.persondao.CustomerDAOImpl;
import vn.ptit.model.book.Author;
import vn.ptit.model.book.Book;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.book.Publisher;
import vn.ptit.model.order.Cash;
import vn.ptit.model.order.Credit;
import vn.ptit.model.order.DigitalWallet;
import vn.ptit.model.order.LineBookItem;
import vn.ptit.model.order.Order;
import vn.ptit.model.person.Customer;

@WebServlet(urlPatterns = "/admin/manage/order/detail")
public class DetailOrderController extends HttpServlet {
	private BookItemDAO bookItemDAO;
	private OrderDAO orderDAO;
	private CustomerDAO customerDAO;

	@Override
	public void init() throws ServletException {
		orderDAO = new OrderDAOImpl();
		customerDAO = new CustomerDAOImpl();
		bookItemDAO = new BookItemDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Order order = orderDAO.findById(id);
		HashMap<BookItem, Integer> mapLineItem = new HashMap<>();
		for (LineBookItem lineBookItem : order.getCart().getLineBookItems()) {
			BookItem bookItem = bookItemDAO.findByLineItem(lineBookItem);
			mapLineItem.put(bookItem, lineBookItem.getQuantity());
		}

		Customer customer = customerDAO.findByOrder(order);

		req.setAttribute("order", order);
		req.setAttribute("customer", customer);
		req.setAttribute("mapLineItem", mapLineItem);
		if(order.getPayment() instanceof Cash) {
			req.setAttribute("paymentTypeCash", (Cash) order.getPayment());
		}
		if(order.getPayment() instanceof DigitalWallet) {
			req.setAttribute("paymentTypeDigitalWallet", (DigitalWallet) order.getPayment());
		}
		if(order.getPayment() instanceof Credit) {
			req.setAttribute("paymentTypeCredit", (Credit) order.getPayment());
		}

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/order/order_detail.jsp");
		requestDispatcher.forward(req, resp);
	}
}
