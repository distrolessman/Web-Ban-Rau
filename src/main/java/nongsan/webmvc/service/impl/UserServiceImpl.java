package nongsan.webmvc.service.impl;


import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import nongsan.webmvc.dao.IUserDAO;
import nongsan.webmvc.dao.impl.UserDAOImpl;
import nongsan.webmvc.model.User;
import nongsan.webmvc.service.IUserService;
import org.mindrot.jbcrypt.BCrypt;

import javax.inject.Inject;

public class UserServiceImpl implements IUserService {
    @Inject
    IUserDAO userDAO;

    @Override
    public Boolean createUser(String userName, String userEmail, String userPhone,
                              String userUsername, String userPassword, String userCreated) {
        try {
            User user = new User();
            user.setName(userName);
            user.setEmail(userEmail);
            user.setPhone(userPhone);
            user.setUsername(userUsername);
            user.setPassword(BCrypt.hashpw(userPassword, BCrypt.gensalt(12)));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            user.setCreated(formatter.parse(userCreated));
            return userDAO.createUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean updateUser(String id, String userName, String userEmail, String userPhone,
                              String userUsername, String userPassword, String userCreated) {
        try {
            User user = userDAO.findUserById(Integer.parseInt(id));
            user.setName(userName);
            user.setEmail(userEmail);
            user.setPhone(userPhone);
            user.setUsername(userUsername);
            user.setPassword(userPassword);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            user.setCreated(formatter.parse(userCreated));
            return userDAO.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> findAllUser() {
        return userDAO.findAllUser();
    }


    @Override
    public Boolean deleteUser(String id) {
        try {
            return userDAO.deleteUser(Integer.parseInt(id));
        } catch (Exception e){
            e.printStackTrace();;
        }
        return false;
    }

    @Override
    public User findUserById(String id) {
        try {
            return userDAO.findUserById(Integer.parseInt(id));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean checkLoginUser(String username, String password) {
        User user = userDAO.findUserByUsername(username);
        if(user == null)
            return false;
        return BCrypt.checkpw(password, user.getPassword());
    }

    @Override
    public User findUserByUsername(String username){
        return userDAO.findUserByUsername(username);
    }
}