package vn.ptit.business.controllers;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.ptit.business.orderdao.CartDAO;
import vn.ptit.business.orderdao.CartDAOImpl;
import vn.ptit.business.persondao.CustomerDAO;
import vn.ptit.business.persondao.CustomerDAOImpl;
import vn.ptit.business.utils.GooglePojo;
import vn.ptit.business.utils.GoogleUtils;
import vn.ptit.business.utils.RandomString;
import vn.ptit.model.person.Account;
import vn.ptit.model.person.Address;
import vn.ptit.model.person.Customer;
import vn.ptit.model.person.FullName;

@WebServlet(urlPatterns = "/login-google")
public class LoginGoogleController extends HttpServlet{
	private CustomerDAO customerDAO;
	private CartDAO cartDAO;

	@Override
	public void init() throws ServletException {
		customerDAO = new CustomerDAOImpl();
		cartDAO = new CartDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");

		if (code == null || code.isEmpty()) {
			resp.sendRedirect("redirect:/login?google=error");
			return;
		}
		String accessToken = GoogleUtils.getToken(code);

		GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
		System.out.println(googlePojo);

		Customer customer = customerDAO.findByEmail(googlePojo.getEmail());
		
		if (customer == null) {
			FullName fullName = new FullName(googlePojo.getName(), "no", "no");
			Address address = new Address(0, "no", "no", googlePojo.getLocale());
			RandomString session = new RandomString();
			Account account = new Account(session.nextString(), session.nextString());
			Customer customerNew = new Customer("no", "no", new Date(), fullName, address, account, googlePojo.getEmail(), true, 0, "");
			boolean flag = customerDAO.insert(customerNew);
			req.getSession().setAttribute("customerLogin", customerNew);
			resp.sendRedirect("/BookStoreOnline");
			return;
		}

		req.getSession().setAttribute("customerLogin", customer);
		resp.sendRedirect("/BookStoreOnline");

	}
}
