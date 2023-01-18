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

@WebServlet(urlPatterns = "/admin/manage/publisher/edit")
public class EditPublisherController extends HttpServlet {
	private PublisherDAO publisherDAO;

	@Override
	public void init() throws ServletException {
		publisherDAO = new PublisherDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Publisher publisher = publisherDAO.findById(id);
		req.setAttribute("publisher", publisher);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/publisher/edit_publisher.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		int id = Integer.parseInt(req.getParameter("id")); 

		Publisher publisher = new Publisher();
		publisher.setId(id);
		publisher.setName(name);
		publisher.setAddress(address);

		int flag = publisherDAO.update(publisher);
		if (flag > 0)
			resp.sendRedirect("/BookStoreOnline/admin/manage/publisher");
		else {
			req.setAttribute("status", "failed");
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/publisher/edit_publisher.jsp");
			requestDispatcher.forward(req, resp);
		}
	}

}
