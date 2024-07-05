package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.Achivement;

import java.time.LocalDate;

@Repository
public interface AchievementRepository extends JpaRepository<Achivement, Long> {
    @Query("SELECT a FROM achivements a WHERE a.user.id = :userId AND a.date = :date")
    Achivement findAchievementByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);

}
