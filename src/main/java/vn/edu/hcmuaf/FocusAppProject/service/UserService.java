package vn.edu.hcmuaf.FocusAppProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.UserDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserStudentInfoDTO;
import vn.edu.hcmuaf.FocusAppProject.models.Department;
import vn.edu.hcmuaf.FocusAppProject.models.TrainingProgram;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.repository.DepartmentRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.TrainingProgramRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.UserRepository;
import vn.edu.hcmuaf.FocusAppProject.response.AdminUserResponse;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.UserServiceImp;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private TrainingProgramRepository trainingProgramRepository;

    @Override
    public List<UserDTO> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User u : userList) {
            UserDTO userDTO = new UserDTO();
            userDTO.setEmail(u.getEmail());
            userDTO.setPassword(u.getPassword());
            userDTO.setPassword(u.getProvider());
            userDTO.setToken(u.getToken());
            userDTO.setVerify(u.isVerify());
            userDTO.setVerificationCode(u.getVerificationCode());
            userDTO.setRole_id(u.getRoles().getId());
            userDTO.setDesiredScore(u.getDesiredScore());

            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @Override
    public void updateTrainingProgram(long userId, TrainingProgram trainingProgram) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setTrainingProgram(trainingProgram);
        userRepository.save(user);
    }

    @Override
    public boolean isUpdateInfo(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User " + userId + " not found"));
        if (user.getDepartment() != null && user.getDesiredScore() != 0.0) {
            return true;
        }
        return false;
    }

    @Override
    public void updateStudentInfo(UserStudentInfoDTO userStudentInfoDTO) {
        User user = userRepository.findById(userStudentInfoDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Department department = departmentRepository.findById(Long.parseLong(userStudentInfoDTO.getDepartmentId())).orElseThrow(() -> new RuntimeException("Department not found"));

        user.setDepartment(department);
        user.setDesiredScore(userStudentInfoDTO.getDesiredScore());
        userRepository.save(user);
    }

    @Override
    public void updateTrainingProgram(long userId, long trainingProgramId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        TrainingProgram trainingProgram = trainingProgramRepository.findById(trainingProgramId).orElseThrow(() -> new RuntimeException("Training program not found"));
        user.setTrainingProgram(trainingProgram);
        userRepository.save(user);
    }

    @Override
    public User updateDesiredScore(long userId, double desiredScore) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setDesiredScore(desiredScore);
        return userRepository.save(user);
    }

    @Override
    public List<AdminUserResponse> getAllUserForAdmin() {
        List<User> userList = userRepository.findAll();
        List<AdminUserResponse> adminUserResponses = new ArrayList<>();
        for (User u : userList) {
            if (!u.isDelete()) {
                String provider = u.getProvider();
                if (provider != null) {
                    provider = provider.substring(0, 1).toUpperCase() + provider.substring(1).toLowerCase();
                }
                AdminUserResponse adminUserResponse = new AdminUserResponse();
                adminUserResponse.setId(u.getId());
                adminUserResponse.setName(u.getName());
                adminUserResponse.setTypeAccount(provider == null ? "Hệ thống" : provider);
                adminUserResponse.setVerify(u.isVerify() ? "Đã xác thực" : "Chưa xác thực");
                if (u.getDepartment() != null) {
                    adminUserResponse.setDepartment(u.getDepartment().getDepartmentName());
                } else {
                    adminUserResponse.setDepartment("Chưa cập nhật");
                }
                adminUserResponses.add(adminUserResponse);
            }
        }
        return adminUserResponses;
    }

    @Override
    public User findByUserId(long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void deleteUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        user.setDelete(true);
        userRepository.save(user);
    }


}
