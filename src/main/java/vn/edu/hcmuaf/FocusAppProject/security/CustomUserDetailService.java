package vn.edu.hcmuaf.FocusAppProject.security;

import lombok.RequiredArgsConstructor;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService{
    @Autowired
    UserRepository userRepository;


    @Bean
    public UserDetailsService userDetailsService(){
        return email -> {
            User user = userRepository.findByEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("User is not exist");
            }else {
                return user;
            }
        };
    }
}

