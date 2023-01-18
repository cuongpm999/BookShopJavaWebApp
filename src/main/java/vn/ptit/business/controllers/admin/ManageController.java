package vn.ptit.business.controllers.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ptit.business.bookdao.BookItemDAO;
import vn.ptit.business.bookdao.BookItemDAOImpl;
import vn.ptit.business.bookdao.BookItemStatDAO;
import vn.ptit.business.bookdao.BookItemStatDAOImpl;
import vn.ptit.business.orderdao.OrderDAO;
import vn.ptit.business.orderdao.OrderDAOImpl;
import vn.ptit.business.orderdao.ShipmentDAOImpl;
import vn.ptit.business.persondao.CustomerDAO;
import vn.ptit.business.persondao.CustomerDAOImpl;
import vn.ptit.business.utils.ChartReport;
import vn.ptit.business.utils.RevenuePerMonthUtil;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.book.BookItemStat;
import vn.ptit.model.order.Order;
import vn.ptit.model.order.Shipment;
import vn.ptit.model.person.Customer;

@WebServlet(urlPatterns = "/admin")
public class ManageController extends HttpServlet{
	private OrderDAO orderDAO;
	private CustomerDAO customerDAO;
	private BookItemDAO bookItemDAO;
	private BookItemStatDAO bookItemStatDAO;
	
	@Override
	public void init() throws ServletException {
		orderDAO = new OrderDAOImpl();
		customerDAO = new CustomerDAOImpl();
		bookItemDAO = new BookItemDAOImpl();
		bookItemStatDAO = new BookItemStatDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Customer> customers = customerDAO.findAll();
		List<BookItem> bookItems = bookItemDAO.findAll();
		List<Order> orders = orderDAO.findAll();
		
		int totalCustomer = customers.size();
		int totalBookItem = bookItems.size();
		int totalOrder = orders.size();
		double totalMoney = 0;
		for (Order order : orders) {
			totalMoney+=order.getPayment().getTotalMoney();
		}
		
		req.setAttribute("totalCustomer", totalCustomer);
		req.setAttribute("totalBookItem", totalBookItem);
		req.setAttribute("totalMoney", totalMoney);
		req.setAttribute("totalOrder", totalOrder);
		
		String label[] = new String[6];
		double data[] = new double[6];

		label[0] = RevenuePerMonthUtil.getStringMonth5();
		label[1] = RevenuePerMonthUtil.getStringMonth4();
		label[2] = RevenuePerMonthUtil.getStringMonth3();
		label[3] = RevenuePerMonthUtil.getStringMonth2();
		label[4] = RevenuePerMonthUtil.getStringMonth1();
		label[5] = RevenuePerMonthUtil.getStringMonth();

		data[0] = RevenuePerMonthUtil.getTotalMoneyMonth5(orders);
		data[1] = RevenuePerMonthUtil.getTotalMoneyMonth4(orders);
		data[2] = RevenuePerMonthUtil.getTotalMoneyMonth3(orders);
		data[3] = RevenuePerMonthUtil.getTotalMoneyMonth2(orders);
		data[4] = RevenuePerMonthUtil.getTotalMoneyMonth1(orders);
		data[5] = RevenuePerMonthUtil.getTotalMoneyMonth(orders);

		req.setAttribute("chartReport", new ChartReport(label, data));
		List<BookItemStat> bookItemStats = bookItemStatDAO.statisticBookItem();
		req.setAttribute("itemStats", bookItemStats);

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/manage.jsp");
		requestDispatcher.forward(req, resp);
	}

}
