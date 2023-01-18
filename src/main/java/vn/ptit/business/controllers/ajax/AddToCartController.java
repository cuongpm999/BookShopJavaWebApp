package vn.ptit.business.controllers.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import vn.ptit.business.bookdao.BookItemDAO;
import vn.ptit.business.bookdao.BookItemDAOImpl;
import vn.ptit.business.orderdao.CartDAO;
import vn.ptit.business.orderdao.CartDAOImpl;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.order.Cart;
import vn.ptit.model.person.Customer;

@WebServlet(urlPatterns = "/rest/api/cart/addToCart")
public class AddToCartController extends HttpServlet {
	private BookItemDAO bookItemDAO;
	private CartDAO cartDAO;

	@Override
	public void init() throws ServletException {
		bookItemDAO = new BookItemDAOImpl();
		cartDAO = new CartDAOImpl();

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		
		Customer customer = new Customer();
		HttpSession httpSession = req.getSession();
		if (httpSession.getAttribute("customerLogin") != null) {
			customer = (Customer) httpSession.getAttribute("customerLogin");
		} else {
			resp.setContentType("application/json");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().write(gson.toJson(null));
			return;
		}

		JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
		String barCode = data.get("barCode").getAsString();
		BookItem bookItem = bookItemDAO.findByBarCode(barCode);
		cartDAO.addToCart(bookItem, customer);
		
		Cart cart = cartDAO.findCurrentCartByCustomer(customer);
		req.getSession().setAttribute("cart", cart);

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(gson.toJson(cart));

	}

}
