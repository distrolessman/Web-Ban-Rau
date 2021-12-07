package nongsan.webmvc.dao.impl;

import java.util.List;

import nongsan.webmvc.model.User;
import nongsan.webmvc.dao.IUserDAO;
import org.hibernate.Session;

import javax.persistence.Query;

public class UserDAOImpl extends BaseDAO<User> implements IUserDAO {

    public UserDAOImpl() {
        super();
        setType(User.class);
    }

    @Override
    public Boolean createUser(User user) {
        return save(user);
    }

    @Override
    public Boolean deleteUser(Integer id) {
        return delete(id);
    }

    @Override
    public User findUserById(Integer id) {
        return findById(id);
    }

    @Override
    public Boolean updateUser(User user) {
        return save(user);
    }

    @Override
    public List<User> findAllUser() {
        return findAll();
    }

    public User findUserByUsername(String username) {
        Session session = null;
        try {
            session = FACTORY.openSession();
            Query query = session.createQuery("FROM User U WHERE U.username = :username");
            query.setParameter("username", username);
            List<User> listUser = query.getResultList();
            if (listUser.size() != 0) {
                return listUser.get(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null)
                session.close();
        }
        return null;
    }
}


