package vn.edu.hcmuaf.FocusAppProject.controller;

import com.nimbusds.jose.util.Pair;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.hcmuaf.FocusAppProject.dto.UserDTO;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.AuthServiceImp;

import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthServiceImp authServiceImp;

    @GetMapping("/login")
    public String login(@RequestParam(value = "fail", required = false) String fail, Model model, HttpServletRequest request) {
        if (fail != null) {
            String email = (String) request.getSession().getAttribute("email");
            String info = (String) request.getSession().getAttribute("info");

            System.out.println(info);

            model.addAttribute("email", email);

            if (info != null) {
                model.addAttribute("info", info);

                request.getSession().removeAttribute("info");
            } else {
                String error = (String) request.getSession().getAttribute("error");
                model.addAttribute("error", error != null ? error : "Đăng nhập thất bại");
                request.getSession().removeAttribute("error");
            }

            request.getSession().removeAttribute("email");
        } else {
            String error = (String) model.getAttribute("error");
            String success = (String) model.getAttribute("success");

            if (error != null) {
                model.addAttribute("error", error);
            }
            if (success != null) {
                model.addAttribute("success", success);
            }
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
        } else {
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

    @GetMapping("/login-google")
    public String redirectToGoogleLogin() {
        return "redirect:/oauth2/authorization/google";
    }

    @GetMapping("/oauth2-login-success")
    public String handleGoogleLogin(OAuth2AuthenticationToken token, Model model, HttpSession session) throws Exception {
        if (token != null) {
            OAuth2User principal = token.getPrincipal();
            Map<String, Object> attributes = principal.getAttributes();
            try {
                String email = (String) attributes.get("email");
                User user = authServiceImp.findByEmail(email);
                if (user == null) {
                    user = authServiceImp.createUserGoogle(attributes);
                }
                session.setAttribute("user", user);
                return "redirect:/trang-chu";
            } catch (Exception e) {
                model.addAttribute("error", e.getMessage());
                return "redirect:/auth/login?fail";
            }
        } else {
            model.addAttribute("error", "Đăng nhập bằng google thất bại");
            return "redirect:/auth/login?fail";
        }
    }

    @GetMapping("/verify")
    public String verify(@RequestParam("userId") long userId, @RequestParam("verificationCode") String verificationCode, Model model) {
        try {
            Pair<String, String> message = authServiceImp.updateVerify(userId, verificationCode);
            model.addAttribute(message.getLeft(), message.getRight());
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "login";
    }
    @GetMapping("/re-verify")
    @ResponseBody
    public ResponseEntity<?> reVerify(@RequestParam long userId) {
        try {
            return ResponseEntity.ok(authServiceImp.reVerifyAccount(userId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
