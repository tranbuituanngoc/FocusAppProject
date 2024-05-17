package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.TrainingProgramSemesters;
import vn.edu.hcmuaf.FocusAppProject.models.keys.KeyTrainingProgramSemester;

@Repository
public interface TrainingProgramSemesterRepository extends JpaRepository<TrainingProgramSemesters, KeyTrainingProgramSemester> {
}
