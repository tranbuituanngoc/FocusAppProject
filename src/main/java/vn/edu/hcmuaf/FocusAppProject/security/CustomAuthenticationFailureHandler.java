package vn.edu.hcmuaf.FocusAppProject.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        String email = request.getParameter("email");
        request.getSession().setAttribute("email", email);

        if (exception instanceof InternalAuthenticationServiceException) {
            String[] parts = exception.getMessage().split("Time valid is expired.");
            System.out.println(parts.toString());
            if (exception.getMessage().endsWith("Time valid is expired.")) {
                String userId = parts[0];
                System.out.println("Time valid is expired. UserId: " + userId);
                request.getSession().setAttribute("info", userId);
            }  else {
                request.getSession().setAttribute("error", "Tài khoản chưa được xác thực. Vui lòng xác thực tài khoản trước khi đăng nhập!");
            }
        } else if (exception instanceof BadCredentialsException) {
            System.out.println("BadCredentialsException");
            request.getSession().setAttribute("error", "Mật khẩu không đúng");
        }
        super.setDefaultFailureUrl("/auth/login?fail");
        super.onAuthenticationFailure(request, response, exception);
    }
}
