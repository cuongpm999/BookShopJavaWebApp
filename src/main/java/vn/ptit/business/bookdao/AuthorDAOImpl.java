package vn.ptit.business.bookdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.ptit.business.configs.ConnectionPool;
import vn.ptit.business.utils.DBUtil;
import vn.ptit.model.book.Author;
import vn.ptit.model.book.Publisher;

public class AuthorDAOImpl implements AuthorDAO{

	@Override
	public int insert(Author author) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String sql = "INSERT INTO author (Name,Biography,Status) VALUES (?,?,?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, author.getName());
			ps.setString(2, author.getBiography());
			ps.setBoolean(3, author.isStatus());
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
	public List<Author> findAll() {
		List<Author> authors = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM author WHERE Status = 1";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Author author = new Author();
				author.setId(rs.getInt("ID"));
				author.setName(rs.getString("Name"));
				author.setBiography(rs.getString("Biography"));

				authors.add(author);
			}
			return authors;
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
		String query = "UPDATE author SET Status = ? WHERE ID = ?";
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
	public int update(Author author) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "UPDATE author SET Name = ?, Biography = ? WHERE ID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, author.getName());
			ps.setString(2, author.getBiography());
			ps.setInt(3, author.getId());
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
	public Author findById(int id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM author WHERE ID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Author author = new Author();
				author.setId(rs.getInt("ID"));
				author.setName(rs.getString("Name"));
				author.setBiography(rs.getString("Biography"));
				return author;
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
