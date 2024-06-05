package vn.edu.hcmuaf.FocusAppProject.service;

import com.nimbusds.jose.util.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import vn.edu.hcmuaf.FocusAppProject.Util.EmailUtil;
import vn.edu.hcmuaf.FocusAppProject.Util.SaltStringUtil;
import vn.edu.hcmuaf.FocusAppProject.dto.PasswordDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserDTO;
import vn.edu.hcmuaf.FocusAppProject.exception.DataNotFoundException;
import vn.edu.hcmuaf.FocusAppProject.exception.PermissionDenyException;
import vn.edu.hcmuaf.FocusAppProject.models.PasswordResetToken;
import vn.edu.hcmuaf.FocusAppProject.models.Role;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.repository.PasswordResetTokenReponsitory;
import vn.edu.hcmuaf.FocusAppProject.repository.RoleRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.TrainingProgramRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.UserRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.AuthServiceImp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceImp {
    @Value("${email.link}")
    private String link;
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
    @Autowired
    private PasswordResetTokenReponsitory passwordResetTokenReponsitory;

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

        //Send email
        String verifyLink = link + "/auth/verify?userId=" + savedUser.getId() + "&verificationCode=" + savedUser.getVerificationCode();
        Map<String, String> values = Map.of("user-name", user.getName(), "verify-link", verifyLink);

        emailUtil.sendMail(user.getEmail(), "Xác thực tài khoản tại Focus App", "verification-email", values);

        return savedUser;
    }

    @Override
    public Pair<String, String> updateVerify(long userId, String verificationCode) throws Exception {
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
                    return Pair.of("success", "Xác thực tài khoản thành công!");
                } else if (!timestamp.before(user.getTimeValid()) && !user.isVerify()) {
                    return Pair.of("error", "Xác thực tài khoản không thành công vì đã quá thời gian cho phép.");
                } else if (user.isVerify()) {
                    return Pair.of("error", "Xác thực không thành công do tài khoản của bạn đã được xác thực trước đó!");
                }
            } else {
                return Pair.of("error", "Mã xác thực không chính xác!");
            }
        }
        return Pair.of("error", "Không tìm thấy tài khoản!");
    }

    @Override
    public Pair<String, String> reVerifyAccount(long userId) throws Exception {
        Optional<User> existingUser = userRepository.findById(userId);
        User user = null;
        if (existingUser.isPresent()) {
            user = existingUser.get();
            //Create salt string(random string)
            String verificationCode = saltString.getSaltString();

            //Set timevalid
            Date todaysDate = new Date(new java.util.Date().getTime());
            Calendar c = Calendar.getInstance();
            c.setTime(todaysDate);
            //set time valid is 5 minute
            c.add(Calendar.MINUTE, 5);
            Timestamp timeValid = new Timestamp(c.getTimeInMillis());
            if (!user.isVerify()) {
                user.setVerificationCode(verificationCode);
                user.setTimeValid(timeValid);
                userRepository.save(user);

                String verifyLink = link + "/auth/verify?userId=" + user.getId() + "&verificationCode=" + verificationCode;
                Map<String, String> values = Map.of("user-name", user.getName(), "verify-link", verifyLink);

                emailUtil.sendMail(user.getEmail(), "Xác thực tài khoản tại Focus App", "verification-email", values);
                return Pair.of("success", "Email xác thực đã được gửi lại đến email của bạn. Vui lòng xác thực lại!");
            } else {
                return Pair.of("error", "Xác thực không thành công do tài khoản của bạn đã được xác thực trước đó!");
            }
        }
        return Pair.of("error", "Không tìm thấy tài khoản!");
    }

    @Override
    public String findRoleNameByEmail(String email) {
        User users = userRepository.findByEmail(email);

        return roleRepository.findRoleNameById(users.getRoles().getId());
    }

    @Override
    public boolean updatePassword(PasswordDTO passwordDTO, long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        if (passwordEncoder.matches(passwordDTO.getOldPassword(), user.getPassword())) {
            String encodedPassword = passwordEncoder.encode(passwordDTO.getNewPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public User createUserGoogle(Map<String, Object> data) throws Exception {
        String email = (String) data.get("email");
        if (userRepository.existsByEmail(email)) {
            throw new DataIntegrityViolationException("Email is already exists");
        }
        User user = User.builder().email(email)
                .name((String) data.get("name"))
                .provider("google")
                .token((String) data.get("sub"))
                .isVerify(true)
                .status(true)
                .roles(roleRepository.findRoleByRoleName("User"))
                .provider("google")
                .build();
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public Pair<String, String> forgotPassword(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return Pair.of("error", "Email không tồn tại!");
        } else {
            if (!user.isVerify()) {
                Calendar c = Calendar.getInstance();
                Date todayDate = new Date(new java.util.Date().getTime());
                c.setTime(todayDate);
                Timestamp timestamp = new Timestamp(c.getTimeInMillis());
                if (timestamp.after(user.getTimeValid())) {
                    return reVerifyAccount(user.getId());
                } else {
                    return Pair.of("error", "Tài khoản chưa được xác thực. Vui lòng xác thực tài khoản trước khi đặt lại mật khẩu!");
                }
            }
        }
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime timeValid = now.plusMinutes(5);
        String verificationCode = saltString.getSaltString();
        PasswordResetToken passwordResetToken = PasswordResetToken.builder()
                .token(verificationCode)
                .expiryDate(timeValid)
                .user(user)
                .build();

        passwordResetTokenReponsitory.save(passwordResetToken);
        String resetLink = link + "/auth/reset-password?token=" + verificationCode + "&userId=" + user.getId();
        Map<String, String> values = Map.of("user-name", user.getName(), "reset-link", resetLink);
        emailUtil.sendMail(user.getEmail(), "Quên mật khẩu tài khoản tại Focus App", "forgot-password", values);
        return Pair.of("success", "Link đặt lại mật khẩu đã được gửi đến email của bạn! Vui lòng tiến hành đặt lại mật khẩu trong vòng 5 phút!");
    }

    @Override
    public Pair<String, String> resetPassword(String newPassword, String token) throws Exception {
        PasswordResetToken passwordResetToken = passwordResetTokenReponsitory.findByToken(token);
        if (passwordResetToken == null || passwordResetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return Pair.of("error", "Token không hợp lệ hoặc đã hết hạn! Vui lòng gửi lại yêu cầu đặt lại mật khẩu!");
        }
        User user = passwordResetToken.getUser();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedPassword);
        userRepository.save(user);
        passwordResetTokenReponsitory.delete(passwordResetToken);
        return Pair.of("success", "Mật khẩu đã được đặt lại thành công! Bạn sẽ được chuyển về trang đăng nhập sau 5 giây!");
    }
}
