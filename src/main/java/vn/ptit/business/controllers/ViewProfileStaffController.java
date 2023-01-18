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
import vn.ptit.model.person.Staff;

@WebServlet(urlPatterns = "/admin/view-profile-staff")
public class ViewProfileStaffController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession httpSession = req.getSession();
		Staff staff = new Staff();
		if (httpSession.getAttribute("staffLogin") != null) {
			staff = (Staff) httpSession.getAttribute("staffLogin");
		}
		req.setAttribute("staff", staff);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/view_profile_staff.jsp");
		requestDispatcher.forward(req, resp);
	}
}
