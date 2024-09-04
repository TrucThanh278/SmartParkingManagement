/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.services;

import com.ou.dto.request.ChangePasswordRequest;
import com.ou.dto.request.DTOUserRequest;
import com.ou.dto.request.DTOUserUpdateRequest;
import com.ou.dto.response.DTOUserResponse;
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
    public List<User> getUsers();
    public User getUserDetail(int id);
    public User getUserByEmail(String email);
    public User getUserByUsername(String username);
    public boolean authUser(String username, String password);
    public DTOUserResponse getDTOUserByUsername(String username);
//    User addUser(Map<String, String> params, MultipartFile avatar);
    void deleteUser(Integer id);
    public User addUser(DTOUserRequest dtoUserRequest, MultipartFile avatar);
    public VerificationToken getVerificationToken(String token);
    void saveUser(User user);
    boolean confirmUser(String token);
    void enableUser (User u);
    DTOUserResponse updateUser(Integer id, DTOUserUpdateRequest dtoUserUpdateRequest, MultipartFile avatar);
    
    void changePassword(Integer userId, ChangePasswordRequest changePasswordRequest);
//    public void addOrUpdate(User u);
}
