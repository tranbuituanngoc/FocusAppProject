package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.CourseGroupCourses;
import vn.edu.hcmuaf.FocusAppProject.models.keys.KeyCourseGroupCourses;

@Repository
public interface CourseGroupCourseRepository extends JpaRepository<CourseGroupCourses, KeyCourseGroupCourses> {
}
