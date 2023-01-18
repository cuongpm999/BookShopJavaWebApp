package vn.ptit.business.controllers.admin.customer;

import java.io.IOException;
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
import vn.ptit.model.book.Publisher;

@WebServlet(urlPatterns = "/admin/manage/customer/delete")
public class DeleteCustomerController extends HttpServlet {
	private CustomerDAO customerDAO;

	@Override
	public void init() throws ServletException {
		customerDAO = new CustomerDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		customerDAO.delete(id);
		resp.sendRedirect("/BookStoreOnline/admin/manage/customer");
	}

}
