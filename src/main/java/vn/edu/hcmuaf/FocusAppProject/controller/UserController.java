package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import vn.edu.hcmuaf.FocusAppProject.dto.*;
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
            User user = userService.updateDesiredScore(userId, desiredScore);
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("user", user);
            return ResponseEntity.ok("Cập nhật điểm cần đạt thành công");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllUserForAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUserForAdmin() {
        try {
            return ResponseEntity.ok(userService.getAllUserForAdmin());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAllRoleForAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllRole() {
        try {
            return ResponseEntity.ok(authService.getAllRole());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/existEmail")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> existEmail(@RequestParam String email) {
        try {
            return ResponseEntity.ok(userService.existsByEmail(email));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create-user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        try {
            return ResponseEntity.ok(authService.createUserAdmin(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update-user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@RequestBody UserAdminDTO user) {
        try {
            return ResponseEntity.ok(authService.updateUserAdmin(user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete-user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok("Xóa người dùng thành công");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/create-role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createRole(@RequestBody RoleDTO roleDTO) {
        try {
            return ResponseEntity.ok(authService.createRole(roleDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update-role")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateRole(@RequestBody RoleDTO roleDTO) {
        try {
            return ResponseEntity.ok(authService.updateRole(roleDTO));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete-role/{roleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteRole(@PathVariable int roleId) {
        try {
            authService.deleteRole(roleId);
            return ResponseEntity.ok("Xóa quyền hạn thành công");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}