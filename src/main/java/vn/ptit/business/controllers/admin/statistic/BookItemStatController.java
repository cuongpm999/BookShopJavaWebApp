package vn.ptit.business.controllers.admin.statistic;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ptit.business.bookdao.BookItemDAO;
import vn.ptit.business.bookdao.BookItemDAOImpl;
import vn.ptit.business.bookdao.BookItemStatDAO;
import vn.ptit.business.bookdao.BookItemStatDAOImpl;
import vn.ptit.business.orderdao.OrderDAOImpl;
import vn.ptit.business.persondao.CustomerDAOImpl;
import vn.ptit.model.book.BookItemStat;

@WebServlet(urlPatterns = "/admin/statistic/book-item")
public class BookItemStatController extends HttpServlet{
	private BookItemStatDAO bookItemStatDAO;
	
	@Override
	public void init() throws ServletException {
		bookItemStatDAO = new BookItemStatDAOImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<BookItemStat> bookItemStats = bookItemStatDAO.statisticBookItem();
		req.setAttribute("bookItemStats", bookItemStats);

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/stat/bookitem_stat.jsp");
		requestDispatcher.forward(req, resp);
	}
}
