package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.edu.hcmuaf.FocusAppProject.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo,Long> {
    @Query("SELECT t FROM todo t WHERE t.user.id = :userId AND t.deadline = :date")
    List<Todo> findTodosByUserIdAndDate(@Param("userId") Long userId, @Param("date") LocalDate date);
}
