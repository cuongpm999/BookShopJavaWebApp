package vn.ptit.business.controllers.admin.shipment;

import java.io.IOException;
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
import vn.ptit.model.book.Publisher;

@WebServlet(urlPatterns = "/admin/manage/shipment/delete")
public class DeleteShipmentController extends HttpServlet {
	private ShipmentDAO shipmentDAO;

	@Override
	public void init() throws ServletException {
		shipmentDAO = new ShipmentDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		shipmentDAO.delete(id);
		resp.sendRedirect("/BookStoreOnline/admin/manage/shipment");
	}

}
