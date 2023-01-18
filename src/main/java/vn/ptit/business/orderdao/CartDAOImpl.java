package vn.ptit.business.orderdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vn.ptit.business.bookdao.BookItemDAOImpl;
import vn.ptit.business.configs.ConnectionPool;
import vn.ptit.business.utils.DBUtil;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.order.Cart;
import vn.ptit.model.order.LineBookItem;
import vn.ptit.model.order.Shipment;
import vn.ptit.model.person.Customer;

public class CartDAOImpl implements CartDAO {

	@Override
	public void addToCart(BookItem bookItem, Customer customer) {
		Cart cart = findCurrentCartByCustomer(customer);

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql1 = "INSERT INTO cart (DateCreate,TotalAmount,CustomerID) VALUES (?,?,?)";
		String sql2 = "INSERT INTO linebookitem (Quanity,BookItemBarCode,CartID) VALUES (?,?,?)";
		String sql3 = "UPDATE linebookitem SET Quanity = ? WHERE ID = ?";
		String sql4 = "UPDATE cart SET TotalAmount = ? WHERE ID = ?";
		String sql5 = "SELECT * FROM linebookitem WHERE BookItemBarCode = ? AND CartID = ?";

		try {
			if (cart == null) {
				ps = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
				Timestamp date = new Timestamp(new Date().getTime());
				ps.setTimestamp(1, date);
				ps.setDouble(2, 0);
				ps.setInt(3, customer.getId());
				ps.executeUpdate();
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					cart = new Cart();
					cart.setId(rs.getInt(1));
				}
			}

			LineBookItem lineBookItem = null;
			ps = connection.prepareStatement(sql5);
			ps.setString(1, bookItem.getBarCode());
			ps.setInt(2, cart.getId());
			rs = ps.executeQuery();
			if (rs.next()) {
				lineBookItem = new LineBookItem(rs.getInt("ID"), rs.getInt("Quanity"));
			}

			if (lineBookItem == null) {
				ps = connection.prepareStatement(sql2);
				ps.setInt(1, 1);
				ps.setString(2, bookItem.getBarCode());
				ps.setInt(3, cart.getId());
				ps.executeUpdate();

			} else {
				ps = connection.prepareStatement(sql3);
				ps.setInt(1, lineBookItem.getQuantity() + 1);
				ps.setInt(2, lineBookItem.getId());
				ps.executeUpdate();
			}

			ps = connection.prepareStatement(sql4);
			ps.setDouble(1, cart.getTotalAmount() + bookItem.getPrice() * (100 - bookItem.getDiscount()) / 100);
			ps.setInt(2, cart.getId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closePreparedStatement(ps);
			DBUtil.closeResultSet(rs);
			pool.freeConnection(connection);
		}

	}

	@Override
	public Cart findCurrentCartByCustomer(Customer customer) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql1 = "SELECT * FROM cart WHERE cart.ID NOT IN (SELECT CartID FROM book_store_online.order WHERE CustomerID = ?) AND CustomerID = ?";
		String sql2 = "SELECT * FROM linebookitem WHERE CartID = ?";
		try {
			ps = connection.prepareStatement(sql1);
			ps.setInt(1, customer.getId());
			ps.setInt(2, customer.getId());
			rs = ps.executeQuery();
			if (rs.next()) {
				Cart cart = new Cart();
				cart.setId(rs.getInt("ID"));
				cart.setDateCreate(rs.getTimestamp("DateCreate"));
				cart.setTotalAmount(rs.getDouble("TotalAmount"));
				
				ps = connection.prepareStatement(sql2);
				ps.setInt(1, cart.getId());
				rs = ps.executeQuery();

				List<LineBookItem> lineBookItems = new ArrayList<>();
				while (rs.next()) {
					LineBookItem lineBookItem = new LineBookItem(rs.getInt("ID"), rs.getInt("Quanity"));
					lineBookItems.add(lineBookItem);
				}
				cart.setLineBookItems(lineBookItems);
				int cnt = 0;
				for (LineBookItem lineBookItem : lineBookItems) {
					cnt += lineBookItem.getQuantity();
				}
				cart.setTotalQuantity(cnt);
				return cart;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return null;
	}

	@Override
	public void editCart(BookItem bookItem, int quantity, Customer customer) {
		Cart cart = findCurrentCartByCustomer(customer);

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql1 = "UPDATE linebookitem SET Quanity = ? WHERE BookItemBarCode = ? AND CartID = ?";
		String sql2 = "UPDATE cart SET TotalAmount = ? WHERE ID = ?";

		try {

			ps = connection.prepareStatement(sql1);
			ps.setInt(1, quantity);
			ps.setString(2, bookItem.getBarCode());
			ps.setInt(3, cart.getId());
			ps.executeUpdate();

			HashMap<BookItem, Integer> mapLineItem = new HashMap<>();
			for (LineBookItem lineBookItem : cart.getLineBookItems()) {
				BookItem bookItem_ = new BookItemDAOImpl().findByLineItem(lineBookItem);
				if (bookItem_.getBarCode().equalsIgnoreCase(bookItem.getBarCode())) {
					mapLineItem.put(bookItem_, quantity);
				} else
					mapLineItem.put(bookItem_, lineBookItem.getQuantity());
			}

			double totalAmount = 0;
			for (Map.Entry<BookItem, Integer> entry : mapLineItem.entrySet()) {
				totalAmount += entry.getValue()
						* (entry.getKey().getPrice() * (100 - entry.getKey().getDiscount()) / 100);
			}

			ps = connection.prepareStatement(sql2);
			ps.setDouble(1, totalAmount);
			ps.setInt(2, cart.getId());
			ps.executeUpdate();

		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}

	}

	@Override
	public void deleteCart(BookItem bookItem, Customer customer) {
		Cart cart = findCurrentCartByCustomer(customer);

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql1 = "DELETE FROM linebookitem WHERE BookItemBarCode = ? AND CartID = ?";
		String sql2 = "UPDATE cart SET TotalAmount = ? WHERE ID = ?";
		String sql3 = "DELETE FROM cart WHERE ID = ?";

		try {

			ps = connection.prepareStatement(sql1);
			ps.setString(1, bookItem.getBarCode());
			ps.setInt(2, cart.getId());
			ps.executeUpdate();

			cart = findCurrentCartByCustomer(customer);

			if (cart.getLineBookItems().isEmpty()) {
				ps = connection.prepareStatement(sql3);
				ps.setInt(1, cart.getId());
				ps.executeUpdate();
				return;
			}

			HashMap<BookItem, Integer> mapLineItem = new HashMap<>();
			for (LineBookItem lineBookItem : cart.getLineBookItems()) {
				BookItem bookItem_ = new BookItemDAOImpl().findByLineItem(lineBookItem);
				mapLineItem.put(bookItem_, lineBookItem.getQuantity());
			}

			double totalAmount = 0;
			for (Map.Entry<BookItem, Integer> entry : mapLineItem.entrySet()) {
				totalAmount += entry.getValue()
						* (entry.getKey().getPrice() * (100 - entry.getKey().getDiscount()) / 100);
			}

			ps = connection.prepareStatement(sql2);
			ps.setDouble(1, totalAmount);
			ps.setInt(2, cart.getId());
			ps.executeUpdate();

		}

		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}

	}

}
