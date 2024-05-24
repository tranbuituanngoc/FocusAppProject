package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.DepartmentDTO;
import vn.edu.hcmuaf.FocusAppProject.models.Department;

import java.util.List;

public interface DepartmentServiceImp {
    Department createDepartment(String departmentID, String departmentName);
    List<DepartmentDTO> getAllDepartments();
}
