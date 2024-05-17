package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.TrainingProgram;
@Repository
public interface TrainingProgramRepository extends JpaRepository<TrainingProgram,Long> {
    TrainingProgram findByYearAndDepartmentId(int year, long departmentId);
    boolean existsByYearAndDepartmentId(int year, long departmentId);
}
