package vn.edu.hcmuaf.FocusAppProject.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.hcmuaf.FocusAppProject.dto.UserDTO;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.AuthServiceImp;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthServiceImp authServiceImp;

    @GetMapping("/login")
    public String login(@RequestParam(value = "fail", required = false) String fail, Model model, HttpServletRequest request) {
        if (fail != null) {
            String email = (String) request.getSession().getAttribute("email");
            model.addAttribute("email", email);
            model.addAttribute("error", "Đăng nhập thất bại");
        }
        return "login";
    }

    @GetMapping("/register")
    public String register(@RequestParam(value = "fail", required = false) String fail, Model model) {
        if (fail != null) {
            String message = (String) model.getAttribute("message");
            UserDTO userDTO = (UserDTO) model.getAttribute("userDTO");
            model.addAttribute("message", message);
            model.addAttribute("userDTO", userDTO != null ? userDTO : new UserDTO());
        }else {
            model.addAttribute("userDTO", new UserDTO());
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid UserDTO userDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", "Đăng ký thất bại");
            redirectAttributes.addFlashAttribute("userDTO", userDTO);
            return "redirect:/auth/register?fail";
        }
        try {
            authServiceImp.createUser(userDTO);
            model.addAttribute("message", "Đăng ký thành công, vui lòng kiểm tra email để xác thực tài khoản");
            return "login";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            redirectAttributes.addFlashAttribute("userDTO", userDTO);
            redirectAttributes.addFlashAttribute("message", "Đăng ký thất bại: " + e.getMessage());
            return "redirect:/auth/register?fail";
        }
    }
}
