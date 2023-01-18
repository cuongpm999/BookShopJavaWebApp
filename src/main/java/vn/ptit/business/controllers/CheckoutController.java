package vn.ptit.business.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import vn.ptit.business.bookdao.BookItemDAO;
import vn.ptit.business.bookdao.BookItemDAOImpl;
import vn.ptit.business.orderdao.CartDAO;
import vn.ptit.business.orderdao.CartDAOImpl;
import vn.ptit.business.orderdao.OrderDAO;
import vn.ptit.business.orderdao.OrderDAOImpl;
import vn.ptit.business.orderdao.ShipmentDAO;
import vn.ptit.business.orderdao.ShipmentDAOImpl;
import vn.ptit.business.persondao.CustomerDAO;
import vn.ptit.business.persondao.CustomerDAOImpl;
import vn.ptit.business.utils.PaymentUtil;
import vn.ptit.business.utils.PaypalUtil;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.order.Cart;
import vn.ptit.model.order.Cash;
import vn.ptit.model.order.Credit;
import vn.ptit.model.order.DigitalWallet;
import vn.ptit.model.order.LineBookItem;
import vn.ptit.model.order.Order;
import vn.ptit.model.order.Shipment;
import vn.ptit.model.person.Customer;

@WebServlet(urlPatterns = "/checkout")
public class CheckoutController extends HttpServlet {
	private ShipmentDAO shipmentDAO;
	private OrderDAO orderDAO;
	private CustomerDAO customerDAO;
	private CartDAO cartDAO;
	private BookItemDAO bookItemDAO;

	@Override
	public void init() throws ServletException {
		shipmentDAO = new ShipmentDAOImpl();
		orderDAO = new OrderDAOImpl();
		customerDAO = new CustomerDAOImpl();
		cartDAO = new CartDAOImpl();
		bookItemDAO = new BookItemDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Customer customer = new Customer();
		HttpSession httpSession = req.getSession();
		if (httpSession.getAttribute("customerLogin") != null) {
			customer = (Customer) httpSession.getAttribute("customerLogin");
		}
		Cart cart = cartDAO.findCurrentCartByCustomer(customer);
		if (cart != null) {
			HashMap<BookItem, Integer> mapLineItem = new HashMap<>();
			for (LineBookItem lineBookItem : cart.getLineBookItems()) {
				BookItem bookItem = bookItemDAO.findByLineItem(lineBookItem);
				mapLineItem.put(bookItem, lineBookItem.getQuantity());
			}

			req.setAttribute("mapLineItem", mapLineItem);
		}

		List<Shipment> shipments = shipmentDAO.findAll();
		req.setAttribute("shipments", shipments);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/checkout.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		int shipmentId = Integer.parseInt(req.getParameter("shipmentId"));
		String paymentType = req.getParameter("paymentType");
		String ccname = req.getParameter("cc-name");
		String ccnumber = req.getParameter("cc-number");
		String ccexpiration = req.getParameter("cc-expiration");
		String paymentWith = req.getParameter("paymentWith");

		Cart cart = new Cart();
		Customer customer = new Customer();

		HttpSession httpSession = req.getSession();
		if (httpSession.getAttribute("cart") != null) {
			cart = (Cart) httpSession.getAttribute("cart");
		}
		if (httpSession.getAttribute("customerLogin") != null) {
			customer = (Customer) httpSession.getAttribute("customerLogin");
		}

		Order order = new Order();
		order.setDateCreate(new Date());
		order.setCart(cart);
		Shipment shipment = shipmentDAO.findById(shipmentId);
		order.setShipment(shipment);
		order.setStatus("Đã giao hàng");

		if (paymentWith.equalsIgnoreCase("Cash")) {
			double allMoney = PaymentUtil.calTotalMoney(cart, shipment);
			Cash cash = new Cash(allMoney, allMoney);
			order.setPayment(cash);
		}

		if (paymentWith.equalsIgnoreCase("Credit")) {
			if (ccname.isEmpty() || ccnumber.isEmpty() || ccexpiration.isEmpty()) {
				if (ccname.isEmpty()) {
					req.setAttribute("status", "ccnameNull");
					
					if (httpSession.getAttribute("customerLogin") != null) {
						customer = (Customer) httpSession.getAttribute("customerLogin");
					}
					Cart cart_ = cartDAO.findCurrentCartByCustomer(customer);
					if (cart != null) {
						HashMap<BookItem, Integer> mapLineItem = new HashMap<>();
						for (LineBookItem lineBookItem : cart.getLineBookItems()) {
							BookItem bookItem = bookItemDAO.findByLineItem(lineBookItem);
							mapLineItem.put(bookItem, lineBookItem.getQuantity());
						}

						req.setAttribute("mapLineItem", mapLineItem);
					}

					List<Shipment> shipments = shipmentDAO.findAll();
					req.setAttribute("shipments", shipments);
					
					RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/checkout.jsp");
					requestDispatcher.forward(req, resp);
					return;
				}
				if (ccnumber.isEmpty()) {
					req.setAttribute("status", "ccnumberNull");
					
					if (httpSession.getAttribute("customerLogin") != null) {
						customer = (Customer) httpSession.getAttribute("customerLogin");
					}
					Cart cart_ = cartDAO.findCurrentCartByCustomer(customer);
					if (cart != null) {
						HashMap<BookItem, Integer> mapLineItem = new HashMap<>();
						for (LineBookItem lineBookItem : cart.getLineBookItems()) {
							BookItem bookItem = bookItemDAO.findByLineItem(lineBookItem);
							mapLineItem.put(bookItem, lineBookItem.getQuantity());
						}

						req.setAttribute("mapLineItem", mapLineItem);
					}


					List<Shipment> shipments = shipmentDAO.findAll();
					req.setAttribute("shipments", shipments);
					
					RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/checkout.jsp");
					requestDispatcher.forward(req, resp);
					return;
				}
				if (ccexpiration.isEmpty()) {
					req.setAttribute("status", "ccexpirationNull");
					
					if (httpSession.getAttribute("customerLogin") != null) {
						customer = (Customer) httpSession.getAttribute("customerLogin");
					}
					Cart cart_ = cartDAO.findCurrentCartByCustomer(customer);
					if (cart != null) {
						HashMap<BookItem, Integer> mapLineItem = new HashMap<>();
						for (LineBookItem lineBookItem : cart.getLineBookItems()) {
							BookItem bookItem = bookItemDAO.findByLineItem(lineBookItem);
							mapLineItem.put(bookItem, lineBookItem.getQuantity());
						}

						req.setAttribute("mapLineItem", mapLineItem);
					}


					List<Shipment> shipments = shipmentDAO.findAll();
					req.setAttribute("shipments", shipments);
					
					
					RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/checkout.jsp");
					requestDispatcher.forward(req, resp);
					return;
				}
			}
			double allMoney = PaymentUtil.calTotalMoney(cart, shipment);
			Credit credit = new Credit(allMoney, ccnumber, ccname, ccexpiration);
			order.setPayment(credit);
		}

		if (paymentWith.equalsIgnoreCase("DigitalWallet")) {
			double allMoney = PaymentUtil.calTotalMoney(cart, shipment);
			DigitalWallet digitalWallet = new DigitalWallet(allMoney, paymentType);
			order.setPayment(digitalWallet);

			try {
				Payment payment = new PaypalUtil().createPayment(order.getPayment().getTotalMoney() * getTyGia(), "USD",
						"paypal", "sale", "test", "http://localhost:8080/BookStoreOnline/checkout/finish/cancel",
						"http://localhost:8080/BookStoreOnline/checkout/finish/success");
				httpSession.setAttribute("order", order);
				for (Links link : payment.getLinks()) {
					if (link.getRel().equals("approval_url")) {
						resp.sendRedirect(link.getHref());
					}
				}

			} catch (PayPalRESTException e) {

				e.printStackTrace();
			}
			return;

		}

		customer.setPoint(customer.getPoint() + order.getPayment().getTotalMoney() * 0.009);

		boolean flag = orderDAO.insert(customer, order);
		if (flag) {
			req.getSession().setAttribute("customerLogin", customer);
			req.setAttribute("status", "success");
			customerDAO.update(customer);
			httpSession.removeAttribute("cart");
		} else {
			req.setAttribute("status", "failed");
		}

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
		requestDispatcher.forward(req, resp);
	}

	public double getTyGia() {
		StringBuilder content = new StringBuilder();
		try {
			URL url = new URL(
					"https://free.currconv.com/api/v7/convert?apiKey=73186a7f4f40e1abc58c&q=VND_USD&compact=y");
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(url.openConnection().getInputStream()));
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line);
			}
			bufferedReader.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JsonObject jsonObject = new Gson().fromJson(content.toString(), JsonObject.class);
		JsonObject object1 = jsonObject.get("VND_USD").getAsJsonObject();
		double tyGia = object1.get("val").getAsDouble();
		return tyGia;
	}

}
