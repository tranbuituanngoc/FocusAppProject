package vn.edu.hcmuaf.FocusAppProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.hcmuaf.FocusAppProject.dto.UserDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserStudentInfoDTO;
import vn.edu.hcmuaf.FocusAppProject.models.Department;
import vn.edu.hcmuaf.FocusAppProject.models.Major;
import vn.edu.hcmuaf.FocusAppProject.models.TrainingProgram;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.repository.DepartmentRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.MajorRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.TrainingProgramRepository;
import vn.edu.hcmuaf.FocusAppProject.repository.UserRepository;
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
    private MajorRepository majorRepository;
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
        if (user.getDepartment() != null && user.getDesiredScore() != 0.0 && user.getMajor() != null) {
            return true;
        }
        return false;
    }

    @Override
    public void updateStudentInfo(UserStudentInfoDTO userStudentInfoDTO) {
        User user = userRepository.findById(userStudentInfoDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));
        Major major = majorRepository.findById(Long.parseLong(userStudentInfoDTO.getMajorId())).orElseThrow(() -> new RuntimeException("Major not found"));
        Department department = departmentRepository.findById(Long.parseLong(userStudentInfoDTO.getDepartmentId())).orElseThrow(() -> new RuntimeException("Department not found"));

        user.setDepartment(department);
        user.setMajor(major);
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

}
