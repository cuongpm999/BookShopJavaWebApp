package vn.ptit.business.controllers.admin.book;

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
import vn.ptit.model.book.Author;
import vn.ptit.model.book.Book;
import vn.ptit.model.book.Publisher;

@WebServlet(urlPatterns = "/admin/manage/book")
public class ManageBookController extends HttpServlet {

	private BookDAO bookDAO;

	@Override
	public void init() throws ServletException {
		bookDAO = new BookDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = bookDAO.findAll(); 
		
		req.setAttribute("books", books);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/book/manage_book.jsp");
		requestDispatcher.forward(req, resp);
	}
}
