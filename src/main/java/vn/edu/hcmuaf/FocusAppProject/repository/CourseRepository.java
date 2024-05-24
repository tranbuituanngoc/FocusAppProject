package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    @Query("SELECT c FROM courses c JOIN c.courseGroupCourses cgc WHERE c.courseName = :courseName AND cgc.group.trainingProgram.id = :trainingProgramId")
    Course findCourseByNameAndTrainingProgramId(@Param("courseName") String courseName, @Param("trainingProgramId") long trainingProgramId);
}
