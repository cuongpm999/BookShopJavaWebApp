package vn.ptit.business.controllers.admin.bookitem;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.cloudinary.utils.ObjectUtils;

import vn.ptit.business.bookdao.AuthorDAO;
import vn.ptit.business.bookdao.AuthorDAOImpl;
import vn.ptit.business.bookdao.BookDAO;
import vn.ptit.business.bookdao.BookDAOImpl;
import vn.ptit.business.bookdao.BookItemDAO;
import vn.ptit.business.bookdao.BookItemDAOImpl;
import vn.ptit.business.bookdao.PublisherDAO;
import vn.ptit.business.bookdao.PublisherDAOImpl;
import vn.ptit.business.configs.CloudinaryConfig;
import vn.ptit.business.utils.RandomString;
import vn.ptit.model.book.Author;
import vn.ptit.model.book.Book;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.book.Publisher;

@WebServlet(urlPatterns = "/admin/manage/book-item/add")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class AddBookItemController extends HttpServlet {
	private BookDAO bookDAO;
	private BookItemDAO bookItemDAO;

	@Override
	public void init() throws ServletException {
		bookDAO = new BookDAOImpl();
		bookItemDAO = new BookItemDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Book> books = bookDAO.findAll();
		req.setAttribute("books", books);

		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/admin/bookitem/add_bookitem.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		double price = Double.parseDouble(req.getParameter("price"));
		BookItem bookItem = new BookItem();
		if (!req.getParameter("discount").isEmpty()) {
			double discount = Double.parseDouble(req.getParameter("discount"));
			bookItem.setDiscount(discount);
		}

		int bookId = Integer.parseInt(req.getParameter("bookId"));
		List<Book> books = bookDAO.findAll();
		
		for (Book book : books) {
			if (book.getId() == bookId) {
				bookItem.setBook(book);
				break;
			}
		}
		bookItem.setPrice(price);

		CloudinaryConfig cloudinaryConfig = new CloudinaryConfig();
		Part filePart = req.getPart("file");
		String fileName = filePart.getSubmittedFileName();
		long size = filePart.getSize();
		byte[] bytes = new byte[(int) size];
		InputStream inputStream = filePart.getInputStream();
		inputStream.read(bytes);
		Map uploadResult = cloudinaryConfig.getCloudinary().uploader().upload(bytes,
				ObjectUtils.asMap("resource_type", "auto", "folder", "BookStoreOnline"));
		bookItem.setImg((String) uploadResult.get("public_id") + "." + fileName.split("\\.")[1]);
		bookItem.setStatus(true);
		RandomString randomString = new RandomString(13, new SecureRandom(), RandomString.digits);
		bookItem.setBarCode(randomString.nextString());

		int flag = bookItemDAO.insert(bookItem);

		if (flag > 0)
			resp.sendRedirect("/BookStoreOnline/admin/manage/book-item");
		else {
			req.setAttribute("status", "failed");
			req.setAttribute("books", books);
			req.setAttribute("bookItem", bookItem);
			RequestDispatcher requestDispatcher = getServletContext()
					.getRequestDispatcher("/admin/bookitem/add_bookitem.jsp");
			requestDispatcher.forward(req, resp);
		}
	}

}
