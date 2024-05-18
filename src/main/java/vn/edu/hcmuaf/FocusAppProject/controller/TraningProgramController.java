package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.FocusAppProject.dto.CourseDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.LinkToDKMHDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.SemesterDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.TrainingProgramDTO;
import vn.edu.hcmuaf.FocusAppProject.models.Department;
import vn.edu.hcmuaf.FocusAppProject.models.TrainingProgram;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.DepartmentServiceImp;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.SemesterCourseServiceImp;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.TrainingProgramSemesterServiceImp;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.TrainingProgramServiceImp;

@RestController
@RequestMapping("/api/ctdt")
public class TraningProgramController {
    @Autowired
    private TrainingProgramServiceImp traningProgramService;
    @Autowired
    private DepartmentServiceImp departmentService;
    @Autowired
    private TrainingProgramSemesterServiceImp traningProgramSemesterService;
    @Autowired
    private SemesterCourseServiceImp semesterCourseService;

    @GetMapping("/isExist")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Boolean> isExistTrainingProgram(@RequestParam int year, @RequestParam String departmentID) {
        return ResponseEntity.ok(traningProgramService.isExist(year, departmentID));
    }

    @PostMapping("/create")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<String> createTrainingProgram(@RequestBody @Valid TrainingProgramDTO trainingProgramDTO) throws Exception {
        try {
            Department department = departmentService.createDepartment(trainingProgramDTO.getDepartment().getDepartmentID(), trainingProgramDTO.getDepartment().getDepartmentName());
            TrainingProgram trainingProgram = traningProgramService.createTrainingProgram(trainingProgramDTO, department);
            for (SemesterDTO semesterDTO : trainingProgramDTO.getSemesters()) {
                traningProgramSemesterService.createTrainingProgramSemester(semesterDTO, trainingProgram.getId());
                for (CourseDTO courseDTO : semesterDTO.getCourses()) {
                    semesterCourseService.createSemesterCourse(courseDTO, semesterDTO.getSemesterId(), department);
                }
            }
            return ResponseEntity.ok("Cập nhật dữ liệu thành công!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
