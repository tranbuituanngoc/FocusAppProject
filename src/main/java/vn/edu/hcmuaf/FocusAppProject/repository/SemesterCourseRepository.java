package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.SemesterCourse;
import vn.edu.hcmuaf.FocusAppProject.models.keys.KeySemesterCourse;

@Repository
public interface SemesterCourseRepository extends JpaRepository<SemesterCourse, KeySemesterCourse> {
}
