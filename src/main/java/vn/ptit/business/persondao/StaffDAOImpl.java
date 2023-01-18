package vn.ptit.business.persondao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vn.ptit.business.configs.ConnectionPool;
import vn.ptit.business.utils.DBUtil;
import vn.ptit.model.person.Account;
import vn.ptit.model.person.Address;
import vn.ptit.model.person.Customer;
import vn.ptit.model.person.FullName;
import vn.ptit.model.person.Staff;

public class StaffDAOImpl implements StaffDAO{

	@Override
	public boolean insert(Staff staff) {
		boolean isSuccess = true;

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql1 = "INSERT INTO person (Mobile,Sex,DateOfBirth,Email,Status) VALUES (?,?,?,?,?)";
		String sql2 = "INSERT INTO account (PersonID,Username,Password) VALUES (?,?,?)";
		String sql3 = "INSERT INTO address (PersonID,Number,Street,District,City) VALUES (?,?,?,?,?)";
		String sql4 = "INSERT INTO fullname (PersonID,FirstName,LastName,MiddleName) VALUES (?,?,?,?)";
		String sql5 = "INSERT INTO staff (Position,DateStart,PersonID) VALUES (?,?,?)";

		try {
			connection.setAutoCommit(false);

			ps = connection.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, staff.getMobile());
			ps.setString(2, staff.getSex());
			ps.setDate(3, new java.sql.Date(staff.getDateOfBirth().getTime()));
			ps.setString(4, staff.getEmail());
			ps.setBoolean(5, staff.isStatus());
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				staff.setId(rs.getInt(1));
			}

			ps = connection.prepareStatement(sql2);
			ps.setInt(1, staff.getId());
			ps.setString(2, staff.getAccount().getUsername());
			ps.setString(3, staff.getAccount().getPassword());
			ps.executeUpdate();
			
			ps = connection.prepareStatement(sql3);
			ps.setInt(1, staff.getId());
			ps.setInt(2, staff.getAddress().getNumber());
			ps.setString(3, staff.getAddress().getStreet());
			ps.setString(4, staff.getAddress().getDistrict());
			ps.setString(5, staff.getAddress().getCity());
			ps.executeUpdate();
			
			ps = connection.prepareStatement(sql4);
			ps.setInt(1, staff.getId());
			ps.setString(2, staff.getFullName().getFirstName());
			ps.setString(3, staff.getFullName().getLastName());
			ps.setString(4, staff.getFullName().getMiddleName());
			ps.executeUpdate();
			
			ps = connection.prepareStatement(sql5);	
			ps.setString(1, staff.getPosition());
			ps.setDate(2, new java.sql.Date(staff.getDateStart().getTime()));
			ps.setInt(3, staff.getId());
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
	public List<Staff> findAll() {
		List<Staff> staffs = new ArrayList<>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT person.*,staff.*,account.*,address.*,fullname.* FROM person,staff,account,address,fullname WHERE Status = 1 AND person.ID = staff.PersonID AND person.ID = account.PersonID AND person.ID = address.PersonID AND person.ID = fullname.PersonID";
		try {
			ps = connection.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				Staff staff = new Staff();
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
				
				staff.setAccount(account);
				staff.setAddress(address);
				staff.setFullName(fullName);
				
				staff.setMobile(rs.getString("Mobile"));
				staff.setSex(rs.getString("Sex"));
				staff.setEmail(rs.getString("Email"));
				staff.setDateOfBirth(rs.getDate("DateOfBirth"));
				staff.setId(rs.getInt("ID"));
				staff.setDateStart(rs.getDate("DateStart"));
				staff.setPosition(rs.getString("Position"));
				
				staffs.add(staff);
			}
			return staffs;
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
	public boolean update(Staff staff) {
		boolean isSuccess = true;

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql1 = "UPDATE person SET Mobile = ?,Sex = ?,DateOfBirth = ?,Email = ? WHERE ID = ?";
		String sql2 = "UPDATE account SET Username = ?,Password = ? WHERE PersonID = ?";
		String sql3 = "UPDATE address SET Number = ?,Street = ?,District = ?,City = ? WHERE PersonID = ?";
		String sql4 = "UPDATE fullname SET FirstName = ?,LastName = ?,MiddleName = ? WHERE PersonID = ?";
		String sql5 = "UPDATE staff SET Position = ?,DateStart = ? WHERE PersonID = ?";

		try {
			connection.setAutoCommit(false);

			ps = connection.prepareStatement(sql1);
			ps.setString(1, staff.getMobile());
			ps.setString(2, staff.getSex());
			ps.setDate(3, new java.sql.Date(staff.getDateOfBirth().getTime()));
			ps.setString(4, staff.getEmail());
			ps.setInt(5, staff.getId());
			ps.executeUpdate();

			ps = connection.prepareStatement(sql2);
			ps.setString(1, staff.getAccount().getUsername());
			ps.setString(2, staff.getAccount().getPassword());
			ps.setInt(3, staff.getId());
			ps.executeUpdate();
			
			ps = connection.prepareStatement(sql3);
			ps.setInt(1, staff.getAddress().getNumber());
			ps.setString(2, staff.getAddress().getStreet());
			ps.setString(3, staff.getAddress().getDistrict());
			ps.setString(4, staff.getAddress().getCity());
			ps.setInt(5, staff.getId());
			ps.executeUpdate();
			
			ps = connection.prepareStatement(sql4);
			ps.setString(1, staff.getFullName().getFirstName());
			ps.setString(2, staff.getFullName().getLastName());
			ps.setString(3, staff.getFullName().getMiddleName());
			ps.setInt(4, staff.getId());
			ps.executeUpdate();
			
			ps = connection.prepareStatement(sql5);
			ps.setString(1, staff.getPosition());
			ps.setDate(2, new java.sql.Date(staff.getDateStart().getTime()));
			ps.setInt(3, staff.getId());
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
	public Staff findById(int id) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT person.*,staff.*,account.*,address.*,fullname.* FROM person,staff,account,address,fullname WHERE Status = 1 AND person.ID = staff.PersonID AND person.ID = account.PersonID AND person.ID = address.PersonID AND person.ID = fullname.PersonID AND person.ID = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				Staff staff = new Staff();
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
				
				staff.setAccount(account);
				staff.setAddress(address);
				staff.setFullName(fullName);
				
				staff.setMobile(rs.getString("Mobile"));
				staff.setSex(rs.getString("Sex"));
				staff.setEmail(rs.getString("Email"));
				staff.setDateOfBirth(rs.getDate("DateOfBirth"));
				staff.setId(rs.getInt("ID"));
				staff.setDateStart(rs.getDate("DateStart"));
				staff.setPosition(rs.getString("Position"));
				return staff;
				
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
	public Staff findByUsername(String username) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT person.*,staff.*,account.*,address.*,fullname.* FROM person,staff,account,address,fullname WHERE Status = 1 AND person.ID = staff.PersonID AND person.ID = account.PersonID AND person.ID = address.PersonID AND person.ID = fullname.PersonID AND account.Username = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if (rs.next()) {
				Staff staff = new Staff();
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
				
				staff.setAccount(account);
				staff.setAddress(address);
				staff.setFullName(fullName);
				
				staff.setMobile(rs.getString("Mobile"));
				staff.setSex(rs.getString("Sex"));
				staff.setEmail(rs.getString("Email"));
				staff.setDateOfBirth(rs.getDate("DateOfBirth"));
				staff.setId(rs.getInt("ID"));
				staff.setDateStart(rs.getDate("DateStart"));
				staff.setPosition(rs.getString("Position"));
				return staff;
				
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
	public Staff findByEmail(String email) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT person.*,staff.*,account.*,address.*,fullname.* FROM person,staff,account,address,fullname WHERE Status = 1 AND person.ID = staff.PersonID AND person.ID = account.PersonID AND person.ID = address.PersonID AND person.ID = fullname.PersonID AND email = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, email);
			rs = ps.executeQuery();
			if (rs.next()) {
				Staff staff = new Staff();
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
				
				staff.setAccount(account);
				staff.setAddress(address);
				staff.setFullName(fullName);
				
				staff.setMobile(rs.getString("Mobile"));
				staff.setSex(rs.getString("Sex"));
				staff.setEmail(rs.getString("Email"));
				staff.setDateOfBirth(rs.getDate("DateOfBirth"));
				staff.setId(rs.getInt("ID"));
				staff.setDateStart(rs.getDate("DateStart"));
				staff.setPosition(rs.getString("Position"));
				return staff;
				
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
	public Staff checkLogin(String username, String password) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection connection = pool.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "SELECT person.*,staff.*,account.*,address.*,fullname.* FROM person,staff,account,address,fullname WHERE Status = 1 AND person.ID = staff.PersonID AND person.ID = account.PersonID AND person.ID = address.PersonID AND person.ID = fullname.PersonID AND account.Username = ? AND account.Password = ?";
		try {
			ps = connection.prepareStatement(query);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if (rs.next()) {
				Staff staff = new Staff();
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
				
				staff.setAccount(account);
				staff.setAddress(address);
				staff.setFullName(fullName);
				
				staff.setMobile(rs.getString("Mobile"));
				staff.setSex(rs.getString("Sex"));
				staff.setEmail(rs.getString("Email"));
				staff.setDateOfBirth(rs.getDate("DateOfBirth"));
				staff.setId(rs.getInt("ID"));
				staff.setPosition(rs.getString("Position"));
				staff.setDateStart(rs.getDate("DateStart"));
				
				return staff;
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
