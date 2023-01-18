package vn.ptit.business.persondao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vn.ptit.business.configs.ConnectionPool;
import vn.ptit.business.utils.DBUtil;
import vn.ptit.model.person.Address;
import vn.ptit.model.person.CustomerStat;
import vn.ptit.model.person.FullName;

public class CustomerStatDAOImpl implements CustomerStatDAO{

	@Override
	public List<CustomerStat> statisticCustomer() {
		List<CustomerStat> customerStats = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT person.mobile,person.email,person.sex,fullname.*,address.*, B.TongTienMua FROM (SELECT SUM(A.TotalMoney) AS TongTienMua,A.CustomerID FROM (SELECT book_store_online.order.CustomerID, payment.TotalMoney FROM book_store_online.order, payment WHERE book_store_online.order.ID = payment.OrderID) AS A GROUP BY A.CustomerID) AS B, person,fullname,address WHERE person.ID = B.CustomerID AND person.ID = fullname.PersonID AND person.ID = address.PersonID ORDER BY B.TongTienMua DESC LIMIT 9";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				CustomerStat customerStat = new CustomerStat();
				FullName fullName = new FullName();
				Address address = new Address();
				
				fullName.setFirstName(rs.getString("FirstName"));
				fullName.setLastName(rs.getString("LastName"));
				fullName.setMiddleName(rs.getString("MiddleName"));
				
				address.setNumber(rs.getInt("Number"));
				address.setStreet(rs.getString("Street"));
				address.setDistrict(rs.getString("District"));
				address.setCity(rs.getString("City"));
				
				customerStat.setAddress(address);
				customerStat.setFullName(fullName);
				customerStat.setMobile(rs.getString("Mobile"));
				customerStat.setEmail(rs.getString("Email"));
				customerStat.setSex(rs.getString("Sex"));
				customerStat.setRevenue(rs.getDouble("TongTienMua"));
				
				customerStats.add(customerStat);
				
			}
			return customerStats;
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
