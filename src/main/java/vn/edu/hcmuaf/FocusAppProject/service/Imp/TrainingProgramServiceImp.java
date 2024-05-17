package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.TrainingProgramDTO;
import vn.edu.hcmuaf.FocusAppProject.models.Department;
import vn.edu.hcmuaf.FocusAppProject.models.TrainingProgram;

public interface TrainingProgramServiceImp {
    TrainingProgram createTrainingProgram(TrainingProgramDTO trainingProgram, Department department);
    boolean isExist(int year, String departmentID);

}
