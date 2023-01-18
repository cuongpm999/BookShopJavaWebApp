package vn.ptit.business.controllers.admin.author;

import java.io.IOException;

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

@WebServlet(urlPatterns = "/admin/manage/author/edit")
public class EditAuthorController extends HttpServlet {
	private AuthorDAO authorDAO;

	@Override
	public void init() throws ServletException {
		authorDAO = new AuthorDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Author author = authorDAO.findById(id);
		req.setAttribute("author", author);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/author/edit_author.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		String biography = req.getParameter("biography");
		int id = Integer.parseInt(req.getParameter("id")); 

		Author author = new Author();
		author.setId(id);
		author.setName(name);
		author.setBiography(biography);

		int flag = authorDAO.update(author);
		if (flag > 0)
			resp.sendRedirect("/BookStoreOnline/admin/manage/author");
		else {
			req.setAttribute("status", "failed");
			req.setAttribute("author", author);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/author/edit_author.jsp");
			requestDispatcher.forward(req, resp);
		}
	}

}
