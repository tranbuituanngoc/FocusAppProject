package vn.edu.hcmuaf.FocusAppProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.util.StringUtils;
import vn.edu.hcmuaf.FocusAppProject.Util.EmailUtil;
import vn.edu.hcmuaf.FocusAppProject.Util.SaltStringUtil;
import vn.edu.hcmuaf.FocusAppProject.dto.UserDTO;
import vn.edu.hcmuaf.FocusAppProject.exception.DataNotFoundException;
import vn.edu.hcmuaf.FocusAppProject.exception.PermissionDenyException;
import vn.edu.hcmuaf.FocusAppProject.exception.VerifyDenyException;
import lombok.RequiredArgsConstructor;
import vn.edu.hcmuaf.FocusAppProject.models.Role;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.repository.RoleRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.TrainingProgramRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.UserRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.AuthServiceImp;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SaltStringUtil saltString;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Override
    public boolean checkLogin(String email, String password) {
        User users = userRepository.findByEmail(email);
        if (users == null) return false;
        return passwordEncoder.matches(password, users.getPassword());
    }

    @Override
    public User createUser(UserDTO userDTO) throws Exception {
        String email = userDTO.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw new DataIntegrityViolationException("Email is already exists");
        }
        Role role = roleRepository.findById(userDTO.getRole_id()).orElseThrow(() -> new DataNotFoundException("Role not found"));
        if (role.getRoleName().equalsIgnoreCase("ADMIN")) {
            throw new PermissionDenyException("You cannot register an admin account");
        }
        //Set timevalid
        Date todaysDate = new Date(new java.util.Date().getTime());
        Calendar c = Calendar.getInstance();
        c.setTime(todaysDate);
        //set time valid is 5 minute
        c.add(Calendar.MINUTE, 5);
        Timestamp timeValid = new Timestamp(c.getTimeInMillis());
        //Create salt string(random string)
        String verificationCode = saltString.getSaltString();
        //Create new user
        User user = User.builder().email(userDTO.getEmail())
                .name(userDTO.getName())
                .provider(userDTO.getProvider())
                .token(userDTO.getToken())
                .isVerify(false)
                .verificationCode(verificationCode)
                .timeValid(timeValid)
                .status(true)
                .roles(role)
                .build();
        if (!StringUtils.hasText(userDTO.getProvider())) {
            System.out.println(userDTO.getPassword());
            String password = userDTO.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            user.setPassword(encodedPassword);
        }
        User savedUser = userRepository.save(user);

//        emailUtil.sendMail(userDTO.getEmail(),"Đây là email xác thực","Xác thực tài khoản");

        return savedUser;
    }

    @Override
    public User updateVerify(long userId, String verificationCode) throws Exception {
        Optional<User> existingUser = userRepository.findById(userId);
        User user = null;
        if (existingUser.isPresent()) {
            user = existingUser.get();
            Calendar c = Calendar.getInstance();
            Date todayDate = new Date(new java.util.Date().getTime());
            c.setTime(todayDate);
            Timestamp timestamp = new Timestamp(c.getTimeInMillis());

            if (user.getVerificationCode().equals(verificationCode)) {
                if (timestamp.before(user.getTimeValid()) && !user.isVerify()) {
                    user.setVerify(true);
                    userRepository.save(user);
                } else {
                    throw new VerifyDenyException("Account verification failed");
                }
            }
        } else {
            throw new NoSuchElementException("User not found");
        }
        return user;
    }

    @Override
    public String findRoleNameByEmail(String email) {
        User users = userRepository.findByEmail(email);

        return roleRepository.findRoleNameById(users.getRoles().getId());
    }


}
