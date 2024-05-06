package vn.edu.hcmuaf.FocusAppProject.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import vn.edu.hcmuaf.FocusAppProject.dto.UserDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserLoginDTO;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.AuthServiceImp;

@Controller
@RequestMapping("/auth")
public class AuthController {
    AuthServiceImp authServiceImp;
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ModelAndView login(@Valid UserLoginDTO userLoginDTO) {
        ModelAndView modelAndView = new ModelAndView();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
        );
        System.out.println("Auth: here");
        SecurityContextHolder.getContext().setAuthentication(authentication);


        if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            System.out.println("Role: User");
            modelAndView.setViewName("redirect:/home");
        } else if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            System.out.println("Role: Admin");
            modelAndView.setViewName("redirect:/admin/index");
        }

        return modelAndView;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
