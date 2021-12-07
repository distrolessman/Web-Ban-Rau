package nongsan.webmvc.service.impl; 
 
import java.util.List;

import nongsan.webmvc.dao.IAdminDAO;
import nongsan.webmvc.model.Admin;
import nongsan.webmvc.service.IAdminService;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;

public class AdminServicesImpl implements IAdminService {
	@Inject
	IAdminDAO adminDAO;

	@Override 
	public Boolean createAdmin(String admin_username, String admin_password, String admin_name) {
		Admin admin = new Admin();
		admin.setUsername(admin_username);
		admin.setPassword(BCrypt.hashpw(admin_password, BCrypt.gensalt(12)));
		admin.setName(admin_name);
		return adminDAO.createAdmin(admin);
	} 
 
	@Override
	public Boolean updateAdminInfo(String id, String username, String password, String name) {
		try {
			Admin newAdmin = adminDAO.findAdminById(Integer.parseInt(id));
			newAdmin.setUsername(username);
			newAdmin.setPassword(password);
			newAdmin.setName(name);
			return adminDAO.updateAdminInfo(newAdmin);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return false;
	}

	@Override 
	public Boolean deleteAdmin(String id) {
		try {
			return adminDAO.deleteAdmin(Integer.parseInt(id));
		}
		catch (Exception e){
			e.printStackTrace();
			return false;
		}
	} 
 
	@Override 
	public Admin findAdminById(String id) {
		try {
			return adminDAO.findAdminById(Integer.parseInt(id));
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
	} 
 
	@Override 
	public Boolean checkLoginAdmin(String username, String password) {
		Admin admin =  adminDAO.findAdminByAdminUsername(username);
		if(admin == null)
			return false;
		return BCrypt.checkpw(password, admin.getPassword());
	} 
 
	@Override 
	public List<Admin> findAllAdmin() {
		return adminDAO.findAllAdmin();
	}	
} 
 
