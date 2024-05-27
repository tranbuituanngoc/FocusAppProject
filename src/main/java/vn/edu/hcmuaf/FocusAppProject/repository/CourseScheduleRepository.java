package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.CourseSchedule;

import java.util.List;

@Repository
public interface CourseScheduleRepository extends JpaRepository<CourseSchedule,Long> {
    boolean existsByUserCourseIdAndWeekId(Long userCourseId, Long weekId);
    List<CourseSchedule> findByUserCourseIdAndWeekId(Long userCourseId, Long weekId);
    @Query("SELECT cs FROM course_schedule cs WHERE cs.userCourse.user.id = :userId AND cs.week.id = :weekId")
    List<CourseSchedule> findByUserIdAndWeekId(@Param("userId") Long userId, @Param("weekId") Long weekId);
}
