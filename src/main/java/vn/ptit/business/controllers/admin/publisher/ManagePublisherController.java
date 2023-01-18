package vn.ptit.business.controllers.admin.publisher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ptit.business.bookdao.PublisherDAO;
import vn.ptit.business.bookdao.PublisherDAOImpl;
import vn.ptit.model.book.Publisher;

@WebServlet(urlPatterns = "/admin/manage/publisher")
public class ManagePublisherController extends HttpServlet {

	private PublisherDAO publisherDAO;

	@Override
	public void init() throws ServletException {
		publisherDAO = new PublisherDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Publisher> publishers = publisherDAO.findAll(); 
		
		req.setAttribute("publishers", publishers);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/publisher/manage_publisher.jsp");
		requestDispatcher.forward(req, resp);
	}
}
