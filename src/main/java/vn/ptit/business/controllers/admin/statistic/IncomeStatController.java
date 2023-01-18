package vn.ptit.business.controllers.admin.statistic;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vn.ptit.business.orderdao.IncomeStatDAO;
import vn.ptit.business.orderdao.IncomeStatDAOImpl;
import vn.ptit.business.orderdao.ShipmentStatDAOImpl;
import vn.ptit.model.order.IncomeStat;
import vn.ptit.model.order.ShipmentStat;

@WebServlet(urlPatterns = "/admin/statistic/income")
public class IncomeStatController extends HttpServlet {
	private IncomeStatDAO incomeStatDAO;

	@Override
	public void init() throws ServletException {
		incomeStatDAO = new IncomeStatDAOImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/stat/income_stat.jsp");
		requestDispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date fromD = null;
		Date toD = null;
		try {
			fromD = simpleDateFormat.parse(req.getParameter("from"));
			toD = simpleDateFormat.parse(req.getParameter("to"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		IncomeStat incomeStat = incomeStatDAO.statisticIncome(fromD, toD);

		req.setAttribute("incomeStat", incomeStat);
		req.setAttribute("fromD", fromD);
		req.setAttribute("toD", toD);
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/admin/stat/income_stat.jsp");
		requestDispatcher.forward(req, resp);
	}
}
