package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.Week;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeekRepository extends JpaRepository<Week, Long> {
    boolean existsBySemesterWeekAndStartDateAndEndDate(int semesterWeek, LocalDate startDate, LocalDate endDate);

    Week findBySemesterWeekAndStartDateAndEndDate(int semesterWeek, LocalDate startDate, LocalDate endDate);

    List<Week> findBySemesterSemesterId(int semesterId);

    @Query("SELECT w FROM weeks w WHERE :date BETWEEN w.startDate AND w.endDate")
    Week findCurrentWeekByDate(@Param("date") LocalDate date);

}
