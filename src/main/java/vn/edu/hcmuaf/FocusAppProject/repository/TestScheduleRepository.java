package vn.edu.hcmuaf.FocusAppProject.repository;

import vn.edu.hcmuaf.FocusAppProject.models.TestSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestScheduleRepository extends JpaRepository<TestSchedule,Long> {
}
