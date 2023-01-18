package vn.ptit.business.controllers.ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import vn.ptit.model.person.Customer;

@WebServlet(urlPatterns = "/rest/api/customer/edit-address")
public class EditAddressController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		JsonObject data = new Gson().fromJson(req.getReader(), JsonObject.class);
		String number = data.get("number").getAsString();
		String street = data.get("street").getAsString();
		String district = data.get("district").getAsString();
		String city = data.get("city").getAsString();
		
		Customer customer = new Customer();
		HttpSession httpSession = req.getSession();
		if (httpSession.getAttribute("customerLogin") != null) {
			customer = ((Customer) httpSession.getAttribute("customerLogin"));
		}
		
		customer.getAddress().setNumber(Integer.parseInt(number));
		customer.getAddress().setStreet(street);
		customer.getAddress().setDistrict(district);
		customer.getAddress().setCity(city);
		
		httpSession.setAttribute("customerLogin", customer);
		
		Gson gson = new Gson();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		resp.getWriter().write(gson.toJson(null));


	}

}
