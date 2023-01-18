package vn.ptit.business.controllers.ajax;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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
import vn.ptit.model.order.LineBookItem;
import vn.ptit.model.person.Customer;

@WebServlet(urlPatterns = "/rest/api/cart/editCart")
public class EditCartController extends HttpServlet {
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
		String quantity = data.get("quantity").getAsString();
		BookItem bookItem = bookItemDAO.findByBarCode(barCode);
		cartDAO.editCart(bookItem, Integer.parseInt(quantity), customer);

		Cart cart = cartDAO.findCurrentCartByCustomer(customer);
		HashMap<BookItem, Integer> mapLineItem = new HashMap<>();
		for (LineBookItem lineBookItem : cart.getLineBookItems()) {
			BookItem bookItem_ = bookItemDAO.findByLineItem(lineBookItem);
			mapLineItem.put(bookItem_, lineBookItem.getQuantity());
		}

		double thanhTien = 0;
		for (Map.Entry<BookItem, Integer> entry : mapLineItem.entrySet()) {
			if (entry.getKey().getBarCode().equalsIgnoreCase(barCode)) {
				int quanity = entry.getValue();
				double discount = entry.getKey().getDiscount();
				double donGia = entry.getKey().getPrice();
				thanhTien = (donGia * (100 - discount) / 100) * quanity;
				break;
			}
		}

		req.getSession().setAttribute("cart", cart);

		Locale local = new Locale("vi", "VN");
		NumberFormat numberFormat = NumberFormat.getInstance(local);
		String price = numberFormat.format(thanhTien);
		String totalPrice = numberFormat.format(cart.getTotalAmount());

		List<String> helperMap = new ArrayList<>();
		helperMap.add(cart.getTotalQuantity() + "");
		helperMap.add(price);
		helperMap.add(totalPrice);

		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(gson.toJson(helperMap));
	}
}
