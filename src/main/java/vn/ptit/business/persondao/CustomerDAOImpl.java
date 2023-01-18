package vn.ptit.business.persondao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.ptit.business.bookdao.BookDAOImpl;
import vn.ptit.business.configs.ConnectionPool;
import vn.ptit.business.utils.DBUtil;
import vn.ptit.model.book.Author;
import vn.ptit.model.book.BookItem;
import vn.ptit.model.book.BookItemStat;
import vn.ptit.model.order.Order;
import vn.ptit.model.person.Account;
import vn.ptit.model.person.Address;
import vn.ptit.model.person.Customer;
import vn.ptit.model.person.CustomerStat;
import vn.ptit.model.person.FullName;

public class CustomerDAOImpl implements CustomerDAO {

	@Override
	public boolean insert(Customer customer) {
		boolean isSuccess = true;

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql1 = "INSERT INTO person (Mobile,Sex,DateOfBirth,Email,Status) VALUES (?,?,?,?,?)";
		String sql2 = "INSERT INTO account (PersonID,Username,Password) VALUES (?,?,?)";
		String sql3 = "INSERT INTO address (PersonID,Number,Street,District,City) VALUES (?,?,?,?,?)";
		String sql4 = "INSERT INTO fullname (PersonID,FirstName,LastName,MiddleName) VALUES (?,?,?,?)";
		String sql5 = "INSERT INTO customer (Point,Note,PersonID) VALUES (?,?,?)";

		try {
			connection.setAutoCommit(false);

			ps = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, customer.getMobile());
			ps.setString(2, customer.getSex());
			ps.setDate(3, new java.sql.Date(customer.getDateOfBirth().getTime()));
			ps.setString(4, customer.getEmail());
			ps.setBoolean(5, customer.isStatus());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				customer.setId(rs.getInt(1));
			}

			ps = connection.prepareStatement(sql2);
			ps.setInt(1, customer.getId());
			ps.setString(2, customer.getAccount().getUsername());
			ps.setString(3, customer.getAccount().getPassword());
			ps.executeUpdate();
			
			ps = connection.prepareStatement(sql3);
			ps.setInt(1, customer.getId());
			ps.setInt(2, customer.getAddress().getNumber());
			ps.setString(3, customer.getAddress().getStreet());
			ps.setString(4, customer.getAddress().getDistrict());
			ps.setString(5, customer.getAddress().getCity());
			ps.executeUpdate();
			
			ps = connection.prepareStatement(sql4);
			ps.setInt(1, customer.getId());
			ps.setString(2, customer.getFullName().getFirstName());
			ps.setString(3, customer.getFullName().getLastName());
			ps.setString(4, customer.getFullName().getMiddleName());
			ps.executeUpdate();
			
			ps = connection.prepareStatement(sql5);
			ps.setDouble(1, customer.getPoint());
			ps.setString(2, customer.getNote());
			ps.setInt(3, customer.getId());
			ps.executeUpdate();

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
	public List<Customer> findAll() {
		List<Customer> customers = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT person.*,customer.*,account.*,address.*,fullname.* FROM person,customer,account,address,fullname WHERE Status = 1 AND person.ID = customer.PersonID AND person.ID = account.PersonID AND person.ID = address.PersonID AND person.ID = fullname.PersonID";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer();
				FullName fullName = new FullName();
				Address address = new Address();
				Account account = new Account();
				
				fullName.setFirstName(rs.getString("FirstName"));
				fullName.setLastName(rs.getString("LastName"));
				fullName.setMiddleName(rs.getString("MiddleName"));
				
				address.setNumber(rs.getInt("Number"));
				address.setStreet(rs.getString("Street"));
				address.setDistrict(rs.getString("District"));
				address.setCity(rs.getString("City"));
				
				account.setUsername(rs.getString("Username"));
				account.setPassword(rs.getString("Password"));
				
				customer.setAccount(account);
				customer.setAddress(address);
				customer.setFullName(fullName);
				
				customer.setMobile(rs.getString("Mobile"));
				customer.setSex(rs.getString("Sex"));
				customer.setEmail(rs.getString("Email"));
				customer.setDateOfBirth(rs.getDate("DateOfBirth"));
				customer.setId(rs.getInt("ID"));
				customer.setNote(rs.getString("Note"));
				customer.setPoint(rs.getDouble("Point"));
				
				customers.add(customer);
			}
			return customers;
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
		String query = "UPDATE person SET Status = ? WHERE ID = ?";
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
	public boolean update(Customer customer) {
		boolean isSuccess = true;

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql1 = "UPDATE person SET Mobile = ?,Sex = ?,DateOfBirth = ?,Email = ? WHERE ID = ?";
		String sql2 = "UPDATE account SET Username = ?,Password = ? WHERE PersonID = ?";
		String sql3 = "UPDATE address SET Number = ?,Street = ?,District = ?,City = ? WHERE PersonID = ?";
		String sql4 = "UPDATE fullname SET FirstName = ?,LastName = ?,MiddleName = ? WHERE PersonID = ?";
		String sql5 = "UPDATE customer SET Point = ?,Note = ? WHERE PersonID = ?";

		try {
			connection.setAutoCommit(false);

			ps = connection.prepareStatement(sql1);
			ps.setString(1, customer.getMobile());
			ps.setString(2, customer.getSex());
			ps.setDate(3, new java.sql.Date(customer.getDateOfBirth().getTime()));
			ps.setString(4, customer.getEmail());
			ps.setInt(5, customer.getId());
			ps.executeUpdate();

			ps = connection.prepareStatement(sql2);
			ps.setString(1, customer.getAccount().getUsername());
			ps.setString(2, customer.getAccount().getPassword());
			ps.setInt(3, customer.getId());
			ps.executeUpdate();
			
			ps = connection.prepareStatement(sql3);
			ps.setInt(1, customer.getAddress().getNumber());
			ps.setString(2, customer.getAddress().getStreet());
			ps.setString(3, customer.getAddress().getDistrict());
			ps.setString(4, customer.getAddress().getCity());
			ps.setInt(5, customer.getId());
			ps.executeUpdate();
			
			ps = connection.prepareStatement(sql4);
			ps.setString(1, customer.getFullName().getFirstName());
			ps.setString(2, customer.getFullName().getLastName());
			ps.setString(3, customer.getFullName().getMiddleName());
			ps.setInt(4, customer.getId());
			ps.executeUpdate();
			
			ps = connection.prepareStatement(sql5);
			ps.setDouble(1, customer.getPoint());
			ps.setString(2, customer.getNote());
			ps.setInt(3, customer.getId());
			ps.executeUpdate();

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
	public Customer findById(int id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT person.*,customer.*,account.*,address.*,fullname.* FROM person,customer,account,address,fullname WHERE Status = 1 AND person.ID = customer.PersonID AND person.ID = account.PersonID AND person.ID = address.PersonID AND person.ID = fullname.PersonID AND person.ID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer();
				FullName fullName = new FullName();
				Address address = new Address();
				Account account = new Account();
				
				fullName.setFirstName(rs.getString("FirstName"));
				fullName.setLastName(rs.getString("LastName"));
				fullName.setMiddleName(rs.getString("MiddleName"));
				
				address.setNumber(rs.getInt("Number"));
				address.setStreet(rs.getString("Street"));
				address.setDistrict(rs.getString("District"));
				address.setCity(rs.getString("City"));
				
				account.setUsername(rs.getString("Username"));
				account.setPassword(rs.getString("Password"));
				
				customer.setAccount(account);
				customer.setAddress(address);
				customer.setFullName(fullName);
				
				customer.setMobile(rs.getString("Mobile"));
				customer.setSex(rs.getString("Sex"));
				customer.setEmail(rs.getString("Email"));
				customer.setDateOfBirth(rs.getDate("DateOfBirth"));
				customer.setId(rs.getInt("ID"));
				customer.setNote(rs.getString("Note"));
				customer.setPoint(rs.getDouble("Point"));
				
				return customer;
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
	public Customer findByUsername(String username) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT person.*,customer.*,account.*,address.*,fullname.* FROM person,customer,account,address,fullname WHERE Status = 1 AND person.ID = customer.PersonID AND person.ID = account.PersonID AND person.ID = address.PersonID AND person.ID = fullname.PersonID AND account.Username = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer();
				FullName fullName = new FullName();
				Address address = new Address();
				Account account = new Account();
				
				fullName.setFirstName(rs.getString("FirstName"));
				fullName.setLastName(rs.getString("LastName"));
				fullName.setMiddleName(rs.getString("MiddleName"));
				
				address.setNumber(rs.getInt("Number"));
				address.setStreet(rs.getString("Street"));
				address.setDistrict(rs.getString("District"));
				address.setCity(rs.getString("City"));
				
				account.setUsername(rs.getString("Username"));
				account.setPassword(rs.getString("Password"));
				
				customer.setAccount(account);
				customer.setAddress(address);
				customer.setFullName(fullName);
				
				customer.setMobile(rs.getString("Mobile"));
				customer.setSex(rs.getString("Sex"));
				customer.setEmail(rs.getString("Email"));
				customer.setDateOfBirth(rs.getDate("DateOfBirth"));
				customer.setId(rs.getInt("ID"));
				customer.setNote(rs.getString("Note"));
				customer.setPoint(rs.getDouble("Point"));
				
				return customer;
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
	public Customer findByEmail(String email) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT person.*,customer.*,account.*,address.*,fullname.* FROM person,customer,account,address,fullname WHERE Status = 1 AND person.ID = customer.PersonID AND person.ID = account.PersonID AND person.ID = address.PersonID AND person.ID = fullname.PersonID AND email = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer();
				FullName fullName = new FullName();
				Address address = new Address();
				Account account = new Account();
				
				fullName.setFirstName(rs.getString("FirstName"));
				fullName.setLastName(rs.getString("LastName"));
				fullName.setMiddleName(rs.getString("MiddleName"));
				
				address.setNumber(rs.getInt("Number"));
				address.setStreet(rs.getString("Street"));
				address.setDistrict(rs.getString("District"));
				address.setCity(rs.getString("City"));
				
				account.setUsername(rs.getString("Username"));
				account.setPassword(rs.getString("Password"));
				
				customer.setAccount(account);
				customer.setAddress(address);
				customer.setFullName(fullName);
				
				customer.setMobile(rs.getString("Mobile"));
				customer.setSex(rs.getString("Sex"));
				customer.setEmail(rs.getString("Email"));
				customer.setDateOfBirth(rs.getDate("DateOfBirth"));
				customer.setId(rs.getInt("ID"));
				customer.setNote(rs.getString("Note"));
				customer.setPoint(rs.getDouble("Point"));
				
				return customer;
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
	public Customer checkLogin(String username, String password) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT person.*,customer.*,account.*,address.*,fullname.* FROM person,customer,account,address,fullname WHERE Status = 1 AND person.ID = customer.PersonID AND person.ID = account.PersonID AND person.ID = address.PersonID AND person.ID = fullname.PersonID AND account.Username = ? AND account.Password = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer();
				FullName fullName = new FullName();
				Address address = new Address();
				Account account = new Account();
				
				fullName.setFirstName(rs.getString("FirstName"));
				fullName.setLastName(rs.getString("LastName"));
				fullName.setMiddleName(rs.getString("MiddleName"));
				
				address.setNumber(rs.getInt("Number"));
				address.setStreet(rs.getString("Street"));
				address.setDistrict(rs.getString("District"));
				address.setCity(rs.getString("City"));
				
				account.setUsername(rs.getString("Username"));
				account.setPassword(rs.getString("Password"));
				
				customer.setAccount(account);
				customer.setAddress(address);
				customer.setFullName(fullName);
				
				customer.setMobile(rs.getString("Mobile"));
				customer.setSex(rs.getString("Sex"));
				customer.setEmail(rs.getString("Email"));
				customer.setDateOfBirth(rs.getDate("DateOfBirth"));
				customer.setId(rs.getInt("ID"));
				customer.setNote(rs.getString("Note"));
				customer.setPoint(rs.getDouble("Point"));
				
				return customer;
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
	public Customer findByOrder(Order order) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT person.*,customer.*,account.*,address.*,fullname.* FROM person,customer,account,address,fullname,book_store_online.order WHERE person.ID = customer.PersonID AND person.ID = account.PersonID AND person.ID = address.PersonID AND person.ID = fullname.PersonID AND person.ID = book_store_online.order.CustomerID AND book_store_online.order.ID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, order.getId());
			rs = ps.executeQuery();
			if (rs.next()) {
				Customer customer = new Customer();
				FullName fullName = new FullName();
				Address address = new Address();
				Account account = new Account();
				
				fullName.setFirstName(rs.getString("FirstName"));
				fullName.setLastName(rs.getString("LastName"));
				fullName.setMiddleName(rs.getString("MiddleName"));
				
				address.setNumber(rs.getInt("Number"));
				address.setStreet(rs.getString("Street"));
				address.setDistrict(rs.getString("District"));
				address.setCity(rs.getString("City"));
				
				account.setUsername(rs.getString("Username"));
				account.setPassword(rs.getString("Password"));
				
				customer.setAccount(account);
				customer.setAddress(address);
				customer.setFullName(fullName);
				
				customer.setMobile(rs.getString("Mobile"));
				customer.setSex(rs.getString("Sex"));
				customer.setEmail(rs.getString("Email"));
				customer.setDateOfBirth(rs.getDate("DateOfBirth"));
				customer.setId(rs.getInt("ID"));
				customer.setNote(rs.getString("Note"));
				customer.setPoint(rs.getDouble("Point"));
				
				return customer;
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
