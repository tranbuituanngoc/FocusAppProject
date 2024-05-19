package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.SemesterDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.SemesterDetailDTO;
import vn.edu.hcmuaf.FocusAppProject.exception.DataNotFoundException;
import vn.edu.hcmuaf.FocusAppProject.models.Semester;

import java.time.LocalDate;

public interface SemesterServiceImp {
    Semester createOrUpdateSemester(SemesterDetailDTO semesterDetailDTO);
    Integer getCurrentSemester();
}
