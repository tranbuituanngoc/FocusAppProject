package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import vn.edu.hcmuaf.FocusAppProject.dto.PasswordDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserStudentInfoDTO;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.AuthServiceImp;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.DepartmentServiceImp;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.MajorServiceImp;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.UserServiceImp;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/api/nguoi-dung")
public class UserController {
    @Autowired
    private UserServiceImp userService;
    @Autowired
    private DepartmentServiceImp departmentService;
    @Autowired
    private MajorServiceImp majorService;
    @Autowired
    private AuthServiceImp authService;

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
    @PutMapping("/changePassword/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> changePassword(@PathVariable long userId, @RequestBody @Valid PasswordDTO data) {
        try {
            return ResponseEntity.ok(authService.updatePassword(data, userId));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}