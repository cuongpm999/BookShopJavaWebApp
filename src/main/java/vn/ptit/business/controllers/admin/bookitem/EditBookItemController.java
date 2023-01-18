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

@WebServlet(urlPatterns = "/admin/manage/book-item/edit")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
		maxFileSize = 1024 * 1024 * 10, // 10 MB
		maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class EditBookItemController extends HttpServlet {
	private BookItemDAO bookItemDAO;

	@Override
	public void init() throws ServletException {
		bookItemDAO = new BookItemDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String barCode = req.getParameter("barCode");
		req.setAttribute("bookItem", bookItemDAO.findByBarCode(barCode));

		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/admin/bookitem/edit_bookitem.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");

		double price = Double.parseDouble(req.getParameter("price"));
		String barCode = req.getParameter("barCode");
		String img = req.getParameter("img");
		BookItem bookItem = new BookItem();
		if (!req.getParameter("discount").isEmpty()) {
			double discount = Double.parseDouble(req.getParameter("discount"));
			bookItem.setDiscount(discount);
		}

		bookItem.setPrice(price);
		bookItem.setBarCode(barCode);

		CloudinaryConfig cloudinaryConfig = new CloudinaryConfig();
		if (req.getPart("file") == null || req.getPart("file").getSize() == 0) {
			bookItem.setImg(img);
			int flag = bookItemDAO.update(bookItem);

			if (flag > 0)
				resp.sendRedirect("/BookStoreOnline/admin/manage/book-item");
			else {
				req.setAttribute("status", "failed");
				req.setAttribute("bookItem", bookItem);
				RequestDispatcher requestDispatcher = getServletContext()
						.getRequestDispatcher("/admin/bookitem/edit_bookitem.jsp");
				requestDispatcher.forward(req, resp);
			}
		} else {
			Part filePart = req.getPart("file");
			String fileName = filePart.getSubmittedFileName();
			long size = filePart.getSize();
			byte[] bytes = new byte[(int) size];
			InputStream inputStream = filePart.getInputStream();
			inputStream.read(bytes);
			Map uploadResult = cloudinaryConfig.getCloudinary().uploader().upload(bytes,
					ObjectUtils.asMap("resource_type", "auto", "folder", "BookStoreOnline"));
			bookItem.setImg((String) uploadResult.get("public_id") + "." + fileName.split("\\.")[1]);

			int flag = bookItemDAO.update(bookItem);

			if (flag > 0)
				resp.sendRedirect("/BookStoreOnline/admin/manage/book-item");
			else {
				req.setAttribute("status", "failed");
				req.setAttribute("bookItem", bookItem);
				RequestDispatcher requestDispatcher = getServletContext()
						.getRequestDispatcher("/admin/bookitem/edit_bookitem.jsp");
				requestDispatcher.forward(req, resp);
			}
		}
	}

}
