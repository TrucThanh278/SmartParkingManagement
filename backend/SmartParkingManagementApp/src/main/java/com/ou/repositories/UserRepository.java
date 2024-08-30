/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.repositories;

import com.ou.pojo.User;
import java.util.List;

/**
 *
 * @author trucn
 */
public interface UserRepository {
    List<User> getUsers();
    User getUserDetail(int id);
    User getUserByEmail(String email);
    boolean authUser(String username, String password);
    User addUser(User user);
    void deleteUser(Integer id);
//    public void addOrUpdateUser(User u);
}
