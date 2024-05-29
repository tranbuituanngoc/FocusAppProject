package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.Course;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.models.UserCourse;

import java.util.List;

@Repository
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    UserCourse findByUserIdAndCourseIdAndUserSemestersId(long userId, int courseId, long userSemesterId);

    @Query("SELECT uc.course FROM user_course uc INNER JOIN uc.scores s WHERE uc.user = :user AND s.result = true")
    List<Course> findPassedCoursesByUser(@Param("user") User user);

    @Query("SELECT uc.course FROM user_course uc INNER JOIN uc.scores s WHERE uc.user = :user AND s.result = false")
    List<Course> findFailCoursesByUser(@Param("user") User user);

    @Query("SELECT uc.course FROM user_course uc WHERE uc.userSemesters.semester.semesterId = :semesterId AND uc.user.id = :userId")
    List<Course> findCoursesBySemesterIdAndUserId(@Param("semesterId") int semesterId, @Param("userId") long userId);
    List<UserCourse> findByUserSemestersId(long userSemesterId);
}
