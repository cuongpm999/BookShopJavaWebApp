package vn.ptit.business.controllers.admin.author;

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
import vn.ptit.business.bookdao.PublisherDAO;
import vn.ptit.business.bookdao.PublisherDAOImpl;
import vn.ptit.model.book.Author;
import vn.ptit.model.book.Publisher;

@WebServlet(urlPatterns = "/admin/manage/author")
public class ManageAuthorController extends HttpServlet {

	private AuthorDAO authorDAO;

	@Override
	public void init() throws ServletException {
		authorDAO = new AuthorDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Author> authors = authorDAO.findAll(); 
		
		req.setAttribute("authors", authors);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/author/manage_author.jsp");
		requestDispatcher.forward(req, resp);
	}
}
