package vn.ptit.business.orderdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.ptit.business.configs.ConnectionPool;
import vn.ptit.business.utils.DBUtil;
import vn.ptit.model.order.ShipmentStat;

public class ShipmentStatDAOImpl implements ShipmentStatDAO{

	@Override
	public List<ShipmentStat> statisticShipment() {
		List<ShipmentStat> shipmentStats = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT shipment.*,A.SoLuong FROM shipment,(SELECT ShipmentID, COUNT(ID) AS SoLuong FROM book_store_online.order GROUP BY ShipmentID) AS A WHERE A.ShipmentID = shipment.ID ORDER BY A.SoLuong DESC";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				ShipmentStat shipmentStat = new ShipmentStat();
				shipmentStat.setId(rs.getInt("ID"));
				shipmentStat.setName(rs.getString("Name"));
				shipmentStat.setPrice(rs.getDouble("Price"));
				shipmentStat.setAddress(rs.getString("Address"));
				shipmentStat.setQuantityShipped(rs.getInt("SoLuong"));
				
				shipmentStats.add(shipmentStat);
			}
			return shipmentStats;
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
