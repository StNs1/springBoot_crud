package web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.model.Role;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findRoleById(long id);
}
