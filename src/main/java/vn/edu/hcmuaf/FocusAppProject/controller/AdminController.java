package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/home")
    public String admin() {
        System.out.println("AdminController is called");
        return "admin/index";
    }
    @GetMapping("/")
    public String adminHome() {
        System.out.println("AdminController is called 2");
        return "admin/index";
    }
}
