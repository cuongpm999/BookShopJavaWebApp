package vn.ptit.business.persondao;

import java.util.List;

import vn.ptit.model.person.Staff;

public interface StaffDAO {
	public boolean insert(Staff staff);
	public List<Staff> findAll();
	public int delete(int id);
	public boolean update(Staff staff);
	public Staff findById(int id);
	public Staff findByUsername(String username);
	public Staff findByEmail(String email);
	public Staff checkLogin(String username, String password);

}
