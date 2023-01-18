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
import vn.ptit.business.orderdao.ShipmentDAO;
import vn.ptit.business.orderdao.ShipmentDAOImpl;
import vn.ptit.business.orderdao.ShipmentStatDAO;
import vn.ptit.business.orderdao.ShipmentStatDAOImpl;
import vn.ptit.business.persondao.CustomerDAOImpl;
import vn.ptit.model.order.ShipmentStat;

@WebServlet(urlPatterns = "/admin/statistic/shipment")
public class ShipmentStatController extends HttpServlet{
	private ShipmentStatDAO shipmentStatDAO;
	
	@Override
	public void init() throws ServletException {
		shipmentStatDAO = new ShipmentStatDAOImpl();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<ShipmentStat> shipmentStats = shipmentStatDAO.statisticShipment();
		req.setAttribute("shipmentStats", shipmentStats);

		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/stat/shipment_stat.jsp");
		requestDispatcher.forward(req, resp);
	}
}
