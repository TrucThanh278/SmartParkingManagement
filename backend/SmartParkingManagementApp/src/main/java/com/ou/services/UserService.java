/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.services;

import com.ou.dto.request.ChangePasswordRequestDTO;
import com.ou.dto.request.UserRequestDTO;
import com.ou.dto.request.UserUpdateRequestDTO;
import com.ou.dto.response.UserResponseDTO;
import com.ou.pojo.User;
import com.ou.pojo.VerificationToken;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author trucn
 */
public interface UserService extends UserDetailsService{
    
    List<User> getUsers();
    User getUserDetail(int id);
    User getUserByEmail(String email);
    User getUserByUsername(String username);
    boolean authUser(String username, String password);
    UserResponseDTO getDTOUserByUsername(String username);
    void deleteUser(Integer id);
    User addUser(UserRequestDTO dtoUserRequest, MultipartFile avatar);
    VerificationToken getVerificationToken(String token);
    void saveUser(User user);
    boolean confirmUser(String token);
    void enableUser (User u);
    UserResponseDTO updateUser(Integer id, UserUpdateRequestDTO dtoUserUpdateRequest, MultipartFile avatar);
    void changePassword(Integer userId, ChangePasswordRequestDTO changePasswordRequest);
    UserResponseDTO getMyInfo();
    
}
