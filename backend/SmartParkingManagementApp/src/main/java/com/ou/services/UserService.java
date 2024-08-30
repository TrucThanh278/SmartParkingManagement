/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.services;

import com.ou.pojo.User;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author trucn
 */
public interface UserService extends UserDetailsService{
    public List<User> getUsers();
    public User getUserDetail(int id);
    public User getUserByEmail(String email);
    boolean authUser(String username, String password);
//    User addUser(Map<String, String> params, MultipartFile avatar);
    void deleteUser(Integer id);
//    public void addOrUpdate(User u);
}
