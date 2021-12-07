package nongsan.webmvc.dao;

import java.util.List;

import nongsan.webmvc.model.User;

public interface IUserDAO extends IBaseDAO<User> {

    Boolean createUser(User user);

    Boolean updateUser(User user);

    Boolean deleteUser(Integer id);

    User findUserById(Integer id);

    List<User> findAllUser();

    User findUserByUsername(String username);
}
