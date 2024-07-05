package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.hcmuaf.FocusAppProject.exception.DataNotFoundException;
import vn.edu.hcmuaf.FocusAppProject.models.Role;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.repository.UserRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.AuthServiceImp;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.UserServiceImp;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AuthServiceImp authServiceImp;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceImp userService;

    @GetMapping("/home")
    public String admin(HttpSession session, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userRepository.findByEmail(username);
            session.setAttribute("user", user);
        }
        model.addAttribute("title", "Quản lý người dùng");
        return "admin/user-manager";
    }

    @GetMapping("/role")
    public String rolePage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("title", "Quản lý quyền hạn");
        return "admin/role-manager";
    }

    @GetMapping("/")
    public String adminHome() {
        return "admin/user-manager";
    }

    @GetMapping("/user-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getUserForm(Model model) {
        return handleUserForm(null, model);
    }

    @GetMapping("/user-form/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getUserForm(@PathVariable Long userId, Model model) {
        return handleUserForm(userId, model);
    }

    private String handleUserForm(Long userId, Model model) {
        if (userId != null) {
            User user = userService.findByUserId(userId);
            model.addAttribute("userId", userId);
            model.addAttribute("name", user.getName());
            model.addAttribute("email", user.getEmail());
            model.addAttribute("role", user.getRoles().getId());

            model.addAttribute("header", "Cập nhật thông tin tài khoản");
        } else {
            model.addAttribute("header", "Tạo tài khoản mới");
        }

        return "admin/user-form";
    }

    @GetMapping("/role-form")
    @PreAuthorize("hasRole('ADMIN')")
    public String getRoleForm(Model model) throws DataNotFoundException {
        return handleRoleForm(null, model);
    }

    @GetMapping("/role-form/{roleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public String getRoleForm(@PathVariable Integer roleId, Model model) throws DataNotFoundException {
        return handleRoleForm(roleId, model);
    }

    private String handleRoleForm(Integer roleId, Model model) throws DataNotFoundException {
        if (roleId != null) {
            Role role = authServiceImp.getRoleById(roleId);
            model.addAttribute("roleId", roleId);
            model.addAttribute("name", role.getRoleName());
            model.addAttribute("user_permit", role.isUserPermission());
            model.addAttribute("role_permit", role.isRolePermission());

            model.addAttribute("header", "Cập nhật thông tin quyền hạn");
        } else {
            model.addAttribute("header", "Tạo quyền hạn mới");
        }

        return "admin/role-form";
    }
}
