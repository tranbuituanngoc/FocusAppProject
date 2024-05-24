package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.CourseGroup;
import vn.edu.hcmuaf.FocusAppProject.models.CourseGroupCourses;
import vn.edu.hcmuaf.FocusAppProject.models.keys.KeyCourseGroupCourses;

@Repository
public interface CourseGroupCourseRepository extends JpaRepository<CourseGroupCourses, KeyCourseGroupCourses> {
    @Query("SELECT cgc.group FROM course_group_courses cgc WHERE cgc.course.id = :courseId AND cgc.group.trainingProgram.id = :trainingProgramId")
    CourseGroup findCourseGroupByCourseId(@Param("courseId") int courseId, @Param("trainingProgramId") long trainingProgramId);

}
