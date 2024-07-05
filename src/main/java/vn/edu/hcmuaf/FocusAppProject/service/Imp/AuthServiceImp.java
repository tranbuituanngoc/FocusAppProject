package vn.edu.hcmuaf.FocusAppProject.service.Imp;

import com.nimbusds.jose.util.Pair;
import vn.edu.hcmuaf.FocusAppProject.dto.PasswordDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.RoleDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserAdminDTO;
import vn.edu.hcmuaf.FocusAppProject.dto.UserDTO;
import vn.edu.hcmuaf.FocusAppProject.exception.DataNotFoundException;
import vn.edu.hcmuaf.FocusAppProject.models.Role;
import vn.edu.hcmuaf.FocusAppProject.models.User;

import java.util.List;
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
    Pair<String, String> forgotPassword(String email) throws Exception;
    Pair<String, String> resetPassword(String newPassword, String token) throws Exception;
    List<Role> getAllRole();
    User createUserAdmin(UserDTO userDTO) throws Exception;
    User updateUserAdmin(UserAdminDTO userDTO) throws Exception;
    Role getRoleById(int roleId) throws DataNotFoundException;

    Role createRole(RoleDTO role) throws Exception;
    Role updateRole(RoleDTO role) throws Exception;
    void deleteRole(int roleId) throws Exception;
}
