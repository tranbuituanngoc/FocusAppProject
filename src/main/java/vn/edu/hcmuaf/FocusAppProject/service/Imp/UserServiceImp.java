package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.PasswordDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserStudentInfoDTO;
import vn.edu.hcmuaf.FocusAppProject.models.TrainingProgram;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.response.AdminUserResponse;

import java.util.List;

public interface UserServiceImp {
    List<UserDTO> getAllUser();
    void updateTrainingProgram(long userId, TrainingProgram trainingProgram);
    boolean isUpdateInfo(long userId);
    void updateStudentInfo(UserStudentInfoDTO userStudentInfoDTO);
    void updateTrainingProgram(long userId, long trainingProgramId);
    User updateDesiredScore(long userId, double desiredScore);
    List<AdminUserResponse> getAllUserForAdmin();
    User findByUserId(long userId);
    boolean existsByEmail(String email);
    void deleteUser(long userId);
}
