package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.CourseDTO;
import vn.edu.hcmuaf.FocusAppProject.models.Department;

public interface SemesterCourseServiceImp {
    void createSemesterCourse(CourseDTO courseDTO, int semesterId, Department department) throws Exception;
}
