package vn.ptit.business.orderdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.ptit.business.bookdao.BookDAOImpl;
import vn.ptit.business.configs.ConnectionPool;
import vn.ptit.business.utils.DBUtil;
import vn.ptit.model.book.Author;
import vn.ptit.model.book.BookItemStat;
import vn.ptit.model.order.Shipment;
import vn.ptit.model.order.ShipmentStat;

public class ShipmentDAOImpl implements ShipmentDAO{

	@Override
	public int insert(Shipment shipment) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String sql = "INSERT INTO shipment (Name,Price,Address,Status) VALUES (?,?,?,?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, shipment.getName());
			ps.setDouble(2, shipment.getPrice());
			ps.setString(3, shipment.getAddress());
			ps.setBoolean(4, shipment.isStatus());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	@Override
	public List<Shipment> findAll() {
		List<Shipment> shipments = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM shipment WHERE Status = 1";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Shipment shipment = new Shipment();
				shipment.setId(rs.getInt("ID"));
				shipment.setName(rs.getString("Name"));
				shipment.setPrice(rs.getDouble("Price"));
				shipment.setAddress(rs.getString("Address"));

				shipments.add(shipment);
			}
			return shipments;
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
	public int delete(int id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "UPDATE shipment SET Status = ? WHERE ID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	@Override
	public int update(Shipment shipment) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "UPDATE shipment SET Name = ?, Price = ?, Address = ? WHERE ID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, shipment.getName());
			ps.setDouble(2, shipment.getPrice());
			ps.setString(3, shipment.getAddress());
			ps.setInt(4, shipment.getId());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	@Override
	public Shipment findById(int id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM shipment WHERE ID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Shipment shipment = new Shipment();
				shipment.setId(rs.getInt("ID"));
				shipment.setName(rs.getString("Name"));
				shipment.setPrice(rs.getDouble("Price"));
				shipment.setAddress(rs.getString("Address"));

				return shipment;
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

}
