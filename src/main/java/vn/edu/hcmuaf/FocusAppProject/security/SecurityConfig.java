package vn.edu.hcmuaf.FocusAppProject.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailService customUserDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers(
                                        "/auth/login",
                                        "/auth/register",
                                        "/auth/login-google",
                                        "/oauth2/authorization/google",
                                        "/auth/re-verify",
                                        "/auth/verify",
                                        "/auth/forgot-password",
                                        "/auth/handle-forgot-password",
                                        "/auth/reset-password",
                                        "/auth/handle-reset-password")
                                .permitAll()
                                .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
                                .anyRequest().authenticated()).formLogin(
                        login -> login.loginPage("/auth/login")
                                .loginProcessingUrl("/auth/login")
                                .usernameParameter("email")
                                .passwordParameter("password")
                                .successHandler(new CustomAuthenticationSuccessHandler())
                                .failureHandler(new CustomAuthenticationFailureHandler()).permitAll())
                .oauth2Login(oauth2 -> oauth2.defaultSuccessUrl("/auth/oauth2-login-success", true))
                .logout(logout -> logout.logoutUrl("/auth/logout").logoutSuccessUrl("/auth/login").permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/static/**",
                "/admin_style/**",
                "/user_style/**"
        );
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailService.userDetailsService()).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }
}