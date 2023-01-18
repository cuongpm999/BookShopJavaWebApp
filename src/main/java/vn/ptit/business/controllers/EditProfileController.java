package vn.ptit.business.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.ptit.business.persondao.CustomerDAO;
import vn.ptit.business.persondao.CustomerDAOImpl;
import vn.ptit.model.person.Account;
import vn.ptit.model.person.Address;
import vn.ptit.model.person.Customer;
import vn.ptit.model.person.FullName;

@WebServlet(urlPatterns = "/edit-profile")
public class EditProfileController extends HttpServlet {
	private CustomerDAO customerDAO;

	@Override
	public void init() throws ServletException {
		customerDAO = new CustomerDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Customer customer = new Customer();
		if (httpSession.getAttribute("customerLogin") != null) {
			customer = (Customer) httpSession.getAttribute("customerLogin");
		}
		req.setAttribute("customer", customer);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/edit_profile.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		int id = Integer.parseInt(req.getParameter("id"));

		String firstName = req.getParameter("firstName");
		String middleName = req.getParameter("middleName");
		String lastName = req.getParameter("lastName");

		String mobile = req.getParameter("mobile");
		String email = req.getParameter("email");
		String sex = req.getParameter("sex");
		Date dateOfBirth = null;
		try {
			dateOfBirth = simpleDateFormat.parse(req.getParameter("dob"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int number = Integer.parseInt(req.getParameter("number"));
		double point = Double.parseDouble(req.getParameter("point"));
		String street = req.getParameter("street");
		String district = req.getParameter("district");
		String city = req.getParameter("city");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String note = req.getParameter("note");

		FullName fullName = new FullName(firstName, middleName, lastName);
		Address address = new Address(number, street, district, city);
		Account account = new Account(username, password);
		Customer customer = new Customer(mobile, sex, dateOfBirth, fullName, address, account, email, true, point,
				note);
		customer.setId(id);

		boolean flag = customerDAO.update(customer);

		if (flag) {
			req.getSession().setAttribute("customerLogin", customer);
			resp.sendRedirect("/BookStoreOnline/");
		} else {
			req.setAttribute("status", "failed");
			req.setAttribute("customer", customer);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/edit_profile.jsp");
			requestDispatcher.forward(req, resp);
		}
	}

}