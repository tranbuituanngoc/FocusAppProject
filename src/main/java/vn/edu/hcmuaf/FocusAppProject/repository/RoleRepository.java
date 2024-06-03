package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    String findRoleNameById(int id);

    Role findRoleByRoleName(String roleName);
}
