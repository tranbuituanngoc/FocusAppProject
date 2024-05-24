package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.FocusAppProject.dto.UserStudentInfoDTO;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.DepartmentServiceImp;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.MajorServiceImp;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.UserServiceImp;

@RestController
@RequestMapping("/api/nguoi-dung")
public class UserController {
    @Autowired
    private UserServiceImp userService;
    @Autowired
    private DepartmentServiceImp departmentService;
    @Autowired
    private MajorServiceImp majorService;

    @GetMapping("/isUpdate/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> isUpdateInfo(@PathVariable long userId) throws Exception {
        try {
            return ResponseEntity.ok(userService.isUpdateInfo(userId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/getDepartment")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getDepartment() {
        try {
            return ResponseEntity.ok(departmentService.getAllDepartments());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getMajor/{departmentId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getMajor(@PathVariable int departmentId) {
        try {
            return ResponseEntity.ok(majorService.getAllMajorsByDepartmentId(departmentId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/updateInfo")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateInfo(@RequestBody @Valid UserStudentInfoDTO data) {
        try {
            userService.updateStudentInfo(data);
            return ResponseEntity.ok("Cập nhật thông tin thành công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}