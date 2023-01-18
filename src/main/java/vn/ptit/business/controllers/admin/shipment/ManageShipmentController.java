package vn.ptit.business.controllers.admin.shipment;

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
import vn.ptit.business.bookdao.PublisherDAO;
import vn.ptit.business.bookdao.PublisherDAOImpl;
import vn.ptit.business.orderdao.ShipmentDAO;
import vn.ptit.business.orderdao.ShipmentDAOImpl;
import vn.ptit.model.book.Author;
import vn.ptit.model.book.Publisher;
import vn.ptit.model.order.Shipment;

@WebServlet(urlPatterns = "/admin/manage/shipment")
public class ManageShipmentController extends HttpServlet {

	private ShipmentDAO shipmentDAO;

	@Override
	public void init() throws ServletException {
		shipmentDAO = new ShipmentDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Shipment> shipments = shipmentDAO.findAll(); 
		
		req.setAttribute("shipments", shipments);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/shipment/manage_shipment.jsp");
		requestDispatcher.forward(req, resp);
	}
}
