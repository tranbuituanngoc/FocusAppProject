package vn.edu.hcmuaf.FocusAppProject.repository;

import vn.edu.hcmuaf.FocusAppProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
    Long findIdByEmail(String email);
}
