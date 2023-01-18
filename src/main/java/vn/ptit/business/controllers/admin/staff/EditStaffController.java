package vn.ptit.business.controllers.admin.staff;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import vn.ptit.model.person.Account;
import vn.ptit.model.person.Address;
import vn.ptit.model.person.Customer;
import vn.ptit.model.person.FullName;
import vn.ptit.model.person.Staff;

@WebServlet(urlPatterns = "/admin/manage/staff/edit")
public class EditStaffController extends HttpServlet {
	private StaffDAO staffDAO;

	@Override
	public void init() throws ServletException {
		staffDAO = new StaffDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Staff staff = staffDAO.findById(id);
		req.setAttribute("staff", staff);
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/admin/staff/edit_staff.jsp");
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
		Date dateStart = null;
		try {
			dateOfBirth = simpleDateFormat.parse(req.getParameter("dob"));
			dateStart = simpleDateFormat.parse(req.getParameter("dateS"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int number = Integer.parseInt(req.getParameter("number"));
		String street = req.getParameter("street");
		String district = req.getParameter("district");
		String city = req.getParameter("city");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String position = req.getParameter("position");

		FullName fullName = new FullName(firstName, middleName, lastName);
		Address address = new Address(number, street, district, city);
		Account account = new Account(username, password);
		Staff staff = new Staff(mobile, sex, dateOfBirth, fullName, address, account, email, true, position, dateStart);
		staff.setId(id);

		boolean flag = staffDAO.update(staff);

		if (flag)
			resp.sendRedirect("/BookStoreOnline/admin/manage/staff");
		else {
			req.setAttribute("status", "failed");
			req.setAttribute("staff", staff);
			RequestDispatcher requestDispatcher = getServletContext()
					.getRequestDispatcher("/admin/staff/edit_staff.jsp");
			requestDispatcher.forward(req, resp);
		}
	}

}
