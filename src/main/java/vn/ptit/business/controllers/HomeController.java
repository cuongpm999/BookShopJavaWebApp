package vn.ptit.business.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ptit.business.bookdao.AuthorDAOImpl;
import vn.ptit.business.bookdao.BookDAOImpl;
import vn.ptit.business.bookdao.BookItemDAO;
import vn.ptit.business.bookdao.BookItemDAOImpl;
import vn.ptit.business.bookdao.PublisherDAOImpl;
import vn.ptit.model.book.BookItem;

@WebServlet(urlPatterns = "/")
public class HomeController extends HttpServlet {
	private BookItemDAO bookItemDAO;

	@Override
	public void init() throws ServletException {
		bookItemDAO = new BookItemDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BookItem> bookItems1 = bookItemDAO.findByCategory("Lịch sử truyền thống");
		req.setAttribute("bookItems1", bookItems1);
		
		List<BookItem> bookItems2 = bookItemDAO.findByCategory("Kiến thức khoa học");
		req.setAttribute("bookItems2", bookItems2);
		
		List<BookItem> bookItems3 = bookItemDAO.findByCategory("Văn học Việt Nam");
		req.setAttribute("bookItems3", bookItems3);
		
		List<BookItem> bookItems4 = bookItemDAO.findByCategory("Văn học nước ngoài");
		req.setAttribute("bookItems4", bookItems4);
		
		List<BookItem> bookItems5 = bookItemDAO.findByCategory("Truyện tranh");
		req.setAttribute("bookItems5", bookItems5);
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/home.jsp");
		requestDispatcher.forward(req, resp);
	}

}
