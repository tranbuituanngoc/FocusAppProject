package vn.edu.hcmuaf.FocusAppProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.edu.hcmuaf.FocusAppProject.models.TrainingProgramSemesters;
import vn.edu.hcmuaf.FocusAppProject.models.keys.KeyTrainingProgramSemester;

import java.util.List;

@Repository
public interface TrainingProgramSemesterRepository extends JpaRepository<TrainingProgramSemesters, KeyTrainingProgramSemester> {
    List<TrainingProgramSemesters> findTrainingProgramSemestersByTrainingProgramId(long trainingProgramId);
}
