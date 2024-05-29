package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.UserSemesters;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSemesterRepository extends JpaRepository<UserSemesters, Long> {
   Optional<UserSemesters> findByUserIdAndSemesterSemesterId(long userId, int semesterId);
   List<UserSemesters> findByUserId(long userId);
}
