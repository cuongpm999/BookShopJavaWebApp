package vn.ptit.business.orderdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.ptit.business.configs.ConnectionPool;
import vn.ptit.business.utils.DBUtil;
import vn.ptit.model.order.IncomeStat;
import vn.ptit.model.order.ShipmentStat;

public class IncomeStatDAOImpl implements IncomeStatDAO {

	@Override
	public IncomeStat statisticIncome(Date from, Date to) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql1 = "SELECT SUM(payment.TotalMoney) AS Revenue FROM (SELECT * FROM book_store_online.order WHERE DateCreate >= ? AND DateCreate <= ?) AS A, payment WHERE A.ID = payment.OrderID";
		String sql2 = "SELECT COUNT(A.CustomerID) AS CNTCustomer FROM (SELECT DISTINCT book_store_online.order.CustomerID FROM book_store_online.order WHERE DateCreate >= ? AND DateCreate <= ?) AS A";
		try {
			IncomeStat incomeStat = new IncomeStat();

			ps = connection.prepareStatement(sql1);
			ps.setDate(1, new java.sql.Date(from.getTime()));
			ps.setDate(2, new java.sql.Date(to.getTime()));
			rs = ps.executeQuery();
			if (rs.next()) {
				incomeStat.setRevenue(rs.getDouble("Revenue"));
			}

			ps = connection.prepareStatement(sql2);
			ps.setDate(1, new java.sql.Date(from.getTime()));
			ps.setDate(2, new java.sql.Date(to.getTime()));
			rs = ps.executeQuery();
			if (rs.next()) {
				incomeStat.setCustomer(rs.getInt("CNTCustomer"));
			}
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			incomeStat.setPeriod(simpleDateFormat.format(from) + " - " + simpleDateFormat.format(to));
			return incomeStat;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}

	}

}
