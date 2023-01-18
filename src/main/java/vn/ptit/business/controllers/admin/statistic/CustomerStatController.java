package vn.ptit.business.controllers.admin.statistic;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ptit.business.bookdao.BookItemDAO;
import vn.ptit.business.bookdao.BookItemDAOImpl;
import vn.ptit.business.orderdao.OrderDAOImpl;
import vn.ptit.business.persondao.CustomerDAO;
import vn.ptit.business.persondao.CustomerDAOImpl;
import vn.ptit.business.persondao.CustomerStatDAO;
import vn.ptit.business.persondao.CustomerStatDAOImpl;
import vn.ptit.model.book.BookItemStat;
import vn.ptit.model.person.CustomerStat;

@WebServlet(urlPatterns = "/admin/statistic/customer")
public class CustomerStatController extends HttpServlet{
	private CustomerStatDAO customerStatDAO;
	
	@Override
	public void init() throws ServletException {
		customerStatDAO = new CustomerStatDAOImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<CustomerStat> customerStats = customerStatDAO.statisticCustomer();
		req.setAttribute("customerStats", customerStats);

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/stat/customer_stat.jsp");
		requestDispatcher.forward(req, resp);
	}
}
