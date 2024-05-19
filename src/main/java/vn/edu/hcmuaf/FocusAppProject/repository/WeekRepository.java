package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.Week;

import java.time.LocalDate;

@Repository
public interface WeekRepository extends JpaRepository<Week, Long> {
    boolean existsBySemesterWeekAndStartDateAndEndDate(int semesterWeek, LocalDate startDate, LocalDate endDate);
    Week findBySemesterWeekAndStartDateAndEndDate(int semesterWeek, LocalDate startDate, LocalDate endDate);
}
