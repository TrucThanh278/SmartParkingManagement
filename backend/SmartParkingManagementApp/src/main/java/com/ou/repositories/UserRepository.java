/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.repositories;

import com.ou.pojo.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author trucn
 */
public interface UserRepository {
    List<User> getUsers();
    User getUserDetail(int id);
    User getUserByUsername(String username);
    boolean authUser(String username, String password);
    User addUser(User user);
    boolean userExistsByUsername(String username);
    Optional<User> findById(Integer id);
    void deleteUser(Integer id);
    void enableUser(User u);
//    public void addOrUpdateUser(User u);
}
