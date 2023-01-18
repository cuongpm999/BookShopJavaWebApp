package vn.ptit.business.orderdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.ptit.business.bookdao.BookItemDAOImpl;
import vn.ptit.business.configs.ConnectionPool;
import vn.ptit.business.persondao.CustomerDAOImpl;
import vn.ptit.business.utils.DBUtil;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.order.Cart;
import vn.ptit.model.order.Cash;
import vn.ptit.model.order.Credit;
import vn.ptit.model.order.DigitalWallet;
import vn.ptit.model.order.LineBookItem;
import vn.ptit.model.order.Order;
import vn.ptit.model.order.Payment;
import vn.ptit.model.order.Shipment;
import vn.ptit.model.person.Customer;

public class OrderDAOImpl implements OrderDAO {

	@Override
	public boolean insert(Customer customer, Order order) {
		boolean isSuccess = true;

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql1 = "INSERT INTO book_store_online.order (CustomerID,ShipmentID,CartID,DateCreate,Status) VALUES (?,?,?,?,?)";
		String sql2 = "INSERT INTO payment (ShipmentID,OrderID,CartID,TotalMoney) VALUES (?,?,?,?)";

		String sql5 = "INSERT INTO cash (CashTendered,PaymentID) VALUES (?,?)";
		String sql6 = "INSERT INTO digitalwallet (Name,PaymentID) VALUES (?,?)";
		String sql7 = "INSERT INTO credit (Number,Type,Date,PaymentID) VALUES (?,?,?,?)";

		try {
			connection.setAutoCommit(false);

			ps = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, customer.getId());
			ps.setInt(2, order.getShipment().getId());
			ps.setInt(3, order.getCart().getId());
			Timestamp date = new Timestamp(order.getDateCreate().getTime());
			ps.setTimestamp(4, date);
			ps.setString(5, order.getStatus());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				order.setId(rs.getInt(1));
			}

			ps = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, order.getShipment().getId());
			ps.setInt(2, order.getId());
			ps.setInt(3, order.getCart().getId());
			ps.setDouble(4, order.getPayment().getTotalMoney());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				order.getPayment().setId(rs.getInt(1));
			}

			if (order.getPayment() instanceof Cash) {
				ps = connection.prepareStatement(sql5);
				ps.setDouble(1, ((Cash) order.getPayment()).getCashTendered());
				ps.setInt(2, order.getPayment().getId());
				ps.executeUpdate();

			}

			if (order.getPayment() instanceof Credit) {
				ps = connection.prepareStatement(sql7);
				ps.setString(1, ((Credit) order.getPayment()).getNumber());
				ps.setString(2, ((Credit) order.getPayment()).getType());
				ps.setString(3, ((Credit) order.getPayment()).getDate());
				ps.setInt(4, order.getPayment().getId());
				ps.executeUpdate();

			}

			if (order.getPayment() instanceof DigitalWallet) {
				ps = connection.prepareStatement(sql6);
				ps.setString(1, ((DigitalWallet) order.getPayment()).getName());
				ps.setInt(2, order.getPayment().getId());
				ps.executeUpdate();
			}

		} catch (SQLException e) {
			isSuccess = false;
			try {
				connection.rollback();
				isSuccess = false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.setAutoCommit(true);
				DBUtil.closeResultSet(rs);
				DBUtil.closePreparedStatement(ps);
				pool.freeConnection(connection);
			} catch (SQLException e) {
				DBUtil.closeResultSet(rs);
				DBUtil.closePreparedStatement(ps);
				pool.freeConnection(connection);
				isSuccess = false;
				e.printStackTrace();
			}
		}
		return isSuccess;
	}

	@Override
	public List<Order> getOrderByCustomer(Customer customer) {
		List<Order> res = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql1 = "SELECT book_store_online.order.*,cart.*,payment.* FROM book_store_online.order,cart,payment WHERE book_store_online.order.CustomerID = ? AND book_store_online.order.CartID = cart.ID AND book_store_online.order.ID = payment.OrderID";
		String sql2 = "SELECT * FROM linebookitem WHERE CartID = ?";
		try {
			ps = connection.prepareStatement(sql1);
			ps.setInt(1, customer.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt(1));
				order.setDateCreate(rs.getTimestamp(5));
				order.setStatus(rs.getString("Status"));
				order.setShipment(new ShipmentDAOImpl().findById(rs.getInt(3)));
				Payment payment = new Payment();
				payment.setId(rs.getInt(10));
				payment.setTotalMoney(rs.getDouble("TotalMoney"));
				order.setPayment(payment);
				Cart cart = new Cart();
				cart.setId(rs.getInt(7));
				cart.setDateCreate(rs.getTimestamp(8));
				cart.setTotalAmount(rs.getDouble("TotalAmount"));
				order.setCart(cart);

				ps = connection.prepareStatement(sql2);
				ps.setInt(1, cart.getId());
				rs1 = ps.executeQuery();

				List<LineBookItem> lineBookItems = new ArrayList<>();
				while (rs1.next()) {
					LineBookItem lineBookItem = new LineBookItem(rs1.getInt("ID"), rs1.getInt("Quanity"));
					lineBookItems.add(lineBookItem);
				}
				order.getCart().setLineBookItems(lineBookItems);
				res.add(order);
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeResultSet(rs1);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}

	}

	@Override
	public List<Order> findAll() {
		List<Order> res = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT book_store_online.order.*,cart.*,payment.* FROM book_store_online.order,cart,payment WHERE book_store_online.order.CartID = cart.ID AND book_store_online.order.ID = payment.OrderID ORDER BY book_store_online.order.DateCreate DESC";
		try {
			ps = connection.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt(1));
				order.setDateCreate(rs.getTimestamp(5));
				order.setStatus(rs.getString("Status"));
				order.setShipment(new ShipmentDAOImpl().findById(rs.getInt(3)));
				Payment payment = new Payment();
				payment.setId(rs.getInt(10));
				payment.setTotalMoney(rs.getDouble("TotalMoney"));
				order.setPayment(payment);
				Cart cart = new Cart();
				cart.setId(rs.getInt(7));
				cart.setDateCreate(rs.getTimestamp(8));
				cart.setTotalAmount(rs.getDouble("TotalAmount"));
				order.setCart(cart);

				res.add(order);
			}
			return res;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	@Override
	public Order findById(int id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String sql1 = "SELECT book_store_online.order.*,cart.*,payment.* FROM book_store_online.order,cart,payment WHERE book_store_online.order.ID = ? AND book_store_online.order.CartID = cart.ID AND book_store_online.order.ID = payment.OrderID";
		String sql2 = "SELECT * FROM linebookitem WHERE CartID = ?";
		String sql3 = "SELECT * FROM cash WHERE PaymentID = ?";
		String sql4 = "SELECT * FROM digitalwallet WHERE PaymentID = ?";
		String sql5 = "SELECT * FROM credit WHERE PaymentID = ?";
		try {
			ps = connection.prepareStatement(sql1);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt(1));
				order.setDateCreate(rs.getTimestamp(5));
				order.setStatus(rs.getString("Status"));
				
				order.setShipment(new ShipmentDAOImpl().findById(rs.getInt(3)));
				
				Cart cart = new Cart();
				cart.setId(rs.getInt(7));
				cart.setDateCreate(rs.getTimestamp(8));
				cart.setTotalAmount(rs.getDouble("TotalAmount"));
				order.setCart(cart);

				ps = connection.prepareStatement(sql2);
				ps.setInt(1, cart.getId());
				rs1 = ps.executeQuery();

				List<LineBookItem> lineBookItems = new ArrayList<>();
				while (rs1.next()) {
					LineBookItem lineBookItem = new LineBookItem(rs1.getInt("ID"), rs1.getInt("Quanity"));
					lineBookItems.add(lineBookItem);
				}
				order.getCart().setLineBookItems(lineBookItems);

				ps = connection.prepareStatement(sql3);
				ps.setInt(1, rs.getInt(11));
				rs1 = ps.executeQuery();
				if (rs1.next()) {
					Cash cash = new Cash();
					cash.setId(rs.getInt(11));
					cash.setTotalMoney(rs.getDouble("TotalMoney"));
					cash.setCashTendered(rs1.getDouble("CashTendered"));
					order.setPayment(cash);
				}
				
				ps = connection.prepareStatement(sql4);
				ps.setInt(1, rs.getInt(11));
				rs1 = ps.executeQuery();
				if (rs1.next()) {
					DigitalWallet digitalWallet = new DigitalWallet();
					digitalWallet.setId(rs.getInt(11));
					digitalWallet.setTotalMoney(rs.getDouble("TotalMoney"));
					digitalWallet.setName(rs1.getString("Name"));
					order.setPayment(digitalWallet);
				}
				
				ps = connection.prepareStatement(sql5);
				ps.setInt(1, rs.getInt(11));
				rs1 = ps.executeQuery();
				if (rs1.next()) {
					Credit credit = new Credit();
					credit.setId(rs.getInt(11));
					credit.setTotalMoney(rs.getDouble("TotalMoney"));
					credit.setDate(rs1.getString(3));
					credit.setNumber(rs1.getString(1));
					credit.setType(rs1.getString(2));
					order.setPayment(credit);
				}

				return order;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeResultSet(rs1);
			DBUtil.closePreparedStatement(ps);
			DBUtil.closePreparedStatement(ps1);
			pool.freeConnection(connection);
		}
		return null;
	}

}
