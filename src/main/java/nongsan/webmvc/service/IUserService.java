package nongsan.webmvc.service;

import nongsan.webmvc.model.User;

import java.util.List;

public interface IUserService {

    Boolean createUser(String userName, String userEmail, String userPhone, String userUsername, String userPassword, String userCreated);

    Boolean updateUser(String id, String userName, String userEmail, String userPhone, String userUsername, String userPassword, String userCreated);

    Boolean deleteUser(String id);

    User findUserById(String id);

    List<User> findAllUser();

    Boolean checkLoginUser(String username, String password);

    User findUserByUsername(String username);
}

