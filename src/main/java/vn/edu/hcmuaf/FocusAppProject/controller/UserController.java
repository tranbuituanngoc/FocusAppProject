package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vn.edu.hcmuaf.FocusAppProject.dto.PasswordDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserStudentInfoDTO;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.AuthServiceImp;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.DepartmentServiceImp;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.UserServiceImp;

@RestController
@RequestMapping("/api/nguoi-dung")
public class UserController {
    @Autowired
    private UserServiceImp userService;
    @Autowired
    private DepartmentServiceImp departmentService;
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
    @PutMapping("/updateDesiredScore/{userId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateDesiredScore(@PathVariable long userId, @RequestParam double desiredScore) {
        try {
            User user =userService.updateDesiredScore(userId, desiredScore);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("user", user);
            return ResponseEntity.ok("Cập nhật điểm cần đạt thành công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}