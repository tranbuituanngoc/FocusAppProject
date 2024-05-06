package vn.edu.hcmuaf.FocusAppProject.service;

import vn.edu.hcmuaf.FocusAppProject.dto.UserDTO;
import vn.edu.hcmuaf.FocusAppProject.models.User;
import vn.edu.hcmuaf.FocusAppProject.repository.UserRepository;
import vn.edu.hcmuaf.FocusAppProject.service.Imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserServiceImp {
    @Autowired
    UserRepository userRepository;
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
            userDTO.setFaculty(u.getFaculty());
            userDTO.setMajor(u.getMajor());
            userDTO.setDesiredScore(u.getDesiredScore());

            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

}
