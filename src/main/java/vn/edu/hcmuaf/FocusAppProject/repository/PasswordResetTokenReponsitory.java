package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.PasswordResetToken;

@Repository
public interface PasswordResetTokenReponsitory extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
