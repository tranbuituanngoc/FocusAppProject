package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.CourseSchedule;

@Repository
public interface CourseScheduleRepository extends JpaRepository<CourseSchedule,Long> {
    boolean existsByUserCourseIdAndWeekId(Long userCourseId, Long weekId);
}
