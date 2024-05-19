package vn.edu.hcmuaf.FocusAppProject.repository;

import vn.edu.hcmuaf.FocusAppProject.models.TestSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;

@Repository
public interface TestScheduleRepository extends JpaRepository<TestSchedule,Long> {
    TestSchedule findByUserCourseIdAndTestDateAndTestTime(Long userCourse, LocalDate testDate, LocalTime testTime);
}
