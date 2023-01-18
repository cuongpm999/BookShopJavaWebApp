package vn.ptit.business.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import vn.ptit.model.person.Customer;

@WebServlet(urlPatterns = "/view-profile")
public class ViewProfileCustomerController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Customer customer = new Customer();
		if (httpSession.getAttribute("customerLogin") != null) {
			customer = (Customer) httpSession.getAttribute("customerLogin");
		}
		req.setAttribute("customer", customer);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/view_profile.jsp");
		requestDispatcher.forward(req, resp);
	}
}
