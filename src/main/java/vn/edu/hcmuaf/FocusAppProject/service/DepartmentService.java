package vn.edu.hcmuaf.FocusAppProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.models.Department;
import vn.edu.hcmuaf.FocusAppProject.repository.DepartmentRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.DepartmentServiceImp;

import java.util.Optional;

@Service
public class DepartmentService implements DepartmentServiceImp {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department createDepartment(String departmentID, String departmentName) {
        Optional<Department> existingDepartment = departmentRepository.findById(Long.parseLong(departmentID));
        if (!existingDepartment.isPresent()) {
            Department department = Department.builder()
                    .id(Long.parseLong(departmentID))
                    .departmentName(departmentName)
                    .build();
            return departmentRepository.save(department);
        }
        return existingDepartment.get();
    }
}
