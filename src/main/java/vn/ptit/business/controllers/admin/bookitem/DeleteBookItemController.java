package vn.ptit.business.controllers.admin.bookitem;

import java.io.IOException;
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
import vn.ptit.model.book.Publisher;

@WebServlet(urlPatterns = "/admin/manage/book-item/delete")
public class DeleteBookItemController extends HttpServlet {
	private BookItemDAO bookItemDAO;

	@Override
	public void init() throws ServletException {
		bookItemDAO = new BookItemDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String barCode = req.getParameter("barCode");
		bookItemDAO.delete(barCode);
		resp.sendRedirect("/BookStoreOnline/admin/manage/book-item");
	}

}
