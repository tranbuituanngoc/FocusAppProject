package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import com.nimbusds.jose.util.Pair;
import vn.edu.hcmuaf.FocusAppProject.dto.PasswordDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserDTO;
import vn.edu.hcmuaf.FocusAppProject.models.User;

import java.util.Map;

public interface AuthServiceImp {
    boolean checkLogin(String email,String password);
    User createUser(UserDTO userDTO) throws Exception;
    Pair<String,String> updateVerify(long userId, String verificationCode) throws Exception;
    Pair<String,String> reVerifyAccount(long userId) throws Exception;
    String findRoleNameByEmail(String email);
    boolean updatePassword(PasswordDTO passwordDTO, long userId) throws Exception;
    User createUserGoogle(Map<String, Object> data) throws Exception;
    User findByEmail(String email);
}
