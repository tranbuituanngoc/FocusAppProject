package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.Semester;

import java.time.LocalDate;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    @Query("SELECT s.semesterId FROM semester s WHERE :date BETWEEN s.startDate AND s.endDate")
    Integer findCurrentSemesterIdsByDate(@Param("date") LocalDate date);
}