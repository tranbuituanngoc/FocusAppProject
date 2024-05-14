package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.repository.UserRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.AuthServiceImp;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    AuthServiceImp authServiceImp;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/home")
    public String home( HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userRepository.findByEmail(username);
            session.setAttribute("user",user);
        }
        return "index";
    }
}
