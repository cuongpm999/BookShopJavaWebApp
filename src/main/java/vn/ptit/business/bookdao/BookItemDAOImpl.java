package vn.ptit.business.bookdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vn.ptit.business.configs.ConnectionPool;
import vn.ptit.business.utils.DBUtil;
import vn.ptit.model.book.Author;
import vn.ptit.model.book.Book;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.book.BookItemStat;
import vn.ptit.model.order.LineBookItem;

public class BookItemDAOImpl implements BookItemDAO {

	@Override
	public int insert(BookItem bookItem) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String sql = "INSERT INTO bookitem (BarCode,Price,Discount,Img,BookID,Status) VALUES (?,?,?,?,?,?)";
		try {
			ps = connection.prepareStatement(sql);
			ps.setString(1, bookItem.getBarCode());
			ps.setDouble(2, bookItem.getPrice());
			ps.setDouble(3, bookItem.getDiscount());
			ps.setString(4, bookItem.getImg());
			ps.setInt(5, bookItem.getBook().getId());
			ps.setBoolean(6, bookItem.isStatus());
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
	public List<BookItem> findAll() {
		List<BookItem> bookItems = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM bookitem WHERE Status = 1";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				BookItem bookItem = new BookItem();
				bookItem.setBarCode(rs.getString("BarCode"));
				bookItem.setPrice(rs.getDouble("Price"));
				bookItem.setDiscount(rs.getDouble("Discount"));
				bookItem.setImg(rs.getString("Img"));
				bookItem.setBook(new BookDAOImpl().findById(rs.getInt("BookID")));

				bookItems.add(bookItem);
			}
			return bookItems;
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
	public int delete(String barCode) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "UPDATE bookitem SET Status = ? WHERE BarCode = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setBoolean(1, false);
			ps.setString(2, barCode);
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
	public int update(BookItem bookItem) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		String query = "UPDATE bookitem SET Price = ?, Discount = ?, Img = ? WHERE BarCode = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setDouble(1, bookItem.getPrice());
			ps.setDouble(2, bookItem.getDiscount());
			ps.setString(3, bookItem.getImg());
			ps.setString(4, bookItem.getBarCode());
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
	public BookItem findByBarCode(String barCode) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT * FROM bookitem WHERE BarCode = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, barCode);
			rs = ps.executeQuery();
			while (rs.next()) {
				BookItem bookItem = new BookItem();
				bookItem.setBarCode(rs.getString("BarCode"));
				bookItem.setPrice(rs.getDouble("Price"));
				bookItem.setDiscount(rs.getDouble("Discount"));
				bookItem.setImg(rs.getString("Img"));
				bookItem.setBook(new BookDAOImpl().findById(rs.getInt("BookID")));
				return bookItem;
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
	public List<BookItem> findByName(String name) {
		List<BookItem> bookItems = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT bookitem.*,book.* FROM bookitem,book WHERE book.Title LIKE ? AND bookitem.BookID = book.ID AND bookitem.Status = 1";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "%" + name + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				BookItem bookItem = new BookItem();
				bookItem.setBarCode(rs.getString("BarCode"));
				bookItem.setPrice(rs.getDouble("Price"));
				bookItem.setDiscount(rs.getDouble("Discount"));
				bookItem.setImg(rs.getString("Img"));
				bookItem.setBook(new BookDAOImpl().findById(rs.getInt("BookID")));

				bookItems.add(bookItem);
			}

			return bookItems;
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
	public List<BookItem> findByCategory(String name) {
		List<BookItem> bookItems = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT bookitem.*,book.* FROM bookitem,book WHERE book.Category LIKE ? AND bookitem.BookID = book.ID AND bookitem.Status = 1 ORDER BY ID DESC";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, "%" + name + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				BookItem bookItem = new BookItem();
				bookItem.setBarCode(rs.getString("BarCode"));
				bookItem.setPrice(rs.getDouble("Price"));
				bookItem.setDiscount(rs.getDouble("Discount"));
				bookItem.setImg(rs.getString("Img"));
				bookItem.setBook(new BookDAOImpl().findById(rs.getInt("BookID")));

				bookItems.add(bookItem);
			}

			return bookItems;
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
	public List<BookItem> findAllWithFilter(Map<String, Object> map) {
		List<BookItem> bookItems = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		Statement s = null;
		ResultSet rs = null;
		String query = "SELECT DISTINCT bookitem.*,bookitem.Price*(100-bookitem.Discount)/100 AS GiaBan FROM bookitem,book,book_author WHERE bookitem.Status = 1 AND book.ID = bookitem.BookID AND book_author.BookID = book.ID";
		for (Map.Entry<String, Object> entry : map.entrySet()) {
			if (entry.getKey().equalsIgnoreCase("author_")) {
				query += " AND book_author.AuthorID = " + entry.getValue().toString();
			} else if (entry.getKey().equalsIgnoreCase("publisher_")) {
				query += " AND book.PublisherID = " + entry.getValue().toString();
			} else if (entry.getKey().equalsIgnoreCase("category")) {
				query += " AND book.category LIKE '%" + entry.getValue().toString() + "%'";
			} else if (entry.getKey().equalsIgnoreCase("key")) {
				query += " AND book.Title LIKE '%" + entry.getValue().toString() + "%'";
			} else if (entry.getKey().equalsIgnoreCase("sort")) {
				if (entry.getValue().toString().equalsIgnoreCase("low-to-high")) {
					query += " ORDER BY GiaBan ASC";
				} else if (entry.getValue().toString().equalsIgnoreCase("high-to-low")) {
					query += " ORDER BY GiaBan DESC";
				} else if (entry.getValue().toString().equalsIgnoreCase("moi-nhat")) {
					query += " ORDER BY ID DESC";
				}

			} else if (entry.getKey().equalsIgnoreCase("state")) {
				if (entry.getValue().toString().equalsIgnoreCase("khuyen-mai")) {
					query += " AND Discount > 0";
				}
			} else if (entry.getKey().equalsIgnoreCase("price")) {
				if (entry.getValue().toString().equalsIgnoreCase("duoi50")) {
					query += " AND bookitem.Price*(100-bookitem.Discount)/100 < 50000";

				} else if (entry.getValue().toString().equalsIgnoreCase("50den100")) {
					query += " AND bookitem.Price*(100-bookitem.Discount)/100 >= 50000 AND bookitem.Price*(100-bookitem.Discount)/100 < 100000";

				} else if (entry.getValue().toString().equalsIgnoreCase("100den200")) {
					query += " AND bookitem.Price*(100-bookitem.Discount)/100 >= 100000 AND bookitem.Price*(100-bookitem.Discount)/100 < 200000";

				} else if (entry.getValue().toString().equalsIgnoreCase("200den300")) {
					query += " AND bookitem.Price*(100-bookitem.Discount)/100 >= 200000 AND bookitem.Price*(100-bookitem.Discount)/100 < 300000";
				} else if (entry.getValue().toString().equalsIgnoreCase("tren300")) {
					query += " AND bookitem.Price*(100-bookitem.Discount)/100 >= 300000";
				}
			}

		}
		try {
			s = connection.createStatement();
			rs = s.executeQuery(query);
			while (rs.next()) {
				BookItem bookItem = new BookItem();
				bookItem.setBarCode(rs.getString("BarCode"));
				bookItem.setPrice(rs.getDouble("Price"));
				bookItem.setDiscount(rs.getDouble("Discount"));
				bookItem.setImg(rs.getString("Img"));
				bookItem.setBook(new BookDAOImpl().findById(rs.getInt("BookID")));

				bookItems.add(bookItem);
			}
			return bookItems;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBUtil.closeResultSet(rs);
			DBUtil.closeStatement(s);
			pool.freeConnection(connection);
		}
	}

	@Override
	public BookItem findByLineItem(LineBookItem lineBookItem) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT bookitem.* FROM bookitem,linebookitem WHERE linebookitem.ID = ? AND linebookitem.BookItemBarCode = bookitem.BarCode";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, lineBookItem.getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				BookItem bookItem = new BookItem();
				bookItem.setBarCode(rs.getString("BarCode"));
				bookItem.setPrice(rs.getDouble("Price"));
				bookItem.setDiscount(rs.getDouble("Discount"));
				bookItem.setImg(rs.getString("Img"));
				bookItem.setBook(new BookDAOImpl().findById(rs.getInt("BookID")));
				return bookItem;
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
