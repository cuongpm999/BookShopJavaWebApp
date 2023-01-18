package vn.ptit.business.controllers.admin.publisher;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ptit.business.bookdao.PublisherDAO;
import vn.ptit.business.bookdao.PublisherDAOImpl;
import vn.ptit.model.book.Publisher;

@WebServlet(urlPatterns = "/admin/manage/publisher/add")
public class AddPublisherController extends HttpServlet {
	private PublisherDAO publisherDAO;

	@Override
	public void init() throws ServletException {
		publisherDAO = new PublisherDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/publisher/add_publisher.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		String address = req.getParameter("address");

		Publisher publisher = new Publisher();
		publisher.setName(name);
		publisher.setAddress(address);
		publisher.setStatus(true);

		int flag = publisherDAO.insert(publisher);
		if (flag > 0)
			resp.sendRedirect("/BookStoreOnline/admin/manage/publisher");
		else {
			req.setAttribute("status", "failed");
			req.setAttribute("publisher", publisher);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/publisher/add_publisher.jsp");
			requestDispatcher.forward(req, resp);
		}
	}

}
