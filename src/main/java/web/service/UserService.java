package web.service;

import web.model.Role;
import web.model.User;

import java.util.List;

public interface UserService {
    boolean registerUser(User user);
    void save(User user);
    void deleteUser(long id);
    List<User> listUsers();
    User getUserById(long id);
    User getUserByEmail(String email);
    List<Role> getRoles(long id1, long id2);
}
