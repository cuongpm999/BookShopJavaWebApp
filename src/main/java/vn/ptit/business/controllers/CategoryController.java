package vn.ptit.business.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
import vn.ptit.business.utils.Pagination;
import vn.ptit.model.book.Author;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.book.Publisher;

@WebServlet(urlPatterns = "/collections")
public class CategoryController extends HttpServlet {
	private BookItemDAO bookItemDAO;
	private AuthorDAO authorDAO;
	private PublisherDAO publisherDAO;

	@Override
	public void init() throws ServletException {
		bookItemDAO = new BookItemDAOImpl();
		authorDAO = new AuthorDAOImpl();
		publisherDAO = new PublisherDAOImpl();
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int page = 1;
		List<Author> authors = authorDAO.findAll();
		List<Publisher> publishers = publisherDAO.findAll();
		
		Collections.shuffle(publishers);
		Collections.shuffle(authors);
		
		String price = req.getParameter("price");
		String author_ = req.getParameter("author_");
		String publisher_ = req.getParameter("publisher_");
		String sort = req.getParameter("sort");
		String category = req.getParameter("category");
		String state = req.getParameter("state");
		String key = req.getParameter("key");
		
		Map<String, Object> map = new HashMap<>();
		
		if (category != null) {
			req.setAttribute("category", category);
			map.put("category", category);
		}
		
		if (state != null) {
			req.setAttribute("state", state);
			map.put("state", state);
		}
		
		if (key != null) {
			req.setAttribute("key", key);
			map.put("key", key);
		}
		
		if (author_ != null) {
			req.setAttribute("author_", author_);
			map.put("author_", author_);
		}
		
		if (publisher_ != null) {
			req.setAttribute("publisher_", publisher_);
			map.put("publisher_", publisher_);
		}
		
		if (price != null) {
			req.setAttribute("price", price);
			map.put("price", price);
		}
		
		if (sort != null) {
			req.setAttribute("sort", sort);
			map.put("sort", sort);
		}
		
		List<BookItem> bookItems = bookItemDAO.findAllWithFilter(map);
		
		if (req.getParameter("page") != null) {
			page = Integer.parseInt(req.getParameter("page"));
			req.setAttribute("page", page);
		}
		
		bookItems = Pagination.paging(bookItems, page);

		req.setAttribute("bookItems", bookItems);
		req.setAttribute("authors", authors);
		req.setAttribute("publishers", publishers);
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/category_book.jsp");
		requestDispatcher.forward(req, resp);
	}
}
