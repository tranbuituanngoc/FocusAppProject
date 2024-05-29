package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @GetMapping("/trang-chu")
    public String home( HttpSession session,Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            User user = userRepository.findByEmail(username);
            session.setAttribute("user",user);
        }
        model.addAttribute("title", "Trang chủ");
        return "index";
    }
    @GetMapping("/thoi-khoa-bieu")
    public String viewSchedule(Model model) {
        model.addAttribute("title", "Thời khóa biểu");
        return "courseSchedule";
    }
    @GetMapping("/de-xuat-mon-hoc")
    public String CourseSuggest(Model model) {
        model.addAttribute("title", "Đề xuất môn học");
        return "courseSuggest";
    }
    @GetMapping("/xem-diem")
    public String viewScore(Model model) {
        model.addAttribute("title", "Xem điểm");
        return "viewScore";
    }
    @GetMapping("/xem-lich-thi")
    public String viewTestSchedule(Model model) {
        model.addAttribute("title", "Xem lịch thi");
        return "viewTestSchedule";
    }
}
