package vn.ptit.business.bookdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.ptit.business.configs.ConnectionPool;
import vn.ptit.business.utils.DBUtil;
import vn.ptit.model.book.Publisher;

public class PublisherDAOImpl implements PublisherDAO {

	@Override
	public int insert(Publisher publisher) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String sql = "INSERT INTO publisher (Name,Address,Status) VALUES (?,?,?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, publisher.getName());
			ps.setString(2, publisher.getAddress());
			ps.setBoolean(3, publisher.isStatus());
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
	public List<Publisher> findAll() {
		List<Publisher> publishers = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM publisher WHERE Status = 1";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Publisher publisher = new Publisher();
				publisher.setId(rs.getInt("ID"));
				publisher.setName(rs.getString("Name"));
				publisher.setAddress(rs.getString("Address"));

				publishers.add(publisher);
			}
			return publishers;
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
		String query = "UPDATE publisher SET Status = ? WHERE ID = ?";
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
	public int update(Publisher publisher) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "UPDATE publisher SET Name = ?, Address = ? WHERE ID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, publisher.getName());
			ps.setString(2, publisher.getAddress());
			ps.setInt(3, publisher.getId());
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
	public Publisher findById(int id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM publisher WHERE ID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Publisher publisher = new Publisher();
				publisher.setId(rs.getInt("ID"));
				publisher.setName(rs.getString("Name"));
				publisher.setAddress(rs.getString("Address"));
				return publisher;
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
