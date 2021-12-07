package nongsan.webmvc.service; 
 
import nongsan.webmvc.model.Admin; 
import java.util.List; 
public interface IAdminService {
	Boolean createAdmin(String admin_username, String admin_password, String admin_name);
 
	Boolean updateAdminInfo(String id, String username, String password, String name);

	Boolean deleteAdmin(String id);
 
	Admin findAdminById(String id);
 
	List<Admin> findAllAdmin();

 	Boolean checkLoginAdmin(String username, String password);
} 
