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
import vn.ptit.business.persondao.StaffDAO;
import vn.ptit.business.persondao.StaffDAOImpl;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.order.Cart;
import vn.ptit.model.person.Customer;
import vn.ptit.model.person.Staff;

@WebServlet(urlPatterns = "/login-staff")
public class LoginStaffController extends HttpServlet {
	private StaffDAO staffDAO;

	@Override
	public void init() throws ServletException {
		staffDAO = new StaffDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login_staff.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String username = req.getParameter("username");
		String password = req.getParameter("password");

		Staff staff = null;
		if (username != null && password != null) {
			staff = staffDAO.checkLogin(username, password);
		}
		if (staff == null) {
			req.setAttribute("status", "failed");
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/login_staff.jsp");
			requestDispatcher.forward(req, resp);
			return;
		}
		req.getSession().setAttribute("staffLogin", staff);
		
		resp.sendRedirect("/BookStoreOnline/admin");
	}

}
