package vn.ptit.business.bookdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.ptit.business.configs.ConnectionPool;
import vn.ptit.business.utils.DBUtil;
import vn.ptit.model.book.BookItemStat;

public class BookItemStatDAOImpl implements BookItemStatDAO{

	@Override
	public List<BookItemStat> statisticBookItem() {
		List<BookItemStat> bookItemStats = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT bookitem.*,A.SoLuongBan FROM bookitem, (SELECT SUM(Quanity) AS SoLuongBan, BookItemBarCode FROM linebookitem GROUP BY BookItemBarCode) AS A WHERE bookitem.BarCode = A.BookItemBarCode ORDER BY A.SoLuongBan DESC LIMIT 9";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				BookItemStat bookItemStat = new BookItemStat();
				bookItemStat.setBarCode(rs.getString("BarCode"));
				bookItemStat.setPrice(rs.getDouble("Price"));
				bookItemStat.setDiscount(rs.getDouble("Discount"));
				bookItemStat.setImg(rs.getString("Img"));
				bookItemStat.setBook(new BookDAOImpl().findById(rs.getInt("BookID")));
				bookItemStat.setQuantityBought(rs.getInt("SoLuongBan"));
				bookItemStats.add(bookItemStat);
			}
			return bookItemStats;
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
