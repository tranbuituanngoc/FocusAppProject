package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.Course;
import vn.edu.hcmuaf.FocusAppProject.models.SemesterCourse;
import vn.edu.hcmuaf.FocusAppProject.models.keys.KeySemesterCourse;

import java.util.List;

@Repository
public interface SemesterCourseRepository extends JpaRepository<SemesterCourse, KeySemesterCourse> {
    @Query("SELECT sc.course FROM semester_course sc JOIN sc.course.courseGroupCourses cgc WHERE CAST(sc.semester.semesterId AS string) LIKE %:semesterId AND cgc.group.trainingProgram.id = :trainingProgramId")
    List<Course> findCoursesBySemesterIdEndingWith(@Param("semesterId") String semesterId, @Param("trainingProgramId") long trainingProgramId);

    @Query("SELECT sc.course FROM semester_course sc WHERE sc.id.semesterId = :semesterId")
    List<Course> findCoursesBySemesterId(@Param("semesterId") int semesterId);
}
