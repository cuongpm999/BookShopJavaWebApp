package vn.ptit.business.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ptit.business.bookdao.AuthorDAO;
import vn.ptit.business.bookdao.AuthorDAOImpl;
import vn.ptit.business.bookdao.BookItemDAO;
import vn.ptit.business.bookdao.BookItemDAOImpl;
import vn.ptit.business.bookdao.PublisherDAO;
import vn.ptit.business.bookdao.PublisherDAOImpl;
import vn.ptit.model.book.BookItem;

@WebServlet(urlPatterns = "/book/detail")
public class DetailController extends HttpServlet{
	private BookItemDAO bookItemDAO;

	@Override
	public void init() throws ServletException {
		bookItemDAO = new BookItemDAOImpl();
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String barCode = req.getParameter("barCode");
		BookItem bookItem = bookItemDAO.findByBarCode(barCode);
		
		List<BookItem> bookItemSames = bookItemDAO.findByCategory(bookItem.getBook().getCategory());
		for (BookItem bookItemSame : bookItemSames) {
			if(bookItemSame.getBarCode().equalsIgnoreCase(bookItem.getBarCode())) {
				bookItemSames.remove(bookItemSame);
				break;
			}
		}
		Collections.shuffle(bookItemSames);
		req.setAttribute("bookItemSames", bookItemSames);
		
		req.setAttribute("bookItem", bookItem);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/book_detail.jsp");
		requestDispatcher.forward(req, resp);
	}
}
