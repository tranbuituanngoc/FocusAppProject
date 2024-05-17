package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.SemesterDTO;
import vn.edu.hcmuaf.FocusAppProject.exception.DataNotFoundException;

public interface TrainingProgramSemesterServiceImp {
    void createTrainingProgramSemester(SemesterDTO semesterDTO, long trainingProgramID) throws DataNotFoundException;
}
