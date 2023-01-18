package vn.ptit.business.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/checkout/finish/cancel")
public class PaypalCancelController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("status", "failed");
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
		requestDispatcher.forward(req, resp);
	}
}
