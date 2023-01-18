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

@WebServlet(urlPatterns = "/admin/manage/book/edit")
public class EditBookController extends HttpServlet {
	private BookDAO bookDAO;
	private PublisherDAO publisherDAO;
	private AuthorDAO authorDAO;

	@Override
	public void init() throws ServletException {
		bookDAO = new BookDAOImpl();
		authorDAO = new AuthorDAOImpl();
		publisherDAO = new PublisherDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Author> authors = authorDAO.findAll();
		List<Publisher> publishers = publisherDAO.findAll();
		req.setAttribute("publishers", publishers);	
		
		int id = Integer.parseInt(req.getParameter("id"));
		Book book = bookDAO.findById(id);
		req.setAttribute("book", book);
		
		List<Author> authorsChecked = new ArrayList<Author>();
		List<Author> authorsNotChecked = new ArrayList<Author>();
		for (int i = 0; i < book.getAuthors().size(); i++) {
			authorsChecked.add(book.getAuthors().get(i));
		}
		authorsNotChecked.addAll(authors);
		for (int i = 0; i < book.getAuthors().size(); i++) {
			for (int j = 0; j < authorsNotChecked.size(); j++) {
				if (authorsNotChecked.get(j).getId() == book.getAuthors().get(i).getId()) {
					authorsNotChecked.remove(j);
				}
			}
		}
		req.setAttribute("authorsChecked", authorsChecked);
		req.setAttribute("authorsNotChecked", authorsNotChecked);	

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/book/edit_book.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(req.getParameter("id"));
		String title = req.getParameter("title");
		String summary = req.getParameter("summary");
		String category = req.getParameter("category");
		int pages = Integer.parseInt(req.getParameter("pages"));
		String language = req.getParameter("language");
		String[] authorsId = req.getParameterValues("authors");
		int publisherId = Integer.parseInt(req.getParameter("publisherId"));
		List<Author> authors = authorDAO.findAll();
		List<Publisher> publishers = publisherDAO.findAll();
		List<Author> listAuthor = new ArrayList<>();
		for (int i = 0; i < authors.size(); i++) {
			for (int j = 0; j < authorsId.length; j++) {
				if (authors.get(i).getId() == Integer.parseInt(authorsId[j])) {
					listAuthor.add(authors.get(i));
				}
			}
		}
		Book book = new Book();
		for (int i = 0; i < publishers.size(); i++) {
			if (publishers.get(i).getId() == publisherId) {
				book.setPublisher(publishers.get(i));
				break;
			}
		}
		book.setId(id);
		book.setAuthors(listAuthor);
		book.setLanguage(language);
		book.setPages(pages);
		book.setSummary(summary);
		book.setTitle(title);
		book.setCategory(category);
		boolean flag = bookDAO.update(book);

		if (flag)
			resp.sendRedirect("/BookStoreOnline/admin/manage/book");
		else {
			req.setAttribute("status", "failed");
			req.setAttribute("authors", authors);
			req.setAttribute("publishers", publishers);
			req.setAttribute("book", book);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/book/edit_book.jsp");
			requestDispatcher.forward(req, resp);
		}
	}

}
