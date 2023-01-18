package vn.ptit.business.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.ptit.business.bookdao.BookItemDAO;
import vn.ptit.business.bookdao.BookItemDAOImpl;
import vn.ptit.business.orderdao.CartDAO;
import vn.ptit.business.orderdao.CartDAOImpl;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.order.Cart;
import vn.ptit.model.order.LineBookItem;
import vn.ptit.model.person.Customer;

@WebServlet(urlPatterns = "/cart")
public class CartController extends HttpServlet {
	private CartDAO cartDAO;
	private BookItemDAO bookItemDAO;

	@Override
	public void init() throws ServletException {
		cartDAO = new CartDAOImpl();
		bookItemDAO = new BookItemDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customer customer = new Customer();
		HttpSession httpSession = req.getSession();
		if (httpSession.getAttribute("customerLogin") != null) {
			customer = (Customer) httpSession.getAttribute("customerLogin");
		}

		Cart cart = cartDAO.findCurrentCartByCustomer(customer);
		if (cart != null) {
			HashMap<BookItem, Integer> mapLineItem = new HashMap<>();
			for (LineBookItem lineBookItem : cart.getLineBookItems()) {
				BookItem bookItem = bookItemDAO.findByLineItem(lineBookItem);
				mapLineItem.put(bookItem, lineBookItem.getQuantity());
			}

			req.setAttribute("mapLineItem", mapLineItem);
		}
		req.getSession().setAttribute("cart", cart);

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
		requestDispatcher.forward(req, resp);
	}

}
