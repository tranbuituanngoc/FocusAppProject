package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.MajorDTO;

import java.util.List;

public interface MajorServiceImp {
    List<MajorDTO> getAllMajorsByDepartmentId(int departmentId);
}
