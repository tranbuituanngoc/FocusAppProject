package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import vn.edu.hcmuaf.FocusAppProject.dto.PasswordDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserDTO;
import vn.edu.hcmuaf.FocusAppProject.models.User;

public interface AuthServiceImp {
    boolean checkLogin(String email,String password);
    User createUser(UserDTO userDTO) throws Exception;
    User updateVerify(long userId, String verificationCode) throws Exception;
    String findRoleNameByEmail(String email);
    boolean updatePassword(PasswordDTO passwordDTO, long userId) throws Exception;
}
