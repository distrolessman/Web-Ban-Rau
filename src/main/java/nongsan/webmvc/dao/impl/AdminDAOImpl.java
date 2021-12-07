package nongsan.webmvc.dao.impl;

import java.util.List;

import nongsan.webmvc.dao.IAdminDAO;
import nongsan.webmvc.model.Admin;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class AdminDAOImpl extends BaseDAO<Admin> implements IAdminDAO {

    public AdminDAOImpl() {
        super();
        setType(Admin.class);
    }

    @Override
    public Boolean createAdmin(Admin admin) {
        return save(admin);
    }

    @Override
    public Boolean deleteAdmin(Integer id) {
        return this.delete(id);
    }

    @Override
    public Admin findAdminById(Integer id) {
        return findById(id);
    }

    @Override
    public Admin findAdminByAdminUsername(String name) {
        Session session = null;
        try {
            session = FACTORY.openSession();
            Query query = session.createQuery("FROM Admin A WHERE A.username=:name");
            query.setParameter("name", name);
            List<Admin> result = query.list();
            if (result.size() != 0)
                return result.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return null;
    }

    @Override
    public Boolean updateAdminInfo(Admin admin) {
        return save(admin);
    }

    @Override
    public List<Admin> findAllAdmin() {
        return findAll();
    }
} 
