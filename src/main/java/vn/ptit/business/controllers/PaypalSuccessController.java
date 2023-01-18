package vn.ptit.business.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import vn.ptit.business.orderdao.CartDAOImpl;
import vn.ptit.business.orderdao.OrderDAO;
import vn.ptit.business.orderdao.OrderDAOImpl;
import vn.ptit.business.orderdao.ShipmentDAOImpl;
import vn.ptit.business.persondao.CustomerDAO;
import vn.ptit.business.persondao.CustomerDAOImpl;
import vn.ptit.business.utils.PaypalUtil;
import vn.ptit.model.order.Order;
import vn.ptit.model.person.Customer;

@WebServlet(urlPatterns = "/checkout/finish/success")
public class PaypalSuccessController extends HttpServlet{
	private OrderDAO orderDAO;
	private CustomerDAO customerDAO;
	
	@Override
	public void init() throws ServletException {
		orderDAO = new OrderDAOImpl();
		customerDAO = new CustomerDAOImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String paymentId = req.getParameter("paymentId");
		String payerId = req.getParameter("PayerID");
		try {
			Payment payment = new PaypalUtil().executePayment(paymentId, payerId);
			if (payment.getState().equals("approved")) {
				HttpSession httpSession = req.getSession();
				Order order = new Order();
				Customer customer = new Customer();
				if (httpSession.getAttribute("order") != null) {
					order = (Order) httpSession.getAttribute("order");
				}
				if (httpSession.getAttribute("customerLogin") != null) {
					customer = (Customer) httpSession.getAttribute("customerLogin");
				}

				
				customer.setPoint(customer.getPoint() + order.getPayment().getTotalMoney() * 0.009);
				
				boolean flag = orderDAO.insert(customer, order);
				if(flag) {
					req.getSession().setAttribute("customerLogin", customer);
					req.setAttribute("status", "success");
					customerDAO.update(customer);
					httpSession.removeAttribute("cart");
				}
				else {
					req.setAttribute("status", "failed");
				}

				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
				requestDispatcher.forward(req, resp);
			}
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
		}

	}

}
