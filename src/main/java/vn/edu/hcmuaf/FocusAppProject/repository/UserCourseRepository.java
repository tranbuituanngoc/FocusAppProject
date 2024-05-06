package vn.edu.hcmuaf.FocusAppProject.repository;

import vn.edu.hcmuaf.FocusAppProject.models.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse,Long> {
}
