package nongsan.webmvc.dao;

import java.util.List;

import nongsan.webmvc.model.Admin;

public interface IAdminDAO extends IBaseDAO<Admin> {
    Boolean createAdmin(Admin admin);

    Boolean updateAdminInfo(Admin admin);

    Boolean deleteAdmin(Integer id);

    Admin findAdminById(Integer id);

    Admin findAdminByAdminUsername(String name);

    List<Admin> findAllAdmin();
} 
