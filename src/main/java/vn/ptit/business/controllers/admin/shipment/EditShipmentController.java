package vn.ptit.business.controllers.admin.shipment;

import java.io.IOException;

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

@WebServlet(urlPatterns = "/admin/manage/shipment/edit")
public class EditShipmentController extends HttpServlet {
	private ShipmentDAO shipmentDAO;

	@Override
	public void init() throws ServletException {
		shipmentDAO = new ShipmentDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		Shipment shipment = shipmentDAO.findById(id);
		req.setAttribute("shipment", shipment);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/shipment/edit_shipment.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String name = req.getParameter("name");
		String address = req.getParameter("address");
		double price = Double.parseDouble(req.getParameter("price"));
		int id = Integer.parseInt(req.getParameter("id")); 

		Shipment shipment = new Shipment();
		shipment.setName(name);
		shipment.setAddress(address);
		shipment.setPrice(price);
		shipment.setId(id);

		int flag = shipmentDAO.update(shipment);
		if (flag > 0)
			resp.sendRedirect("/BookStoreOnline/admin/manage/shipment");
		else {
			req.setAttribute("status", "failed");
			req.setAttribute("shipment", shipment);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/shipment/edit_shipment.jsp");
			requestDispatcher.forward(req, resp);
		}
	}

}
