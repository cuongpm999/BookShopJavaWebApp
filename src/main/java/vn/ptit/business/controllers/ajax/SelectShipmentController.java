package vn.ptit.business.controllers.ajax;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import vn.ptit.business.orderdao.ShipmentDAO;
import vn.ptit.business.orderdao.ShipmentDAOImpl;
import vn.ptit.business.utils.PaymentUtil;
import vn.ptit.model.order.Cart;
import vn.ptit.model.order.Shipment;

@WebServlet(urlPatterns = "/rest/api/shipment/select")
public class SelectShipmentController extends HttpServlet {
	private ShipmentDAO shipmentDAO;

	@Override
	public void init() throws ServletException {
		shipmentDAO = new ShipmentDAOImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
		String idShipment = data.get("idShipment").getAsString();
		Shipment shipment = shipmentDAO.findById(Integer.parseInt(idShipment));

		Cart cart = new Cart();
		HttpSession httpSession = req.getSession();
		if (httpSession.getAttribute("cart") != null) {
			cart = (Cart) httpSession.getAttribute("cart");
		}
		
		double allMoney = PaymentUtil.calTotalMoney(cart, shipment);
		Locale local = new Locale("vi", "VN");
		NumberFormat numberFormat = NumberFormat.getInstance(local);

		
		List<String> helperMap = new ArrayList<>();
		helperMap.add(numberFormat.format(shipment.getPrice()));
		helperMap.add(numberFormat.format(allMoney));

		Gson gson = new Gson();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(gson.toJson(helperMap));
	}

}
