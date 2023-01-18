package vn.ptit.business.controllers.admin.staff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import vn.ptit.business.bookdao.PublisherDAO;
import vn.ptit.business.bookdao.PublisherDAOImpl;
import vn.ptit.business.persondao.CustomerDAO;
import vn.ptit.business.persondao.CustomerDAOImpl;
import vn.ptit.business.persondao.StaffDAO;
import vn.ptit.business.persondao.StaffDAOImpl;
import vn.ptit.model.book.Author;
import vn.ptit.model.book.Book;
import vn.ptit.model.book.Publisher;
import vn.ptit.model.person.Customer;
import vn.ptit.model.person.Staff;

@WebServlet(urlPatterns = "/admin/manage/staff")
public class ManageStaffController extends HttpServlet {

	private StaffDAO staffDAO;

	@Override
	public void init() throws ServletException {
		staffDAO = new StaffDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Staff> staffs = staffDAO.findAll(); 
		
		req.setAttribute("staffs", staffs);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/staff/manage_staff.jsp");
		requestDispatcher.forward(req, resp);
	}
}
