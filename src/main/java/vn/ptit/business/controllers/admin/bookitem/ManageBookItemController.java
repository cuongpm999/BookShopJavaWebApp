package vn.ptit.business.controllers.admin.bookitem;

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
import vn.ptit.business.bookdao.BookItemDAO;
import vn.ptit.business.bookdao.BookItemDAOImpl;
import vn.ptit.business.bookdao.PublisherDAO;
import vn.ptit.business.bookdao.PublisherDAOImpl;
import vn.ptit.model.book.Author;
import vn.ptit.model.book.Book;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.book.Publisher;

@WebServlet(urlPatterns = "/admin/manage/book-item")
public class ManageBookItemController extends HttpServlet {

	private BookItemDAO bookItemDAO;

	@Override
	public void init() throws ServletException {
		bookItemDAO = new BookItemDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BookItem> bookItems = bookItemDAO.findAll(); 
		
		req.setAttribute("bookItems", bookItems);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/bookitem/manage_bookitem.jsp");
		requestDispatcher.forward(req, resp);
	}
}
