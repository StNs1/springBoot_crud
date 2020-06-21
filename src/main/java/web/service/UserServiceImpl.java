package web.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.Role;
import web.model.User;
import web.repository.RoleRepository;
import web.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean registerUser(User user) {
        User userFromDB = userRepository.findUserByEmail(user.getEmail());
        if (userFromDB != null) {
            return false;
        }
        user.setRoles(roleRepository.findRoleById(1L));
        userRepository.save(user);
        return true;
    }

    @Override
    public void save(User user) {
            userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<Role> getRoles(long id1, long id2) {
        List<Role> list = roleRepository.findRoleById(1L);
        list.addAll(roleRepository.findRoleById(2L));
        return list;
    }
}
