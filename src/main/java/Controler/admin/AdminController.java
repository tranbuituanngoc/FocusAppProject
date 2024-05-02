package Controler.admin;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @PostConstruct
    public void init() {
        System.out.println("AdminController is initialized");
    }
    @GetMapping("/index")
    public String admin() {
        System.out.println("AdminController is called");
        return "admin/index";
    }
}
