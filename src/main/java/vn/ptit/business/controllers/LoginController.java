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

import vn.ptit.business.orderdao.CartDAO;
import vn.ptit.business.orderdao.CartDAOImpl;
import vn.ptit.business.persondao.CustomerDAO;
import vn.ptit.business.persondao.CustomerDAOImpl;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.order.Cart;
import vn.ptit.model.person.Customer;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
	private CustomerDAO customerDAO;
	private CartDAO cartDAO;

	@Override
	public void init() throws ServletException {
		customerDAO = new CustomerDAOImpl();
		cartDAO = new CartDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		Customer customer = null;
		if (username != null && password != null) {
			customer = customerDAO.checkLogin(username, password);
		}
		if (customer == null) {
			req.setAttribute("status", "failed");
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");
			requestDispatcher.forward(req, resp);
			return;
		}
		req.getSession().setAttribute("customerLogin", customer);
		Cart cart = cartDAO.findCurrentCartByCustomer(customer);
		req.getSession().setAttribute("cart", cart);
		resp.sendRedirect("/BookStoreOnline");
	}

}
