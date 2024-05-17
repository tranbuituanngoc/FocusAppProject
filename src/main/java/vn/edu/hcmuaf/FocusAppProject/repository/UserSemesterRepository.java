package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.UserSemesters;

@Repository
public interface UserSemesterRepository extends JpaRepository<UserSemesters, Long> {
}
