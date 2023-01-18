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
import vn.ptit.model.book.Author;
import vn.ptit.model.book.Book;

public class BookDAOImpl implements BookDAO {

	@Override
	public boolean insert(Book book) {
		boolean isSuccess = true;

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql1 = "INSERT INTO book (PublisherID,Title,Category,Summary,Pages,Language,Status) VALUES (?,?,?,?,?,?,?)";
		String sql2 = "INSERT INTO book_author (BookID,AuthorID) VALUES (?,?)";

		try {
			connection.setAutoCommit(false);

			ps = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, book.getPublisher().getId());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getCategory());
			ps.setString(4, book.getSummary());
			ps.setInt(5, book.getPages());
			ps.setString(6, book.getLanguage());
			ps.setBoolean(7, book.isStatus());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				book.setId(rs.getInt(1));
			}

			for (Author author : book.getAuthors()) {
				ps = connection.prepareStatement(sql2);
				ps.setInt(1, book.getId());
				ps.setInt(2, author.getId());
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
	public List<Book> findAll() {
		List<Book> books = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String query = "SELECT * FROM book WHERE Status = 1";
		String query1 = "SELECT AuthorID FROM book_author WHERE BookID = ?";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("ID"));
				book.setTitle(rs.getString("Title"));
				book.setCategory(rs.getString("Category"));
				book.setSummary(rs.getString("Summary"));
				book.setLanguage(rs.getString("Language"));
				book.setPages(rs.getInt("Pages"));
				book.setPublisher(new PublisherDAOImpl().findById(rs.getInt("PublisherID")));

				List<Author> authors = new ArrayList<>();
				ps = connection.prepareStatement(query1);
				ps.setInt(1, book.getId());
				rs1 = ps.executeQuery();
				while (rs1.next()) {
					Author author = new AuthorDAOImpl().findById(rs1.getInt("AuthorID"));
					authors.add(author);
				}
				book.setAuthors(authors);

				books.add(book);
			}
			return books;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeResultSet(rs1);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	@Override
	public boolean delete(int id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "UPDATE book SET Status = ? WHERE ID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setBoolean(1, false);
			ps.setInt(2, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
	}

	@Override
	public boolean update(Book book) {
		boolean isSuccess = true;

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql1 = "UPDATE book SET PublisherID = ?, Title = ?, Category = ?, Summary = ?, Pages = ?, Language = ? WHERE ID = ?";
		String sql2 = "INSERT INTO book_author (BookID,AuthorID) VALUES (?,?)";
		String sql3 = "DELETE FROM book_author WHERE BookID = ?";

		try {
			connection.setAutoCommit(false);
			
			ps = connection.prepareStatement(sql3);
			ps.setInt(1, book.getId());
			ps.executeUpdate();
			
			ps = connection.prepareStatement(sql1);
			ps.setInt(1, book.getPublisher().getId());
			ps.setString(2, book.getTitle());
			ps.setString(3, book.getCategory());
			ps.setString(4, book.getSummary());
			ps.setInt(5, book.getPages());
			ps.setString(6, book.getLanguage());
			ps.setInt(7, book.getId());
			ps.executeUpdate();

			for (Author author : book.getAuthors()) {
				ps = connection.prepareStatement(sql2);
				ps.setInt(1, book.getId());
				ps.setInt(2, author.getId());
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
	public Book findById(int id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		String query = "SELECT * FROM book WHERE ID = ?";
		String query1 = "SELECT AuthorID FROM book_author WHERE BookID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Book book = new Book();
				book.setId(rs.getInt("ID"));
				book.setTitle(rs.getString("Title"));
				book.setCategory(rs.getString("Category"));
				book.setSummary(rs.getString("Summary"));
				book.setLanguage(rs.getString("Language"));
				book.setPages(rs.getInt("Pages"));
				book.setPublisher(new PublisherDAOImpl().findById(rs.getInt("PublisherID")));

				List<Author> authors = new ArrayList<>();
				ps = connection.prepareStatement(query1);
				ps.setInt(1, book.getId());
				rs1 = ps.executeQuery();
				while (rs1.next()) {
					Author author = new AuthorDAOImpl().findById(rs1.getInt("AuthorID"));
					authors.add(author);
				}
				book.setAuthors(authors);
				return book;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeResultSet(rs1);
			DBUtil.closePreparedStatement(ps);
			pool.freeConnection(connection);
		}
		return null;
	}

}
