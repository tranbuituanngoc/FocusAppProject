package vn.edu.hcmuaf.FocusAppProject.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.repository.UserRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService {
    @Autowired
    UserRepository userRepository;


    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            User user = userRepository.findByEmail(email);
            if (user == null || user.isDelete()) {
                throw new UsernameNotFoundException("User is not exist");
            }
            if (!user.isVerify()) {
                Calendar c = Calendar.getInstance();
                Date todayDate = new Date(new java.util.Date().getTime());
                c.setTime(todayDate);
                Timestamp timestamp = new Timestamp(c.getTimeInMillis());
                if (timestamp.after(user.getTimeValid()) ) {
                    System.out.println("User id: "+user.getId());
                    throw new InternalAuthenticationServiceException(user.getId()+"Time valid is expired.");
                } else {
                    System.out.println("Account not verified -2");
                    throw new InternalAuthenticationServiceException("Account not verified");
                }
            }
            return user;
        };
    }
}

